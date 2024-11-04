package Models.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class getInfor {

    private static final String ZABBIX_API_URL = "http://192.168.0.69/zabbix/api_jsonrpc.php";
    private static String USER = "Admin";
    private static String PASSWORD = "zabbix";
    private static String authToken = "";

    //Tạo instance
    private static getInfor instance;
    private getInfor() {}

    public static getInfor getInstance() {
        if (instance == null) {
            instance = new getInfor();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        try {
            // Xác thực và lấy token
            authToken = getInfor.getInstance().authenticate();
            System.out.println("Authenticated with token: " + authToken);
            // Lấy danh sách host
            getInfor.getInstance().getHosts(authToken);
            
            String host ="10640";
            
            while(true) {

            // Lấy CPU load cho một host cụ thể (thay hostId bằng ID host thực tế)
            System.out.print("CPU :" + getInfor.getInstance().getCpuLoad(host) + "%\n");

            // Lấy thông tin băng thông mạng
            System.out.print("Receive: " + getInfor.getInstance().getNetworkTraffic_recei(host) + "Kbps\n");
            System.out.print("Send: " + getInfor.getInstance().getNetworkTraffic_send(host) + "Kbps\n");
            
            System.out.print("\n");
            TimeUnit.SECONDS.sleep(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Lấy token xác thực từ Zabbix
    public String authenticate() throws IOException {
        URL url = new URL(ZABBIX_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Yêu cầu JSON để đăng nhập
        String authRequest = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("method", "user.login")
                .put("params", new JSONObject()
                        .put("username", USER)
                        .put("password", PASSWORD))
                .put("id", 1)
                .toString();

        try (OutputStream os = conn.getOutputStream()) {
            os.write(authRequest.getBytes());
            os.flush();
        }

        // Đọc phản hồi từ Zabbix
        String response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            response = br.readLine();
        }

        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getString("result");
    }

    // Lấy thông tin các host hiện có
    public void getHosts(String authToken) throws IOException {
        URL url = new URL(ZABBIX_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Yêu cầu JSON để lấy danh sách host
        String hostRequest = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("method", "host.get")
                .put("params", new JSONObject()
                        .put("output", new JSONArray(Arrays.asList("hostid", "host"))))
                .put("auth", authToken)
                .put("id", 1)
                .toString();

        try (OutputStream os = conn.getOutputStream()) {
            os.write(hostRequest.getBytes());
            os.flush();
        }

        // Đọc phản hồi từ Zabbix
        String response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            response = br.readLine();
        }

        JSONObject jsonResponse = new JSONObject(response);
        System.out.println("Hosts: " + jsonResponse.get("result"));
    }

    // Lệnh lấy CPU load
    public String getCpuLoad(String hostId) throws IOException {
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "item.get");
        json.put("params", new JSONObject() {{
            put("output", new JSONArray(Arrays.asList("lastvalue")));  // Chỉ lấy giá trị cuối
            put("hostids", hostId);
            put("search", new JSONObject() {{
                put("key_", "system.cpu.util");
            }});
        }});
        json.put("auth", authToken);
        json.put("id", 1);

        String response = sendRequest(json);

        // Phân tích phản hồi JSON
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray resultArray = jsonResponse.getJSONArray("result");
        String lastValue = null;
        if (resultArray.length() > 0) {
        	lastValue = resultArray.getJSONObject(0).getString("lastvalue");
        } 
        return lastValue;
    }
    
 // Lấy tốc độ bit mạng nhận
    public String getNetworkTraffic_recei(String hostId) throws IOException {
    	
        String lastValueIn = "0";

        // Tạo một đối tượng JSON cho request
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "item.get");
        json.put("params", new JSONObject() {{
            put("output", "extend");
            put("hostids", hostId);
            put("search", new JSONObject() {{
            	put("key_", "net.if.in");
            }}); // Tìm kiếm tất cả các item
        }});
        json.put("auth", authToken);
        json.put("id", 1);

        // Gửi yêu cầu và nhận phản hồi
        String responseIn = sendRequest(json);
        JSONObject jsonResponseIn = new JSONObject(responseIn);

        // Kiểm tra xem phản hồi có trường "error" không
        if (jsonResponseIn.has("error")) {
            JSONObject errorObject = jsonResponseIn.getJSONObject("error");
            System.err.println("Error: " + errorObject.getString("message") + " - " + errorObject.getString("data"));
            return "Error: " + errorObject.getString("message");
        }

        // Xử lý phản hồi để lấy băng thông nhận
        JSONArray resultArrayIn = jsonResponseIn.getJSONArray("result");
        for (int i = 0; i < resultArrayIn.length(); i++) {
            String lastValueOut = resultArrayIn.getJSONObject(i).optString("lastvalue", "0");
            if (!lastValueOut.equals("0")) {
                lastValueIn = lastValueOut; // Cập nhật lastValueIn nếu tìm thấy giá trị hợp lệ
                break; // Thoát khỏi vòng lặp nếu có giá trị hợp lệ
            }
        }

        return lastValueIn;
    }


    
 // Lấy tốc độ bit mạng gửi
    public String getNetworkTraffic_send(String hostId) throws IOException {

        // Tạo một đối tượng JSON cho request
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "item.get");
        json.put("params", new JSONObject() {{
            put("output", "extend");
            put("hostids", hostId);
            put("search", new JSONObject(){{
            	put("key_", "net.if.out");
            }});
        }});
        json.put("auth", authToken);
        json.put("id", 1);

        // Gửi yêu cầu và nhận phản hồi
        String responseOut = sendRequest(json);
        JSONObject jsonResponseOut = new JSONObject(responseOut);

        // Kiểm tra lỗi
        if (jsonResponseOut.has("error")) {
            JSONObject errorObject = jsonResponseOut.getJSONObject("error");
            System.err.println("Error: " + errorObject.getString("message") + " - " + errorObject.getString("data"));
            return "Error: " + errorObject.getString("message");
        }

        // Lưu trữ giá trị cuối cùng
        String lastValue = "0";

        // Duyệt qua từng item trong phản hồi
        JSONArray resultArrayOut = jsonResponseOut.getJSONArray("result");
        for (int i = 0; i < resultArrayOut.length(); i++) {
            String lastValueOut = resultArrayOut.getJSONObject(i).optString("lastvalue", "0");
            // Cập nhật lastValue nếu cần
            if (!lastValueOut.equals("0")) {
                lastValue = lastValueOut;
                break; // Thoát nếu tìm thấy giá trị hợp lệ
            }
        }

        return lastValue;
    }

    //Gui vaf nhan ket qua tu server
    private String sendRequest(JSONObject json) throws IOException {
        URL url = new URL(ZABBIX_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Gửi request
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes());
            os.flush();
        }

        // Đọc phản hồi
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }
    
//    //Xem các key của Item
//    public void getItemKeys(String authToken, String hostId) throws IOException {
//
//        // Yêu cầu JSON để lấy danh sách item
//    	JSONObject itemRequest = new JSONObject() {{
//    	    put("jsonrpc", "2.0");
//    	    put("method", "item.get");
//    	    put("params", new JSONObject() {{
//    	        put("output", new JSONArray(Arrays.asList("itemid", "key_")));
//    	        put("hostids", hostId);
//    	    }});
//    	    put("auth", authToken);
//    	    put("id", 1);
//    	}};
//
//        // Đọc phản hồi từ Zabbix
//        String response = getInstance().sendRequest(itemRequest);
//
//        JSONObject jsonResponse = new JSONObject(response);
//        JSONArray resultArray = jsonResponse.getJSONArray("result");
//        
//        // In ra item keys
//        for (int i = 0; i < resultArray.length(); i++) {
//            JSONObject item = resultArray.getJSONObject(i);
//            System.out.println("Item ID: " + item.getString("itemid") + ", Key: " + item.getString("key_"));
//        }
//    }


    

}
