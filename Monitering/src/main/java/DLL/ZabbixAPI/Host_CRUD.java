package DLL.ZabbixAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.protocol.RequestTargetHost;
import org.json.JSONArray;
import org.json.JSONObject;

import Model.Device;
import Model.Disk;
import Model.Host;

//Lớp thêm xóa sử các host, show thông tin tổng quát của host
public class Host_CRUD {
	//Tạo instance
    private static Host_CRUD instance;
    private Host_CRUD() {
		// TODO Auto-generated constructor stub
	}

    public static Host_CRUD getInstance() {
        if (instance == null) {
            instance = new Host_CRUD();
        }
        return instance;
    }
    
    public static void main(String[] args) {
    	//Laays token
		String token = Item_get.getInstance().authenticate("Admin", "zabbix");
		
		//Lấy danh sách device
		List<Host> hs = getInstance().getHosts(token);
		
		//in ra test
		for(Host H: hs) {
			H.Display();
			System.out.print("\n");
		}
	}
    
//    //Lấy toàn bộ thiết bị và thông tin
//    public List<Device> getDevice(String authToken) {
//    	
//    	List<Device> rs = new ArrayList<Device>();
//    	List<Host> hosts = getHosts(authToken);
//    	getInfor.getInstance().setToken(authToken);
//    	
//    	for(Host host:hosts) {
//    		String id = host.hostid;
//    		String hname = host.hostname;
//    		String name = null;
//    		String CPUload = null;
//    		String IP = null;
//    		String SNMP = null;
//    		String bitReceive = null;
//    		String bitSend = null;
//    		String RAM_total = null;
//    		String RAM_used = null;
//    		String RAM_util = null;
//    		String Time_hw = null;
//    		String Time_net = null;
//    		List<Disk> ListDisk = null;
//    		try {
//    			name = getInfor.getInstance().getName(id);
//    			CPUload = getInfor.getInstance().getCpuLoad(id);
//    			IP = getInfor.getInstance().getIP(id);
//    			SNMP = getInfor.getInstance().getSNMP(id);
//        		bitReceive = getInfor.getInstance().getNetworkTraffic_recei(id);
//        		bitSend = getInfor.getInstance().getNetworkTraffic_send(id);
//        		RAM_total = getInfor.getInstance().getRAMInfo_total(id);
//        		RAM_used = getInfor.getInstance().getRAMInfo_used(id);
//        		RAM_util = getInfor.getInstance().getRAMInfo_util(id);
//        		Time_hw = getInfor.getInstance().getTime_hardware(id);
//        		Time_net = getInfor.getInstance().getTime_network(id);
//        		ListDisk = getInfor.getInstance().getDiskInfo(id);
//    		}catch (Exception e) {
//				// TODO: handle exception
//			}
//    		rs.add(new Device( name, id, IP, SNMP, CPUload, bitReceive, bitSend, RAM_total, RAM_used, RAM_util, Time_hw, Time_net, ListDisk));
//    	}
//		return rs;
//    }
    
    // Lấy thông tin các host hiện có
    public List<Host> getHosts(String authToken)  {

		try {
			URL url = new URL(Item_get.getInstance().getURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        List<Host> rs = new ArrayList<Host>() ;
        // Tạo yêu cầu JSON để lấy thông tin host
        JSONObject hostRequest = new JSONObject()
            .put("jsonrpc", "2.0")
            .put("method", "host.get")
            .put("params", new JSONObject()
                .put("output", new JSONArray(Arrays.asList("hostid", "host"))
                		)
            		)
            .put("auth", authToken)
            .put("id", 1);

        JSONObject jsonResponse = null;
		try {
			jsonResponse = Item_get.getInstance().sendRequest(hostRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        
        //Lấy thông tin và tạo host
        JSONArray jsonarr = jsonResponse.getJSONArray("result");
        
        //Lập qua từng phàn tử để lấy thông tin cần thiết
        for(int i = 0; i < jsonarr.length(); i ++) {
        	JSONObject jsonob = jsonarr.getJSONObject(i);
        	String hostid = jsonob.optString("hostid", "");
        	String hostname = jsonob.optString("host", "");
        	
        	//Lấy thông tin SNMP
        	JSONObject SNMP = getConfig(hostid, authToken);
        	if(SNMP == null) continue;
        	
        	//Xử lý kết quả\
        	String IP = SNMP.optString("ip", "0");
        	String available =SNMP.optString("available", "0"); //Trạng thái kết nối
        	//Lấy detail : "details":{"bulk":"1","community":"password","version":"2","max_repetitions":"10"}
	        JSONObject detail = SNMP.getJSONObject("details");
	        String community = detail.optString("community", "N/A");
	        String version = detail.optString("version", "1");
	        rs.add(new Host(hostname, hostid, IP, available, community, version));
        }
        
        return rs;
    }
    
    //Lấy thông tin cấu hình của 1 host
    public JSONObject getConfig(String hostid, String authToken) {
    	//Tạo yêu cầu để lấy thông tin IP, community, avable của host
		JSONObject request = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("method", "hostinterface.get")
                .put("params", new JSONObject()
                    .put("output", new JSONArray()
                        .put("ip")
                        .put("available")
                        .put("details")
                    )
                    .put("hostids", hostid)
                    .put("filter", new JSONObject()
                            .put("type", 2) // Lọc chỉ lấy các interface kiểu 'agent', type=2 là SNMP
                        )
                )
                .put("id", 1)
                .put("auth", authToken);
		
		JSONObject jsonResponseInterface = null;
		try {
			jsonResponseInterface = Item_get.getInstance().sendRequest(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(jsonResponseInterface + "\t");
		if(jsonResponseInterface != null && jsonResponseInterface.has("result") && jsonResponseInterface.getJSONArray("result").length() > 0)
			return jsonResponseInterface.getJSONArray("result").getJSONObject(0);
		else return null;
    }
}

