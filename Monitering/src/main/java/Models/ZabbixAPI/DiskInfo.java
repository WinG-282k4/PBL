package Models.ZabbixAPI;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

// Thêm class để lưu thông tin ổ đĩa
class DiskInfo {
    String name;
    String lastValue;

    public DiskInfo(String name, String lastValue) {
        this.name = name;
        this.lastValue = lastValue;
    }
    
    public static void main(String[] args) {
		System.out.print(new DiskInfo(null, null));
	}
    

}