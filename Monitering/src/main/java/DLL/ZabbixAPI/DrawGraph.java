package DLL.ZabbixAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Device;
import Model.Graph;
import Model.Host;

//Lớp xử lý lấy history của 1 item để view ra đồ thị
public class DrawGraph {

	//Tạo instance
	private static DrawGraph instance;
	private DrawGraph() {
		
	};
	public static DrawGraph getInstance() {
		if(instance == null) 
			instance = new DrawGraph();
		return instance;
	}
	
	static long timeFrom = System.currentTimeMillis() / 1000 - 3600; // 1 hour ago
    static long timeTill = System.currentTimeMillis() / 1000;        // Now
    
    public static void main(String[] args) {
		String token = Item_get.getInstance().authenticate("Admin", "zabbix");
		
		Map<String, List<Graph>> rs = getInstance().getHistoryHost(token, "10641", timeFrom, timeTill);
		
		// Lặp qua từng entry trong Map
		for (Map.Entry<String, List<Graph>> entry : rs.entrySet()) {
		    String itemId = entry.getKey();  // Lấy itemId
		    List<Graph> graphs = entry.getValue();  // Lấy danh sách Graphs tương ứng

		    System.out.println("\nItem ID: " + itemId);  // In ra itemId

		    // Lặp qua từng Graph trong danh sách
		    for (Graph graph : graphs) {
		        System.out.println("  Label: " + graph.getLabel() + ", Value: " + graph.getValue());
		    }
		}
		
//		List<Graph> lgr = getInstance().getHistoryItem(token, "48088", timeFrom, timeFrom);
//		for(Graph gr : lgr) {
//			System.out.println("  Label: " + gr.getLabel() + ", Value: " + gr.getValue());
//		}
	}
    
    
    
    
	
