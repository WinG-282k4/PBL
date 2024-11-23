package DLL.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Device;
import Model.Disk;
import Model.Item;

//Lớp nghiệp vụ dùng để lấy các thông tin như tốc độ mạng, CPU, .. cần cho giám sát
public class getInfor {

    private static String user = "Admin";
    private static String password = "zabbix";
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
            String Token = Item_get.getInstance().authenticate(user, password);
            System.out.println("Authenticated with token: " + Token);
            
            String host ="10641";

//            //Name
//        	System.out.print("Name: " + getInfor.getInstance().getName(host) + "\n");
//        	System.out.print("IP: " + getInstance().getIP(host) + "\n");
        	
            while(true) {
            	
            	Device demo = getInstance().getFull_Infor(host, Token);
            	demo.Display();
            	            	
//	            // Lấy CPU load cho một host cụ thể (thay hostId bằng ID host thực tế)
//	            System.out.print("\nCPU :" + getInfor.getInstance().getCpuLoad(host) + "\n");
//	
//	            // Lấy thông tin băng thông mạng
//	            System.out.print("Receive: " + getInfor.getInstance().getNetworkTraffic_recei(host) + "\n");
//	            System.out.print("Send: " + getInfor.getInstance().getNetworkTraffic_send(host) + "\n");
//	            
//	            
//	            //Lấy thông tin RAM
//	            System.out.print("RAM: " + getInfor.getInstance().getRAMInfo_total(host) + "\n");
//	            System.out.print("RAM-used: " + getInfor.getInstance().getRAMInfo_used(host) + "\n");
//	            System.out.print("RAM-util: " + getInfor.getInstance().getRAMInfo_util(host) + "\n");
//	            
//	            //Thời gian sử dụng
//	            System.out.print("Time(hardware): " + getInfor.getInstance().getTime_hardware(host) + "\n");
//	            System.out.print("Time(network): " + getInfor.getInstance().getTime_network(host) + "\n");
//	            
//	            //Các ổ đĩa
//	            List<Disk> disk = getInstance().getDiskInfo(host);
//	            for (Disk diskInfo : disk) {
//	                System.out.println("Disk name: " + diskInfo.name);
//	                System.out.println("Last Value: " + diskInfo.lastValue);
//	            }
//	            
//	            //Lấy trạng thái kết nối
//	            System.out.print("SNMP :" + getInstance().getSNMP(host) + "\n");
	            
	            TimeUnit.SECONDS.sleep(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //Cài đặt token để lấy API
    public void setToken(String token) {
    	authToken = token;
    }
    
    //Hàm lấy toàn bộ thông tin của một hostid đê show lastvalue
    public Device getFull_Infor(String hostID, String token) {
    	setToken(token);
    	String id = hostID;
    	
    	//Khởi tạo các biến thông tin
		String name = null;
		Item CPUload = null;
		String IP = null;
		Item SNMP = null;
		Item bitReceive = null;
		Item bitSend = null;
		Item RAM_total = null;
		Item RAM_used = null;
		Item RAM_util = null;
		Item Time_hw = null;
		Item Time_net = null;
		List<Disk> ListDisk = null;
		
		//Gửi API lấy thông tin
		try {
			name = getName(id);
			CPUload = getCpuLoad(id);
			IP = getIP(id);
			SNMP = getSNMP(id);
    		bitReceive = getNetworkTraffic_recei(id);
    		bitSend = getNetworkTraffic_send(id);
    		RAM_total = getRAMInfo_total(id);
    		RAM_used = getRAMInfo_used(id);
    		RAM_util = getRAMInfo_util(id);
    		Time_hw = getTime_hardware(id);
    		Time_net = getTime_network(id);
    		ListDisk = getDiskInfo(id);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//Trả về thiết bị với các thông tin cần
		return new Device( name, id, IP, SNMP, CPUload, bitReceive, bitSend, RAM_total, RAM_used, RAM_util, Time_hw, Time_net, ListDisk);
    }
    
    //Ham lấy IP
    public String getIP(String Hostid) throws Exception {
    	String IP = "";
    	//Lấy dữ liệu
    	JSONArray resultArray = Item_get.getInstance().getJSONresponse(Hostid, "hostinterface.get", "" , authToken);
    	
    	// Duyệt qua từng phần tử trong mảng result
    	for (int i = 0; i < resultArray.length(); i++) {
    	    JSONObject resultObject = resultArray.getJSONObject(i);
    	    // Lấy giá trị của trường 'ip'
    	    IP = resultObject.optString("ip", ""); // Sử dụng optString để tránh NullPointerException nếu không có trường "ip"
    	}
    	
    	return IP;
    }
    
    //Lấy trạng thái kêt nối SNMP
    public Item getSNMP(String hostid) {
    	Item result = null;
    	try {
			result = Item_get.getInstance().getInfor(hostid, "zabbix[host,snmp,available]", authToken);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    	
    }
        
    // Lấy thông tin ổ đĩa
    public List<Disk> getDiskInfo(String hostId) throws Exception {
        return Item_get.getInstance().getDiskInfo(hostId, authToken);
    }
    
    //Lấy tên trên thiết bị
    public String getName(String hostId) throws IOException {
    	if(hostId.equals("10084")) return Item_get.getInstance().getInfor(hostId, "system.hostname", authToken).getValue();
    	return Item_get.getInstance().getInfor(hostId, "system.name", authToken).getValue();
    }
    
    
  //Thời gian sử dụng mang
    public Item getTime_network(String hostId) throws Exception {
    	Item uptimeInSeconds = Item_get.getInstance().getInfor(hostId,"system.net.uptime" , authToken);
    	long seconds = Long.parseLong(uptimeInSeconds.getValue());

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;
        
        String Time = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        Item rs = new Item(uptimeInSeconds.getId(), Time);
        return rs;
    }
    
    //Thời gian sử dụng máy
    public Item getTime_hardware(String hostId) throws Exception {
    	Item uptimeInSeconds = Item_get.getInstance().getInfor(hostId,"system.hw.uptime" , authToken);
    	long seconds = Long.parseLong(uptimeInSeconds.getValue());

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        String Time = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        Item rs = new Item(uptimeInSeconds.getId(), Time);
        return rs;
    }

    // Lệnh lấy % CPU load
    public Item getCpuLoad(String hostId) throws IOException {
    	Item rs = Item_get.getInstance().getInfor(hostId,"system.cpu.util" , authToken);
    	
    	return rs;
        
    }
    
 // Lấy tốc độ bit mạng nhận
    public Item getNetworkTraffic_recei(String hostId) throws IOException {
    	Item rs = Item_get.getInstance().getInfor(hostId,"net.if.in" , authToken);
    	return rs;
    	
    }

 // Lấy thông tin RAM tối đa
    public Item getRAMInfo_total(String hostId) throws IOException {
    	//Tạo key
    	String key = "vm.memory.walk.data.total";
    	if(hostId.equals("10084")) key = "vm.memory.size[total]";
    	
    	Item ramValue = Item_get.getInstance().getInfor(hostId, key , authToken);
    	
        // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue.getValue()) / (1024 * 1024 * 1024);
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        String value = String.format("%.2f GB", ramInGB);
        Item rs = new Item(ramValue.getId(), value);
        return rs;
        
    }
    
 // Lấy thông tin RAM đã sử dụng
    public Item getRAMInfo_used(String hostId) throws IOException {
    	String key = "vm.memory.used";
    	if(hostId.equals("10084")) key = "vm.memory.size[available]";
    	
    	Item ramValue = Item_get.getInstance().getInfor(hostId, key , authToken);
    	
        // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue.getValue()) / (1024 * 1024 * 1024);
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        String value = String.format("%.2f GB", ramInGB);
        Item rs = new Item(ramValue.getId(), value);
        return rs;
    }
    
 // Lấy thông tin RAM % đang sử dụng
    public Item getRAMInfo_util(String hostId) throws IOException {
    	Item ramValue = Item_get.getInstance().getInfor(hostId, "vm.memory.util", authToken);
    	 // Chuyển đổi byte sang GB
        double ramInGB = Double.parseDouble(ramValue.getValue());
        
        // Định dạng thành chuỗi với 2 chữ số thập phân
        String value = String.format("%.2f%%", ramInGB);
        
        Item rs = new Item(ramValue.getId(), value);
        return rs;
    }
    
 // Lấy tốc độ bit mạng gửi
    public Item getNetworkTraffic_send(String hostId) throws IOException {
    	return Item_get.getInstance().getInfor(hostId,"net.if.out" , authToken);
    }


}

    
