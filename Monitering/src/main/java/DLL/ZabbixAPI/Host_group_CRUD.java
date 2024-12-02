package DLL.ZabbixAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import Model.Host;
import Model.Host_Group;

public class Host_group_CRUD {
	
	//Tạo instance cho class
	private static Host_group_CRUD instance;
	private Host_group_CRUD() {
		
	}
	public static Host_group_CRUD getInstance() {
		if(instance == null) {
			instance = new Host_group_CRUD();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		String token = Item_get.getInstance().authenticate("Admin", "zabbix");
		String rs;
		
//		rs = getInstance().Create_Group("Test Group", token);
//		rs = getInstance().Update_Group(new Host_Group("24", "Test upate group", null), token);
//		rs = getInstance().Delete_Group("23", token);
//		System.out.print(rs );
		
		System.out.print("\n");
		
		List<Host_Group> lh = getInstance().Get_Groups(token);
		
		for(Host_Group group : lh){
			System.out.print("Id group: " + group.id() + "\n");
			System.out.print("Name group: " + group.name() + "\n");
			System.out.print("Danh sach host: \n");
			for(Host h1 : group.hosts()) {
				System.out.print("Host name: " + h1.name());
				System.out.print("\tHost id: " + h1.id());
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	//Hàm thêm group
	public String Create_Group(String groupName, String authToken) {
	    // Tạo JSON request
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "hostgroup.create")
	            .put("id", 1)
	            .put("auth", authToken) // Token xác thực
	            .put("params", new JSONObject()
	                .put("name", groupName) // Tên của group
	            );

	    // Gửi request và xử lý response
	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (IOException e) {
	               System.out.println(e);
	        return "Lỗi khi gửi request: " + e.getMessage();
	    }

	    try {
	        // Kiểm tra kết quả trả v�?
	        JSONObject result = jsonResponse.getJSONObject("result");
	        return "Thêm group thành công với ID: " + result.getJSONArray("groupids").getString(0);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Bắt ngoại lệ khi JSON trả v�? lỗi
	        return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
	    }
	}

	//Hàm update group
	public String Update_Group(Host_Group group, String authToken) {
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "hostgroup.update")
	            .put("id", 1)
	            .put("auth", authToken)
	            .put("params", new JSONObject()
	                .put("groupid", group.id()) // ID của group
	                .put("name", group.name()) // Tên mới của group
	            );

	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Lỗi khi gửi request: " + e.getMessage();
	    }

	    try {
	        JSONObject result = jsonResponse.getJSONObject("result");
	        return "Sửa group thành công";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
	    }
	}

	//Hàm xóa group
	public String Delete_Group(String groupId, String authToken) {
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "hostgroup.delete")
	            .put("id", 1)
	            .put("auth", authToken)
	            .put("params", new JSONArray()
	                .put(groupId) // ID của group cần xóa
	            );

	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Lỗi khi gửi request: " + e.getMessage();
	    }

	    try {
	        JSONObject result = jsonResponse.getJSONObject("result");
	        return "Xóa group thành công với ID: " + groupId;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Lỗi: " + jsonResponse.getJSONObject("error").optString("data");
	    }
	}

	// Hàm lấy danh sách các group và các host trong từng group
	public List<Host_Group> Get_Groups(String authToken) {
	    List<Host_Group> rs = new ArrayList<Host_Group>();

	    // Tạo JSON request
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "hostgroup.get")
	            .put("id", 1)
	            .put("auth", authToken) // Token xác thực
	            .put("params", new JSONObject()
	                .put("output", new JSONArray() // Lấy các trư�?ng cần thiết của group
	                    .put("groupid")
	                    .put("name")
	                )
	                .put("selectHosts", new JSONArray() // Lấy thông tin các host trong group
	                    .put("hostid")
	                    .put("host")
	                )
	            );

	    // Gửi request và xử lý response
	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return rs; // Trả v�? danh sách rỗng nếu lỗi xảy ra
	    }

	    try {
	        // Lấy kết quả từ JSON response
	        JSONArray jsonarr = jsonResponse.getJSONArray("result");
	        for (int i = 0; i < jsonarr.length(); i++) {
	            JSONObject groupObj = jsonarr.getJSONObject(i);
	            String id = groupObj.getString("groupid");
	            String name = groupObj.getString("name");

	            // Lấy danh sách các host trong group
	            List<Host> hosts = new ArrayList<Host>();
	            if (groupObj.has("hosts")) {
	                JSONArray hostsArray = groupObj.getJSONArray("hosts");
	                for (int j = 0; j < hostsArray.length(); j++) {
	                    JSONObject hostObj = hostsArray.getJSONObject(j);
	                    String hostId = hostObj.getString("hostid");
	                    
	                    //Kiem tra co phai giao thuc snmp ko
	                    JSONObject SNMP = Host_CRUD.getInstance().getConfig(hostId, authToken);
		            	if(SNMP == null && !hostId.equals("10084")) continue;
		            	
	                    String hostName = hostObj.getString("host");
	                    hosts.add(new Host(hostId, hostName));
	                }
	            }

	            // Thêm group vào danh sách
	            rs.add(new Host_Group(id, name, hosts));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rs;
	}

	//Hamf timd group theo ten
		List<Host_Group> searchHostGroup(String token, String name){
			List<Host_Group> rs = new ArrayList<Host_Group>();
	        List<Host_Group> groups = Get_Groups(token);
	        for(Host_Group group : groups) {
	            if(group.name().contains(name)) {
	                rs.add(group);
	            }
	        }
	        return rs;
		}
		//Tìm kiểm host thuộc host group
	    public List<Host> searchHostofHG(String token, String nameHG){
	    	List<Host> rs = new ArrayList<Host>();
	    	List<Host_Group> hostgroup = Host_group_CRUD.getInstance().Get_Groups(token);
			for (Host_Group hg : hostgroup) {
				if (hg.getName().contains(nameHG)) {
					rs.addAll( hg.getHosts());
				}
			}
	    	return rs;
	    }

}
