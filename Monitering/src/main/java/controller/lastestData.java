package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Host_group_CRUD;
import DLL.ZabbixAPI.getInfor;
import Model.Device;
import Model.Host;

/**
 * Servlet implementation class lastestData
 */
@WebServlet("/lastestData")
public class lastestData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lastestData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
            
            Host host1 = null;

            
            // Trường hợp cả hai đều có giá trị
             if (groupHost != null && !groupHost.isEmpty() && hostName != null && !hostName.isEmpty()) {
                List<Host> allHosts = Host_CRUD.getInstance().getHosts(authToken);
                for (Host host : allHosts) {
                	System.out.println(host.getHostName());
                    if (host.getGroupname().equals(groupHost) && host.getHostName().equals(hostName)) {
                    	System.out.println(host.getHostid());
                        host1=host;
                    }
                }
            // Trường hợp chỉ có Host
            }else if(hostName != null) {
                List<Host> allHosts = Host_CRUD.getInstance().getHosts(authToken);
                for (Host host : allHosts) {
                    if (host.getHostName().equals(hostName)) {
                    	System.out.println(host.getHostName());
                        host1=host;
                    }
                }
            }
             
             Device device=null;
             if(host1!=null) {
            	 device=getInfor.getInstance().getFull_Infor(host1.getHostid(), authToken);
             }
            
            // Gửi danh sách kết quả về JSP
            request.setAttribute("hostname", hostName);
            request.setAttribute("list", device);
            request.getRequestDispatcher("lastestData.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
