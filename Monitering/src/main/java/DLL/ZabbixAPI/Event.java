package DLL.ZabbixAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Acknowledge;
import Model.Problem;

//Lớp xử lý, thao tác với problem
public class Event {
	
	//Tạo instance
	private static Event instance;
	private Event() {
		// TODO Auto-generated constructor stub
	}
	public static Event getInstance() {
		if(instance == null) {
			instance = new Event();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		String token = Item_get.getInstance().authenticate("Admin", "zabbix");
		List<Problem> rs ;
		
		//Test xác nhận problem
//		getInstance().updateProblem(token, "816", 2, "Đã sửa", true);
		
		rs = getInstance().getProblems(token);
		for( Problem pr : rs) {
			pr.Display();
			System.out.print("\n");
		}
	}
	
	//Hàm lấy các vấn đề hiện tại
	public List<Problem> getProblems(String authToken) {
	    List<Problem> problems = new ArrayList<>();

	    // Tạo JSON request
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "problem.get")
	            .put("id", 1)
	            .put("auth", authToken)
	            .put("params", new JSONObject()
	                    .put("output", new JSONArray() // Lấy các trường cần thiết
	                            .put("eventid")
	                            .put("name")
	                            .put("severity")
	                            .put("clock")
	                            .put("acknowledged")
	                            .put("suppressed") // Thêm trường trạng thái
	                    )
	                    .put("selectAcknowledges", "extend") // Lấy acknowledge đầy đủ
	                    .put("sortfield", "eventid")          // Sắp xếp theo thời gian
	                    .put("sortorder", "DESC")           // Theo thứ tự giảm dần
	            );

	    try {
	        // Gửi request
	        JSONObject jsonResponse = Item_get.getInstance().sendRequest(request);
	        System.out.print(jsonResponse + "\n");
	        JSONArray results = jsonResponse.getJSONArray("result");
	        
	        // Duyệt qua từng problem
	        for (int i = 0; i < results.length(); i++) {
	            JSONObject obj = results.getJSONObject(i);
	            
	            //Lấy các thông tin về event(problem)
	            String eventId = obj.getString("eventid");
	            String name = obj.getString("name");
	            int status = obj.getInt("suppressed");
	            int severity = obj.getInt("severity");
	            long clock = obj.getLong("clock");
	            String acknowledged = obj.getString("acknowledged");
	            Boolean authacknowledged = false;
	            if(acknowledged.equals("1")) authacknowledged = true;
	            
	            //Thời gian xác nhận mặc định bằng 0 - chưa đc xác nhận
	            long ackClock = 0;

	            // Lấy danh sách acknowledge
	            List<Acknowledge> actions = new ArrayList<>();
	            if (obj.has("acknowledges")) {
	                JSONArray acknowledges = obj.getJSONArray("acknowledges");
	                for (int j = 0; j < acknowledges.length(); j++) {
	                    JSONObject ack = acknowledges.getJSONObject(j);
	                    String message = ack.optString("message", "No message");
	                    long ackTime = ack.getLong("clock");
	                    String old_severity = ack.optString("old_severity", "0");
	                    String new_severity = ack.optString("new_severity", "0");

	                    // Cập nhật ackClock nếu event đã xác nhận
	                    if (ackClock < ackTime && ackTime != 0) {
	                        ackClock = ackTime;
	                    }

	                    // Thêm acknowledge vào danh sách
	                    actions.add(new Acknowledge(message, ackTime, old_severity, new_severity));
	                }
	            }
	            
	            // Lấy thông tin host từ ênt
	            Map<String, String> h = getHostFromEvent(authToken, eventId);
	            String hostId = h.get("hostid");
	            String hostName = h.get("hostname");
	            
	            // Thêm problem vào danh sách
	            problems.add(new Problem(eventId, name, status, hostId, hostName, severity, clock, ackClock, authacknowledged, actions));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return problems;
	}
	
	//Hàm lấy host xảy ra problem
	public Map<String, String> getHostFromEvent(String authToken, String eventId) {
	    Map<String, String> hostInfo = new HashMap<>(); // Lưu hostid và hostname

	    try {
	        // Tạo JSON request cho API event.get
	        JSONObject hostRequest = new JSONObject()
	                .put("jsonrpc", "2.0")
	                .put("method", "event.get")
	                .put("id", 2)
	                .put("auth", authToken)
	                .put("params", new JSONObject()
	                        .put("eventids", eventId)
	                        .put("selectHosts", new JSONArray()
	                                .put("hostid")
	                                .put("host")
	                        )
	                        .put("output", "extend")
	                );

	        // Gửi yêu cầu đến API
	        JSONObject hostResponse = Item_get.getInstance().sendRequest(hostRequest);
	        JSONArray hostResults = hostResponse.optJSONArray("result");

	        // Kiểm tra và trích xuất thông tin host
	        if (hostResults != null && hostResults.length() > 0) {
	            JSONObject hostObj = hostResults.getJSONObject(0);
	            if (hostObj.has("hosts")) {
	                JSONArray hosts = hostObj.getJSONArray("hosts");
	                if (hosts.length() > 0) {
	                    JSONObject host = hosts.getJSONObject(0);
	                    hostInfo.put("hostid", host.optString("hostid", ""));
	                    hostInfo.put("hostname", host.optString("host", ""));
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return hostInfo;
	}
	
	//Hàm cập nhập xác nhận vấn đề (cập nhập, thay đổi, xác nhận và thêm message
	public void updateProblem(String token, String eventId, int severity, String message, boolean ack) {
		int action;
		if(ack) action = 14;
		else action = 28;
		
		 try {
		        // Tạo JSON request để xác nhận sự kiện
		        JSONObject request = new JSONObject()
		                .put("jsonrpc", "2.0")
		                .put("method", "event.acknowledge")
		                .put("id", 6)
		                .put("auth", token)
		                .put("params", new JSONObject()
		                        .put("eventids", new JSONArray().put(eventId)) // Sự kiện cần xác nhận
		                        .put("action", action)
		                        .put("message", message) // Thông điệp xác nhận
		                        .put("severity", severity)
		                        
		                );

		        // Gửi request đến API
		        JSONObject response = Item_get.getInstance().sendRequest(request);

		        // Kiểm tra kết quả trả về từ API
		        if (response.has("result")) {
		            System.out.println(response);
		            
		        } else {
		            System.err.println(response);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	//Hàm đóng vấn đề (Có thể ko đóng đc)
	public String closeProblem(String token, String eventId) {		
		String rs = null;
		 try {
		        // Tạo JSON request để xác nhận sự kiện
		        JSONObject request = new JSONObject()
		                .put("jsonrpc", "2.0")
		                .put("method", "event.acknowledge")
		                .put("id", 6)
		                .put("auth", token)
		                .put("params", new JSONObject()
		                        .put("eventids", new JSONArray().put(eventId)) // Sự kiện cần xác nhận
		                        .put("action", 1)

		                        
		                );

		        // Gửi request đến API
		        JSONObject response = Item_get.getInstance().sendRequest(request);

		        // Kiểm tra kết quả trả về từ API
		        if (response.has("result")) {
		            rs = "Đã đóng vấn đề";
		            
		        } else {
		        	rs = "Lỗi: " + response.getJSONObject("error").optString("data");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return rs;
	}

}
