package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Host_group_CRUD;
import Model.Host;

public class getthongtin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getthongtin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy token từ session
            String authToken = (String) request.getSession().getAttribute("token");

            if (authToken == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Lấy giá trị từ form
            String groupHost = request.getParameter("groupHost");
            String hostName = request.getParameter("hostName");

            List<Host> hosts = new ArrayList<>();

            // Trường hợp cả hai đều null (Không có từ khóa tìm kiếm)
            if ((groupHost == null || groupHost.isEmpty()) && (hostName == null || hostName.isEmpty())) {
                hosts = Host_CRUD.getInstance().getHosts(authToken);
            } 
            // Trường hợp chỉ có groupHost có giá trị
            else if (groupHost != null && !groupHost.isEmpty() && (hostName == null || hostName.isEmpty())) {
                hosts = Host_group_CRUD.getInstance().searchHostofHG(authToken, groupHost);
            } 
            // Trường hợp chỉ có hostName có giá trị
            else if ((groupHost == null || groupHost.isEmpty()) && hostName != null && !hostName.isEmpty()) {
                List<Host> allHosts = Host_CRUD.getInstance().getHosts(authToken);
                for (Host host : allHosts) {
                    if (host.getHostName().contains(hostName)) {
                        hosts.add(host);
                    }
                }
            } 
            // Trường hợp cả hai đều có giá trị
            else if (groupHost != null && !groupHost.isEmpty() && hostName != null && !hostName.isEmpty()) {
                List<Host> allHosts = Host_CRUD.getInstance().getHosts(authToken);
                for (Host host : allHosts) {
                    if (host.getGroupname().contains(groupHost) && host.getHostName().contains(hostName)) {
                        hosts.add(host);
                    }
                }
            }

            // Gửi danh sách kết quả về JSP
            request.setAttribute("list", hosts);
            request.getRequestDispatcher("listhost.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
