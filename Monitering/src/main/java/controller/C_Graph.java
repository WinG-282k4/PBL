package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.DrawGraph;
import DLL.ZabbixAPI.Item_get;
import Model.Graph;

@WebServlet("/C_Graph")
public class C_Graph extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thời gian (từ - đến) để lấy dữ liệu đồ thị
    static long timeFrom = System.currentTimeMillis() / 1000 - 3600; // 1 giờ trước
    static long timeTill = System.currentTimeMillis() / 1000;        // Bây giờ

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "/Graph.jsp";

        // Lấy các tham số từ request
        String hostid = "10641";// = request.getParameter("hostid");
        String itemid = request.getParameter("itemid");
        String disk = request.getParameter("Disk");

        // Xác thực với Zabbix API
        String token = Item_get.getInstance().authenticate("Admin", "zabbix");

        // Kiểm tra và xử lý các tham số
        if (hostid != null && !hostid.isEmpty()) {
            // Lấy danh sách đồ thị cho từng item của hostid
            Map<String, String> graphData = DrawGraph.getInstance().getGraphhost(token, hostid, timeFrom, timeTill);
            
            System.out.print(graphData);

            // Truyền graphData sang JSP
            request.setAttribute("GraphData", graphData);
            
        } else if (itemid != null && !itemid.isEmpty()) {
            List<Graph> graph = DrawGraph.getInstance().getHistoryItem(token, itemid, timeFrom, timeTill);
            
            // Lấy dữ liệu labels và values cho từng item
            String labels = graph.stream()
                                  .map(g -> "\"" + g.getLabel() + "\"")
                                  .collect(Collectors.joining(", "));
            String data = graph.stream()
                                .map(g -> String.valueOf(g.getValue()))
                                .collect(Collectors.joining(", "));
            
            //Tạo kết quả gửi đi
            Map<String, String> graphData = new HashMap<>();
            graphData.put(itemid + "_labels", labels);
            graphData.put(itemid + "_data", data);
            
            request.setAttribute("GraphData", graphData);
            
        } else if (disk != null && !disk.isEmpty()) {
            request.setAttribute("GraphDisk", disk);
        } else {
            request.setAttribute("ErrorMessage", "Không tìm thấy tham số phù hợp!");
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
