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
		
		Host temp = new Host("Thanh_Laptop", "10639", "22", "  ", "192.168.0.145", " ", "password", "2", "Test tạo host 1");
//		getInstance().getTemplates(token, Item_get.getInstance().getURL());
//		Tạo host
//		String Create_host = getInstance().Create_Host(temp, token);
//		System.out.print(Create_host);
		
		//Update 1 host
//		getInstance().Update_Host(temp, token);
	
		//Test xóa host
		String rs = getInstance().Delete_Host(temp, token);
		System.out.print(rs);
		
		//Lấy danh sách device
		List<Host> hs = getInstance().getHosts(token);
		System.out.print("\n");
		
		//in ra test
		for(Host H: hs) {
			H.Display();
			System.out.print("\n");
		}
	}
    
    //Hàm xoa một host
    public String Delete_Host(Host host, String authtoken) {
    	//Tạo json
    	  JSONObject request = new JSONObject()
    	            .put("jsonrpc", "2.0")
    	            .put("method", "host.delete")
    	            .put("id", 1)
    	            .put("auth", authtoken) // Token xác thực
    	            .put("params", new JSONObject()
    	            	.put("hostid", Integer.parseInt(host.id()))
    	            );
    	  
    	//Gửi request
    	 JSONObject jsonResponse = null;
  		try {
  			jsonResponse = Item_get.getInstance().sendRequest(request);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		try {
  	  		JSONObject result =  jsonResponse.getJSONObject("result");
  	  		return "Xóa host thành công";  
  		}catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  			//Bắt ngoại lệ khi json trả về lỗi
    		return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
  		}    	
    }
    
    //Update host
    public String Update_Host(Host UHost, String authToken) {
    	//Đổi interface trước khi cập nhập host (Nếu ko sẽ bị xung đột)
    	UpdateInterface(UHost, authToken);
    	
    	//Tạo json
  	  JSONObject request = new JSONObject()
  	            .put("jsonrpc", "2.0")
  	            .put("method", "host.update")
  	            .put("id", 1)
  	            .put("auth", authToken) // Thay YOUR_AUTH_TOKEN bằng token thực tế
  	            .put("params", new JSONObject()
  	            	.put("hostid", Integer.parseInt(UHost.id()))
  	                .put("host", UHost.name())
  	                .put("groups", new JSONArray()
  	                    .put(new JSONObject()
  	                        .put("groupid", UHost.groupid()) 
  	                    )
  	                )
  	                .put("description", UHost.descript())
  	                .put(authToken, false)
  	                .put("templates", new JSONArray()
  	                    .put(new JSONObject()
  	                        .put("templateid", "10249") 
  	                    )
  	                )
  	            );
  	  
  	//Gửi request
  	 JSONObject jsonResponse = null;
		try {
			jsonResponse = Item_get.getInstance().sendRequest(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
	  		JSONObject result =  jsonResponse.getJSONObject("result");
	  		return "Sửa host thành công";  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//Bắt ngoại lệ khi json trả về lỗi
  			return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
		}    	
    }
    
    //Cập nhập interface cho host (Dùng trong hàm Update_host
    public void UpdateInterface(Host host, String authToken) {
    	//Lấy interfaceid
    	String interfaceid = getHostInterface(host.id(), authToken);
    	
    	//Cập nhập nó
    	JSONObject addInterfaceRequest = new JSONObject()
    		    .put("jsonrpc", "2.0")
    		    .put("method", "hostinterface.update")
    		    .put("params", new JSONObject()
    		        .put("interfaceid", interfaceid)
    		        .put("type", 2) // SNMP
    		        .put("main", 1)
    		        .put("useip", 1)
    		        .put("ip", host.IP()) // Địa chỉ IP mới
    		        .put("dns", "")
    		        .put("port", "161")
    		        .put("details", new JSONObject()
    		            .put("version", 2)
    		            .put("community", host.community())
    		        )
    		    )
    		    .put("auth", authToken)
    		    .put("id", 1);

    		// Gửi yêu cầu thêm giao diện
    		try {
    		    JSONObject response = Item_get.getInstance().sendRequest(addInterfaceRequest);
    		    System.out.println("Đã thêm giao diện mới: " + response);
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}

    }
    
    //Láy interfaceid của 1 host
    public String getHostInterface(String hostId, String authToken) {
        try {
            JSONObject request = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("method", "hostinterface.get")
                .put("id", 1)
                .put("auth", authToken)
                .put("params", new JSONObject()
                    .put("hostids", hostId)
                );

            JSONObject response = Item_get.getInstance().sendRequest(request);

            if (response.has("result")) {
                JSONArray interfaces = response.getJSONArray("result");
                for (int i = 0; i < interfaces.length(); i++) {
                    JSONObject iface = interfaces.getJSONObject(i);
                    System.out.println("Interface ID: " + iface.getString("interfaceid"));
                    return iface.getString("interfaceid"); // Lấy ID của giao diện đầu tiên
                }
            } else {
                System.out.println("Lỗi khi lấy danh sách interface: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Lấy tất cả thông tin Item của host để kt
    public JSONArray getHostItems(String hostId, String authToken) {
        JSONObject request = new JSONObject()
            .put("jsonrpc", "2.0")
            .put("method", "item.get")
            .put("params", new JSONObject()
                .put("hostids", hostId)
                .put("output", "extend") // Lấy tất cả các thông tin item
            )
            .put("auth", authToken)
            .put("id", 1);

        try {
            JSONObject response = Item_get.getInstance().sendRequest(request);
            System.out.print(response.toString());
            return response.getJSONArray("result");
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    //Thêm host 
    public String Create_Host(Host C_host, String authToken ) {
    	
    	//Tạo json
    	  JSONObject request = new JSONObject()
    	            .put("jsonrpc", "2.0")
    	            .put("method", "host.create")
    	            .put("id", 1)
    	            .put("auth", authToken) // Thay YOUR_AUTH_TOKEN bằng token thực tế
    	            .put("params", new JSONObject()
    	                .put("host", C_host.name())
    	                .put("interfaces", new JSONArray()
    	                    .put(new JSONObject()
    	                        .put("type", 2)
    	                        .put("main", 1)
    	                        .put("useip", 1)
    	                        .put("ip", C_host.IP())
    	                        .put("dns", "")
    	                        .put("port", "161")
    	                        .put("details", new JSONObject()
    	                            .put("version", C_host.version())
    	                            .put("community", C_host.community())
    	                        )
    	                    )
    	                )
    	                .put("description", C_host.descript())
    	                .put("groups", new JSONArray()
    	                    .put(new JSONObject()
    	                        .put("groupid", C_host.groupid()) // Thay groupid thực tế nếu cần
    	                    )
    	                )
    	                .put("templates", new JSONArray()
    	                    .put(new JSONObject()
    	                        .put("templateid", "10249") // Thay templateid thực tế nếu cần
    	                    )
    	                )
    	            );
    	  
    	//Gửi request
    	 JSONObject jsonResponse = null;
  		try {
  			jsonResponse = Item_get.getInstance().sendRequest(request);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		try {
	  		JSONObject result =  jsonResponse.getJSONObject("result");

	  		return "Thêm host thành công";  
  		}catch (Exception e) {
			// TODO: handle exception
  			//Bắt ngoại lệ khi json trả về lỗi
  			return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
		}
    }
    
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
        	        .put("output", new JSONArray().put("hostid").put("host").put("description")) // Danh sách trường cần trả về
        	        .put("selectHostGroups", new JSONArray().put("groupid").put("name")) // Thông tin nhóm cần lấy
        	    )
        	    .put("auth", authToken) // Token xác thực
        	    .put("id", 1); // ID của request


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
        	String decript = jsonob.optString("description", "");
        	
        	JSONArray jarr = jsonob.getJSONArray("hostgroups");
        	String groupname = jarr.getJSONObject(0).getString("name");
        	String groupid = jarr.getJSONObject(0).getString("groupid");
        	
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
	        
	        rs.add(new Host(hostname, hostid, groupid, groupname , IP, available, community, version, decript));
        }
        
        return rs;
    }
    
    //Lấy thông tin cấu hình SNMP của 1 host
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
		
		//Kiểm tra và trả về
		if(jsonResponseInterface != null && jsonResponseInterface.has("result") && jsonResponseInterface.getJSONArray("result").length() > 0)
			return jsonResponseInterface.getJSONArray("result").getJSONObject(0);
		else return null;
    }
}

