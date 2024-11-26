package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host;

/**
 * Servlet implementation class deleteDevice
 */
public class deleteHost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteHost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authToken = Item_get.getInstance().authenticate("Admin", "zabbix");
		String hostid=request.getParameter("id");
		List<Host> list=Host_CRUD.getInstance().getHosts(authToken);
		Host host = null;
        for(int i=0;i<list.size();i++) {
        	if(list.get(i).getHostid().equals(hostid)) {
        		host=list.get(i);
        		break;
        	}
        }
        if(host.getHostid()!=null) {
        	String rs = Host_CRUD.getInstance().Delete_Host(host, authToken);
        	System.out.print(rs);
        	response.sendRedirect("getthongtin");
        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
