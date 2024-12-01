package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Host_group_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host;
import Model.Host_Group;
/**
 * Servlet implementation class addDevice
 */
public class addHost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addHost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authToken = Item_get.getInstance().authenticate("Admin","zabbix");
		List<Host_Group> groups=Host_group_CRUD.getInstance().Get_Groups(authToken);
		request.setAttribute("list", groups);
		request.getRequestDispatcher("addHost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authToken = (String)request.getSession().getAttribute("token");
		String hostName=request.getParameter("hostName");
		String hostid=null;
		String hostIP=request.getParameter("hostIP");
		String Groupid=request.getParameter("Groupid");
		String Groupname=request.getParameter("Groupname");
		String SNMP=null;
		String SNMP_community=request.getParameter("SNMP_community");
		String SNMP_version=request.getParameter("SNMP_version");
		String Description=request.getParameter("Description");
		try {
			Host host=new Host(hostName, hostid, Groupid, Groupname, hostIP, SNMP, SNMP_community, SNMP_version, Description);
			Host_CRUD.getInstance().Create_Host(host, authToken);
			request.getRequestDispatcher("getthongtin").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
