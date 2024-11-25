package DLL.ZabbixAPI;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
//									(token, eventid, severity, mesage, acknowledge)
//		getInstance().updateProblem(token, "2189", 1, "Đổi mạng vẫn ko ping đc, do máy", false);
		
		//Test đóng problem
//		String result = getInstance().closeProblem(token, "2189");
//		System.out.print(result);
		
		rs = getInstance().getProblems(token);
		for( Problem pr : rs) {
			pr.Display();
			System.out.print("\n");
		}
	}
	
	//Hàm lấy các vấn đ�? hiện tại
	public List<Problem> getProblems(String authToken) {
<<<<<<< HEAD
		//Tạo list trả v�?
	    List<Problem> problems = new ArrayList<Problem>();
=======
	    List<Problem> problems = new ArrayList<>();
>>>>>>> bbae12ffffc95a05958bcc83df8f8f404ce9c92e

	    // Tạo JSON request
	    JSONObject request = new JSONObject()
	    	    .put("jsonrpc", "2.0")
	    	    .put("method", "problem.get")
	    	    .put("id", 1)
	    	    .put("auth", authToken)
	    	    .put("params", new JSONObject()
	    	        .put("output", new JSONArray()
	    	            .put("eventid")
	    	            .put("name")
	    	            .put("clock")
	    	            .put("severity")
	    	            .put("acknowledged")
	    	        		)
	    	        .put("source", 0) // Chỉ lấy các sự kiện liên quan đến trigger
	    	        .put("object", 0) // Liên kết với trigger
	    	        .put("sortfield", "eventid") // Sắp xếp theo thời gian
	    	        .put("sortorder", "DESC") // Mới nhất trước
	    	        .put("suppressed",false)
	    	    		
	    	    		);


	    try {
	        // Gửi request
	        JSONObject jsonResponse = Item_get.getInstance().sendRequest(request);

	        JSONArray results = jsonResponse.getJSONArray("result");
	        
	        // Duyệt qua từng problem
	        for (int i = 0; i < results.length(); i++) {
	            JSONObject obj = results.getJSONObject(i);
	            
	            //Lấy các thông tin về event(problem)
	            String eventId = obj.getString("eventid");
	            String name = obj.getString("name");
	            int severity = obj.getInt("severity");
	            long L_clock = obj.getLong("clock");
	            String acknowledged = obj.getString("acknowledged");
	            Boolean authacknowledged = false;
	            if(acknowledged.equals("1")) authacknowledged = true;
	            
	            //Thời gian xác nhận mặc định bằng 0 - chưa đc xác nhận
	            long L_ackClock = 0;

	            // Lấy danh sách acknowledge
	            List<Acknowledge> actions = getAction(authToken, eventId);	   
	            
	            for(Acknowledge at : actions) {
	            	long ackTime = convertDateToEpoch(at.getClock());
                // Cập nhật ackClock nếu event đã xác nhận
	                if (L_ackClock < ackTime && ackTime != 0) {
	                    L_ackClock = ackTime;
	                }
	            }

	            //Chuyển định dạng clock từ long thành string
	            String clock = convertEpochToDate(L_clock);
	            String ackClock = "Chưa xác nhận";
	            if(L_ackClock != 0) ackClock = convertEpochToDate(L_ackClock);
	            
	            // Lấy thông tin host từ ênt
	            Map<String, String> h = getHostFromEvent(authToken, eventId);
	            String hostId = h.get("hostid");
	            String hostName = h.get("hostname");
	            
	            // Thêm problem vào danh sách
	            problems.add(new Problem(eventId, name, hostId, hostName, severity, clock, ackClock, authacknowledged, actions));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return problems;
	}
	
	//Lấy lịch sử hoạt động của problem
	public List<Acknowledge> getAction(String authToken, String evenId) {
	    List<Acknowledge> actions = new ArrayList<>();

	    // Tạo JSON request
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "problem.get")
	            .put("id", 1)
	            .put("auth", authToken)
	            .put("params", new JSONObject()
	                    .put("output", new JSONArray()
	                            .put("acknowledged")
	                    )
	                    .put("eventids", evenId)
	                    .put("selectAcknowledges", "extend")
	                    .put("sortfield", "eventid")
	                    .put("sortorder", "DESC")
	            );

	    // Gửi request
	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (Exception e) {
	        e.printStackTrace();
<<<<<<< HEAD
	        return problems; // Trả v�? danh sách rỗng nếu lỗi xảy ra
=======
	        return actions; // Trả về danh sách rỗng nếu có lỗi
>>>>>>> bbae12ffffc95a05958bcc83df8f8f404ce9c92e
	    }

	    // Xử lý kết quả
	    JSONArray results = jsonResponse.getJSONArray("result");

	    if (results.isEmpty()) {
	        
	        return actions;
	    }

	    for (int i = 0; i < results.length(); i++) {
	        JSONObject obj = results.getJSONObject(i);
	        if (obj.has("acknowledges")) {
	            JSONArray acknowledges = obj.getJSONArray("acknowledges");
	            for (int j = 0; j < acknowledges.length(); j++) {
	                JSONObject ack = acknowledges.getJSONObject(j);
	                String message = ack.optString("message", "No message");
	                long tempTime = Long.parseLong(ack.getString("clock"));
	                String ackTime = convertEpochToDate(tempTime);
	                String old_severity = ack.optString("old_severity", "0");
	                String new_severity = ack.optString("new_severity", "0");

	                // Thêm acknowledge vào danh sách
	                actions.add(new Acknowledge(message, ackTime, old_severity, new_severity));
	            }
	        }
	    }

	    return actions;
	}

	
	//Hàm lấy host xảy ra problem
 	public Map<String, String> getHostFromEvent(String authToken, String eventId) {
	    Map<String, String> hostInfo = new HashMap<>(); // Lưu hostid và hostname

	    try {
<<<<<<< HEAD
	    	//Xử lý kết quả
	        JSONArray results = jsonResponse.getJSONArray("result");
	        for (int i = 0; i < results.length(); i++) {
	        	
	        	//Lấy thông tin
	            JSONObject obj = results.getJSONObject(i);
	            String eventId = obj.getString("eventid");
	            String name = obj.getString("name");
	            int severity = obj.getInt("severity");
	            long clock = obj.getLong("clock");  // Th�?i gian sự kiện
	            long ackClock = 0;  // Mặc định là 0 (chưa có th�?i gian ghi nhận)
	            boolean acknowledged = obj.getInt("acknowledged") == 1;
	            String message = ""; // Mặc định message rỗng

	            // Kiểm tra mảng acknowledges và lấy clock và message nếu có
	            if (obj.has("acknowledges")) {
	                JSONArray acknowledges = obj.getJSONArray("acknowledges");
	                if (acknowledges.length() > 0) {
	                    JSONObject acknowledge = acknowledges.getJSONObject(0); // Chỉ lấy phần tử đầu tiên (nếu có)
	                    ackClock = acknowledge.optLong("clock", 0);  // Lấy th�?i gian ghi nhận
	                    message = acknowledge.optString("message", "");  // Lấy message từ phần acknowledges
=======
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
>>>>>>> bbae12ffffc95a05958bcc83df8f8f404ce9c92e
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return hostInfo;
	}
	
	//Hàm cập nhập xác nhận vấn đề (cập nhập, thay đổi, xác nhận và thêm message
	public String updateProblem(String token, String eventId, int severity, String message, boolean ack) {
		String rs = null;
		int action;
//		1 - close problem;
//		2 - acknowledge event;
//		4 - add message;
//		8 - change severity;
//		16 - unacknowledge event;
//		32 - suppress event;
//		64 - unsuppress event;
//		128 - change event rank to cause;
//		256 - change event rank to symptom.
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
		            rs =  "Update problem thành công";
		            
		        } else {
		            rs ="Lỗi: " + response.getJSONObject("error").optString("data");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 return rs;
	}
	
	//Hàm đóng vấn đề (Có thể ko đóng đc), xem như đã giải quyết
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
	
    // Chuyển epoch time (giây) thành định dạng ngày tháng
    public String convertEpochToDate(long epochTime) {
        Instant instant = Instant.ofEpochSecond(epochTime);
        LocalDateTime dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    
 // Chuyển ngày tháng (String) thành epoch time (giây)
    public long convertDateToEpoch(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

}
