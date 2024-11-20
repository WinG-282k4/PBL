package DLL.ZabbixAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
		//Tạo list trả về
	    List<Problem> problems = new ArrayList<>();

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
	                    .put("severity")
	                    .put("clock")
	                    .put("acknowledged")
	                )
	                .put("sortfield", "eventid")
	                .put("sortorder", "DESC")
	            );

	    // Gửi request và xử lý response
	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return problems; // Trả về danh sách rỗng nếu lỗi xảy ra
	    }
	    
	    System.out.print(jsonResponse);

	    try {
	    	//Xử lý kết quả
	        JSONArray results = jsonResponse.getJSONArray("result");
	        for (int i = 0; i < results.length(); i++) {
	        	
	        	//Lấy thông tin
	            JSONObject obj = results.getJSONObject(i);
	            String eventId = obj.getString("eventid");
	            String name = obj.getString("name");
	            int severity = obj.getInt("severity");
	            long clock = obj.getLong("clock");  // Thời gian sự kiện
	            long ackClock = 0;  // Mặc định là 0 (chưa có thời gian ghi nhận)
	            boolean acknowledged = obj.getInt("acknowledged") == 1;
	            String message = ""; // Mặc định message rỗng

	            // Kiểm tra mảng acknowledges và lấy clock và message nếu có
	            if (obj.has("acknowledges")) {
	                JSONArray acknowledges = obj.getJSONArray("acknowledges");
	                if (acknowledges.length() > 0) {
	                    JSONObject acknowledge = acknowledges.getJSONObject(0); // Chỉ lấy phần tử đầu tiên (nếu có)
	                    ackClock = acknowledge.optLong("clock", 0);  // Lấy thời gian ghi nhận
	                    message = acknowledge.optString("message", "");  // Lấy message từ phần acknowledges
	                }
	            }

	            // Thêm problem vào danh sách
	            problems.add(new Problem(eventId, name, severity, clock, ackClock, acknowledged, message));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return problems;
	}


}
