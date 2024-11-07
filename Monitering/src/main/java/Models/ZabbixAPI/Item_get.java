package Models.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Item_get {
	
	//Taoj instance
	private static Item_get instance;
	private Item_get() {
		// TODO Auto-generated constructor stub
	}
	public static Item_get getIntance () {
		if(instance == null) {
			instance = new Item_get();
		}
		return instance;
	}
	
	//IP server zabbix
	private static final String ZABBIX_API_URL = "http://10.10.2.170/zabbix/api_jsonrpc.php";
	
	// Lấy 1 thông tin với key
    public String getInfor(String hostId, String key, String authToken) throws IOException {
        String Value = "0";
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "item.get");
        json.put("params", new JSONObject() {{
            put("output", "extend");
            put("hostids", hostId);
            put("search", new JSONObject() {{
                put("key_", key); // Key cho RAM
            }});
        }});
        json.put("auth", authToken);
        json.put("id", 1);

        String response = sendRequest(json);
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.has("error")) {
            return "Error: " + jsonResponse.getJSONObject("error").getString("message");
        }

        JSONArray resultArray = jsonResponse.getJSONArray("result");
        for (int i = 0; i < resultArray.length(); i++) {
            Value = resultArray.getJSONObject(i).optString("lastvalue", "0");
            if (!Value.equals("0")) break;
        }
        return Value;
    }
    
    // Hàm lấy thông tin nhiều ổ đĩa theo key
    public List<DiskInfo> getDiskInfoByKey(String hostId, String key, String authToken) throws Exception {
        JSONObject request = new JSONObject();
        request.put("jsonrpc", "2.0");
        request.put("method", "item.get");
        request.put("params", new JSONObject()
                .put("output", "extend")
                .put("hostids", hostId)
                .put("search", new JSONObject().put("key_", key)));
        request.put("auth", authToken);
        request.put("id", 1);

        String response = sendRequest(request);
        JSONArray items = new JSONObject(response).getJSONArray("result");

        List<DiskInfo> diskInfoList = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String diskName = item.getString("name");
            String lastValue = item.optString("lastvalue", "N/A"); // Giá trị mặc định nếu không tìm thấy

            // Thêm thông tin ổ đĩa vào danh sách
            diskInfoList.add(new DiskInfo(diskName, lastValue));
        }

        return diskInfoList;
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
}
