package Models.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class getInfor {

    private static final String ZABBIX_API_URL = "http://10.10.2.170/zabbix/api_jsonrpc.php";
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
            
            String host ="10084";
            //Name
        	System.out.print("Name: " + getInfor.getInstance().getName(host) + "\n");
        	
            while(true) {
            	            	
	            // Lấy CPU load cho một host cụ thể (thay hostId bằng ID host thực tế)
	            System.out.print("CPU :" + getInfor.getInstance().getCpuLoad(host) + "\n");
	
	            // Lấy thông tin băng thông mạng
	            System.out.print("Receive: " + getInfor.getInstance().getNetworkTraffic_recei(host) + "\n");
	            System.out.print("Send: " + getInfor.getInstance().getNetworkTraffic_send(host) + "\n");
	            
	            
	            //Lấy thông tin RAM
	            System.out.print("RAM: " + getInfor.getInstance().getRAMInfo_total(host) + "\n");
	            System.out.print("RAM-used: " + getInfor.getInstance().getRAMInfo_used(host) + "\n");
	            System.out.print("RAM-util: " + getInfor.getInstance().getRAMInfo_util(host) + "\n");
	            
	            //Thời gian sử dụng
	            System.out.print("Time(hardware): " + getInfor.getInstance().getTime_hardware(host) + "\n");
	            System.out.print("Time(network): " + getInfor.getInstance().getTime_network(host) + "\n");
	            
	            //Các ổ đĩa
	            List<DiskInfo> disk = getInstance().getDiskInfo(host);
	            for (DiskInfo diskInfo : disk) {
	                System.out.println("Disk name: " + diskInfo.name);
	                System.out.println("Last Value: " + diskInfo.lastValue);
	            }
	            
	            System.out.print("\n");
	            TimeUnit.SECONDS.sleep(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
 // Lấy thông tin ổ đĩa
    List<DiskInfo> getDiskInfo(String hostId) throws Exception {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("method", "item.get");
        request.put("params", new JSONObject()
                .put("output", "extend")
                .put("hostids", hostId)
                .put("search", new JSONObject().put("key_", "vfs.fs"))); // Tìm kiếm các item có key vfs.fs
        request.put("auth", authToken);
        request.put("id", 1);

        String response = getInfor.getInstance().sendRequest(request);
        JSONArray items = new JSONObject(response).getJSONArray("result");

        List<DiskInfo> diskInfoList = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String diskName = item.getString("name");
            String lastValueStr = item.optString("lastvalue", ""); // Lấy giá trị lastvalue

            // Bỏ qua mục không cần thiết
            if (diskName.contains("Windows: SNMP walk mounted filesystems")) {
                continue;
            }

            double lastValueInGB = 0.0; // Khởi tạo biến để lưu giá trị sau khi chuyển đổi
            String lastValueOutput = ""; // Biến để lưu giá trị đầu ra

            // Kiểm tra xem lastValueStr có rỗng hay không
            if (!lastValueStr.isEmpty()) {
                try {
                    // Kiểm tra nếu lastValueStr chứa ký tự '%'
                    if (diskName.contains("%")) {
                        // Giá trị là phần trăm, không cần chia
                    	double lastValue = Double.parseDouble(lastValueStr); // Chuyển đổi sang double
                        lastValueOutput = String.format("%.2f", lastValue) + " %"; // Lưu lại giá trị phần trăm
                    } else {
                        // Giá trị là dung lượng, chia cho 1024^3 để chuyển đổi thành GB
                        double lastValue = Double.parseDouble(lastValueStr); // Chuyển đổi sang double
                        lastValueInGB = lastValue / (1024 * 1024 * 1024); // Chia để chuyển đổi thành GB
                        lastValueOutput = String.format("%.2f GB", lastValueInGB); // Định dạng giá trị ra 2 chữ số thập phân
                    }
                } catch (NumberFormatException e) {
                    // Bỏ qua các lỗi khi phân tích giá trị
                    continue; // Bỏ qua nếu có lỗi
                }
            } else {
                System.out.println("Last value is empty for disk: " + diskName);
                continue; // Bỏ qua nếu giá trị trống
            }

            // Thêm thông tin ổ đĩa vào danh sách
            diskInfoList.add(new DiskInfo(diskName, lastValueOutput));
        }

        return diskInfoList;
    }



    
    //Lấy tên thiết bị
    public String getName(String hostId) throws IOException {
    	return Item_get.getIntance().getInfor(hostId, "system.name", authToken);
    }
    
  //Thời gian sử dụng mang
    public String getTime_network(String hostId) throws Exception {
    	String uptimeInSeconds = Item_get.getIntance().getInfor(hostId,"system.net.uptime" , authToken);
    	long seconds = Long.parseLong(uptimeInSeconds);

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
    
    //Thời gian sử dụng máy
    public String getTime_hardware(String hostId) throws Exception {
    	String uptimeInSeconds = Item_get.getIntance().getInfor(hostId,"system.hw.uptime" , authToken);
    	long seconds = Long.parseLong(uptimeInSeconds);

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
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

    // Lệnh lấy % CPU load
    public String getCpuLoad(String hostId) throws IOException {
    	
    	return Item_get.getIntance().getInfor(hostId,"system.cpu.util" , authToken) + " %";
        
    }
    
 // Lấy tốc độ bit mạng nhận
    public String getNetworkTraffic_recei(String hostId) throws IOException {
    	return Item_get.getIntance().getInfor(hostId,"net.if.in" , authToken) + " Kbps";
    	
    }

 // Lấy thông tin RAM tối đa
    public String getRAMInfo_total(String hostId) throws IOException {
    	String ramValue = Item_get.getIntance().getInfor(hostId, "vm.memory.walk.data.total", authToken);
    	
        // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue) / (1024 * 1024 * 1024);
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        return String.format("%.2f GB", ramInGB);
        
    }
    
 // Lấy thông tin RAM đã sử dụng
    public String getRAMInfo_used(String hostId) throws IOException {
    	String ramValue = Item_get.getIntance().getInfor(hostId, "vm.memory.used", authToken);
    	
        // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue) / (1024 * 1024 * 1024);
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        return String.format("%.2f GB", ramInGB);

    }
    
 // Lấy thông tin RAM % đang sử dụng
    public String getRAMInfo_util(String hostId) throws IOException {
    	String ramValue = Item_get.getIntance().getInfor(hostId, "vm.memory.util", authToken);
    	 // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue);
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        return String.format("%.2f%%", ramInGB);
    }
    
 // Lấy tốc độ bit mạng gửi
    public String getNetworkTraffic_send(String hostId) throws IOException {
    	return Item_get.getIntance().getInfor(hostId,"net.if.out" , authToken) + " Kbps";
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

    
