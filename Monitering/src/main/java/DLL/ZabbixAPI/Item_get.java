package DLL.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Disk;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class Item_get {
	
	//Taoj instance
	private static Item_get instance;
	private Item_get() {
		// TODO Auto-generated constructor stub
	}
	public static Item_get getInstance () {
		if(instance == null) {
			instance = new Item_get();
		}
		return instance;
	}
	
	private static String ZABBIX_API_URL = "http://192.168.0.69/zabbix/api_jsonrpc.php";
	
	//IP server zabbix	
	public void setURL(String URL) {
		ZABBIX_API_URL = URL;
	}
	
	public String getURL() {
		return ZABBIX_API_URL;
	}

    // Lấy token xác thực từ Zabbix
    public String authenticate(String USERNAME, String PASSWORD) {
        try {
			URL url = new URL(ZABBIX_API_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Yêu cầu JSON để đăng nhập
        JSONObject authRequest = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("method", "user.login")
                .put("params", new JSONObject()
                        .put("username", USERNAME) // Đổi "username" thành "user" cho đúng chuẩn API của Zabbix
                        .put("password", PASSWORD))
                .put("id", 1);
        
        // Đọc phản hồi từ Zabbix
        JSONObject jsonResponse = null;
		try {
			jsonResponse = Item_get.getInstance().sendRequest(authRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return jsonResponse.getString("result"); // Auth token nằm trong "result"
    }
    
	// Lấy 1 thông tin với key
    public String getInfor(String hostId, String key, String authToken) throws IOException {
        String Value = "0";
        
        JSONArray resultArray =  getJSONresponse(hostId, "item.get", key, authToken);
        
        for (int i = 0; i < resultArray.length(); i++) {
            Value = resultArray.getJSONObject(i).optString("lastvalue", "0");
            if (!Value.equals("0")) break;
        }
        return Value;
    }
    
    // Hàm lấy thông tin nhiều ổ 
    List<Disk> getDiskInfo(String hostId, String authToken) throws Exception {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("method", "item.get");
        request.put("params", new JSONObject()
                .put("output", "extend")
                .put("hostids", hostId)
                .put("search", new JSONObject().put("key_", "vfs.fs"))); // Tìm kiếm các item có key vfs.fs
        request.put("auth", authToken);
        request.put("id", 1);

        JSONObject response = sendRequest(request);
        JSONArray items = new JSONObject(response.toString()).getJSONArray("result");

        List<Disk> diskInfoList = new ArrayList<>();

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
                continue; // Bỏ qua nếu giá trị trống
            }

            // Thêm thông tin ổ đĩa vào danh sách
            diskInfoList.add(new Disk(diskName, lastValueOutput));
        }

        return diskInfoList;
    }
    
    // Hàm gửi và lấy JSONArray
    public JSONArray getJSONresponse(String hostId, String method, String key, String authToken) throws IOException {
        String Value = "0";
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", method);
        json.put("params", new JSONObject() {{
            put("output", "extend");
            put("hostids", hostId);
            put("search", new JSONObject() {{
                put("key_", key); // Key cho RAM
            }});
        }});
        json.put("auth", authToken);
        json.put("id", 1);

        JSONObject jsonResponse = sendRequest(json);

        if (jsonResponse.has("error")) {
            return null;
        }

        return  jsonResponse.getJSONArray("result");
    }
    
    //Gui vaf nhan ket qua tu server
    public JSONObject sendRequest(JSONObject json) throws IOException {
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
        
        JSONObject result = new JSONObject(response.toString());

        return result;
    }
}