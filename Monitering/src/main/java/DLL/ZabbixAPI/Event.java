package DLL.ZabbixAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	            String eventId = obj.getString("eventid");
	            String name = obj.getString("name");
	            int severity = obj.getInt("severity");
	            long clock = obj.getLong("clock");
	            String acknowledged = obj.getString("acknowledged");
	            Boolean authacknowledged = false;
	            if(acknowledged.equals("1")) authacknowledged = true;
	            
	            long ackClock = 0;

	            // Lấy danh sách acknowledge
	            List<Acknowledge> actions = new ArrayList<>();
	            if (obj.has("acknowledges")) {
	                JSONArray acknowledges = obj.getJSONArray("acknowledges");
	                for (int j = 0; j < acknowledges.length(); j++) {
	                    JSONObject ack = acknowledges.getJSONObject(j);
	                    String user = ack.optString("alias", "Unknown");
	                    String message = ack.optString("message", "No message");
	                    long ackTime = ack.getLong("clock");

	                    // Cập nhật ackClock nếu acknowledge mới nhất
	                    if (ackClock < ackTime) {
	                        ackClock = ackTime;
	                    }

	                    // Thêm acknowledge vào danh sách
	                    actions.add(new Acknowledge(message, ackTime, user));
	                }
	            }

	            // Thêm problem vào danh sách
	            problems.add(new Problem(eventId, name, severity, clock, ackClock, authacknowledged, actions));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return problems;
	}



}
