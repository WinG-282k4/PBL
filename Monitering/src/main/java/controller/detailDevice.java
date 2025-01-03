package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Item_get;
import DLL.ZabbixAPI.getInfor;
import Model.Device;
import Model.Host;

/**
 * Servlet implementation class detailDevice
 */
public class detailDevice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailDevice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostid=request.getParameter("ID");
        String authToken = (String)request.getSession().getAttribute("token");
		Device dv=getInfor.getInstance().getFull_Infor(hostid,authToken);
		Host host=Host_CRUD.getInstance().get1Hosts(authToken, hostid);
		if(dv!=null) {
			String name_host=host.getHostName();
			request.setAttribute("hostname", name_host);
			request.setAttribute("list", dv);
			request.getRequestDispatcher("detaildevice.jsp").forward(request, response);
			
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
