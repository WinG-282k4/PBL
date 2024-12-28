package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.DrawGraph;
import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Disk;
import Model.Host;

@WebServlet("/C_Graph")
public class C_Graph extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thời gian (từ - đến) để lấy dữ liệu đồ thị
    static long timeFrom = System.currentTimeMillis() / 1000 - 3600; // 1 giờ trước
    static long timeTill = System.currentTimeMillis() / 1000;        // Bây giờ

	private List<Disk> diskgraph1;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "/graph2.jsp";

        // Lấy các tham số từ request
//      String hostid = "10642"; request.getParameter("hostid");
        String hostid = request.getParameter("ID");
        String itemid = request.getParameter("itemid");
        String disk = request.getParameter("Diskname");

        // Xác thực với Zabbix API
        String token =(String)request.getSession().getAttribute("token");
        
        
        Host host = Host_CRUD.getInstance().get1Hosts(token,hostid);
        request.setAttribute("host", host);

        // Kiểm tra và xử lý các tham số
        if (hostid != null && !hostid.isEmpty()) {
            // Lấy danh sách đồ thị cho từng item của hostid
            Map<String, String> graphData = DrawGraph.getInstance().getGraphhost(token, hostid, timeFrom, timeTill);
            List<Disk> diskgraph;
            List<Disk> diskgraph1 = new ArrayList<Disk>();
			try {
				diskgraph = Item_get.getInstance().getDiskInfo(hostid,token);
				for(int i=0;i<diskgraph.size();i++) {
					if(diskgraph.get(i).getName().contains("%")) {
						diskgraph1.add(diskgraph.get(i));
					}
				}
				request.setAttribute("GraphDisk", diskgraph1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // Truyền graphData sang JSP
            request.setAttribute("GraphData", graphData);
            
        } else if (itemid != null && !itemid.isEmpty()) {
        	
            Map<String, String> graphData = DrawGraph.getInstance().getGraphItem(token, itemid, timeFrom, timeTill);
            request.setAttribute("GraphData", graphData);
            
        } else if (disk != null && !disk.isEmpty()) {
        	
        	//String Value = request.getParameter("value");
        	//Disk diskgraph = new Disk(disk,Value);
        	List<Disk> diskgraph;
			try {
				diskgraph = Item_get.getInstance().getDiskInfo(hostid,token);
				for(int i=0;i<diskgraph.size();i++) {
					System.out.println(diskgraph.get(i).lastValue);
				}
				request.setAttribute("GraphDisk", diskgraph);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	// Khởi tạo đối tượng Disk với tên ổ đĩa và phần trăm đã sử dụng(test)
//        	Disk diskgraph = new Disk("Ổ C", "65");

        	// Đặt đối tượng vào request
        	
            
        } else {
//            request.setAttribute("ErrorMessage", "Không tìm thấy tham số phù hợp!");
        }

        // Chuyển tiếp đến trang đích
        forwardRequest(request, response, destination);
    }

    // Phương thức chuyển tiếp đến trang đích
    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String destination) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            throw e; // Ném ngoại lệ để xử lý thêm (nếu cần)
        }
    }
}
