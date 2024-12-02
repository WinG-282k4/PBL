package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson; // Thêm thư viện Gson để chuyển đổi đối tượng thành JSON

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import Model.Host;


@WebServlet("/searchHosts")
public class listHost1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public listHost1() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String authToken = (String) request.getSession().getAttribute("token");
            List<Host> hosts = Host_CRUD.getInstance().getHosts(authToken);

            // Lấy từ khóa tìm kiếm từ tham số
            String searchQuery = request.getParameter("search");
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                hosts = hosts.stream()
                        .filter(host -> host.getHostName().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Chuyển danh sách kết quả thành JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(hosts));

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing search request.");
        }
    }
}