	//Hàm tạo graph với mảng Json đưa vào
	private JSONArray getHistoryData(String authToken, JSONArray itemIds, long timeFrom , long timeNow, int type) {

	    // Tạo params
	    JSONObject params = new JSONObject()
	            .put("output", "extend")
	            .put("history", type)
	            .put("itemids", itemIds) // JSONArray
	            .put("time_from", timeFrom)
	            .put("time_till", timeNow)
	            .put("sortfield", "clock")
	            .put("sortorder", "ASC")
	    		.put("limit", 20);

	    // Tạo request
	    JSONObject request = new JSONObject()
	            .put("jsonrpc", "2.0")
	            .put("method", "history.get")
	            .put("id", 1)
	            .put("auth", authToken) // Token xác thực
	            .put("params", params);

	    // Gửi request
	    JSONObject jsonResponse = null;
	    try {
	        jsonResponse = Item_get.getInstance().sendRequest(request);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    JSONArray rs = jsonResponse.getJSONArray("result");
	    
	    rs = jsonResponse.getJSONArray("result");

	    // Trả về phản hồi JSON ở dạng chuỗi
	    return rs;
	}
	
	//Hàm lấy môt list giá trị của 1 item
	public List<Graph> getHistoryItem(String authToken, String itemId, long timeFrom , long timeNow) {
		int type = 0;
		JSONArray itemid = new JSONArray().put(itemId);
		JSONArray rs = getHistoryData(authToken, itemid, timeFrom, timeNow, type);
		
		List<Graph> graphList = new ArrayList<Graph>();
		for(int i = 0; i < rs.length(); i ++ ) {
			JSONObject entry = rs.getJSONObject(i);

            // Lấy clock và chuyển thành định dạng thời gian
            long timestamp = Long.parseLong(entry.getString("clock"));
            String formattedTime = new SimpleDateFormat("HH:mm:ss").format(new Date(timestamp * 1000));

            // Lấy giá trị
            String value = entry.getString("value");

            // Tạo đối tượng Graph và thêm vào danh sách
            Graph graph = new Graph(formattedTime, value);
            graphList.add(graph);
		}
		
		if(graphList.isEmpty()) {
			
			type = 3;
			rs = getHistoryData(authToken, itemid, timeFrom, timeNow, type);
			
			graphList = new ArrayList<Graph>();
			for(int i = 0; i < rs.length(); i ++ ) {
				JSONObject entry = rs.getJSONObject(i);
	
	            // Lấy clock và chuyển thành định dạng thời gian
	            long timestamp = Long.parseLong(entry.getString("clock"));
	            String formattedTime = new SimpleDateFormat("HH:mm:ss").format(new Date(timestamp * 1000));
	
	            // Lấy giá trị
	            String value = entry.getString("value");

	            // Tạo đối tượng Graph và thêm vào danh sách
	            Graph graph = new Graph(formattedTime, value);
	            graphList.add(graph);
			}
		}
		
		return graphList;
	}
	
	//Lấy toàn bộ Graph của 1 host
	public Map<String, List<Graph>> getHistoryHost(String authToken, String hostId, long timeFrom, long timeNow) {
	    Map<String, List<Graph>> graphMap = new HashMap<>();

	    // Lấy thông tin host
	    Device host = getInfor.getInstance().getFull_Infor(hostId, authToken);

	    // Lấy lịch sử của các itemId (có thể phân loại theo kiểu dữ liệu)
	    List<Graph> resultsCPU = getHistoryItem(authToken, host.getCPU().getId(), timeFrom, timeNow);
	    List<Graph> resultsRAM = getHistoryItem(authToken, host.getRAM_util().getId(), timeFrom, timeNow);
	    List<Graph> resultsBitReceive = getHistoryItem(authToken, host.getBitReceive().getId(), timeFrom, timeNow);
	    List<Graph> resultsBitSend = getHistoryItem(authToken, host.getBitSend().getId(), timeFrom, timeNow);
	    List<Graph> resultsTimHard = getHistoryItem(authToken, host.getTime_hardware().getId(), timeFrom, timeNow);
	    List<Graph> resultsTimeNet = getHistoryItem(authToken, host.getTime_network().getId(), timeFrom, timeNow);

	    // Thêm kết quả vào map
	    graphMap.put("CPU_%", resultsCPU);
//	    graphMap.put(host.getCPU().getId(), resultsCPU);
	    graphMap.put("RAM_%", resultsRAM);
	    graphMap.put("Bit Reiceive_Kbps", resultsBitReceive);
	    graphMap.put("Bit Send_Kbps", resultsBitSend);
	    graphMap.put("Time Hardware_Second", resultsTimHard);
	    graphMap.put("Time Network_Second", resultsTimeNet);

	    return graphMap;
	}
	
	//Chuyển đổi thành map<String,String> cho dễ View
	public Map<String,String> getGraphhost(String token, String hostid,long timeFrom, long timeTill){
		Map<String, List<Graph>> listGraphs = DrawGraph.getInstance().getHistoryHost(token, hostid, timeFrom, timeTill);
        Map<String, String> graphData = new TreeMap<>();

        for (Map.Entry<String, List<Graph>> entry : listGraphs.entrySet()) {
            String itemID = entry.getKey();  // Lấy item ID(Name)
            List<Graph> graphs = entry.getValue();
            
//            //Nếu có đợn vị là thời gian
//            if(itemID.contains("HH:MM:SS")) {
//            	//duyệt qua từng phần tử
//            	for(Graph temp : graphs) {
//            		String value = temp.getValue();
//            		long seconds = Long.parseLong(value);
//
//            		// Tính toán số ngày, giờ, phút, giây
//            		long days = seconds / (24 * 3600); // 1 ngày = 86400 giây
//            		long hours = (seconds % (24 * 3600)) / 3600;
//            		long minutes = (seconds % 3600) / 60;
//            		long remainingSeconds = seconds % 60;
//
//            		// Định dạng lại giá trị thành ngày, giờ, phút, giây
//            		value = String.format("%d %02d:%02d:%02d", days, hours, minutes, remainingSeconds);
//
//            		temp.setValue(value);
//
//            	}
//            }

            // Lấy dữ liệu labels và values cho từng item
            String labels = graphs.stream()
                                  .map(g -> "\"" + g.getLabel() + "\"")
                                  .collect(Collectors.joining(", "));
            String data = graphs.stream()
                                .map(g -> String.valueOf(g.getValue()))
                                .collect(Collectors.joining(", "));

            // Lưu labels và data theo itemID
            graphData.put(itemID + "_labels", labels);
            graphData.put(itemID + "_data", data);
        }
        return graphData;
	}
	
	public Map<String,String> getGraphItem(String token, String itemid,long timeFrom, long timeTill){
		List<Graph> graph = DrawGraph.getInstance().getHistoryItem(token, itemid, timeFrom, timeTill);
        
        // Lấy dữ liệu labels và values cho từng item
        String labels = graph.stream()
                              .map(g -> "\"" + g.getLabel() + "\"")
                              .collect(Collectors.joining(", "));
        String data = graph.stream()
                            .map(g -> String.valueOf(g.getValue()))
                            .collect(Collectors.joining(", "));
        
        //Tạo kết quả gửi đi
        String units = null;
        String name = null;
        try {
        units = Item_get.getInstance().getItemUnits(token, itemid);
        name = Item_get.getInstance().getItemName(token, itemid);
        }catch (Exception e) {
			// TODO: handle exception
		}
        
        Map<String, String> graphData = new HashMap<>();
        graphData.put(name + "_" + units + "_labels", labels);
        graphData.put(name + "_" + units + "_data", data);
        
        return graphData;
	}

	
}
