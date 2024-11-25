package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_group_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host;
import Model.Host_Group;

/**
 * Servlet implementation class updateHostGroup
 */
public class updateHostGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateHostGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupID=request.getParameter("groupid");
		String authToken = Item_get.getInstance().authenticate("Admin", "zabbix");
		List<Host_Group> list=Host_group_CRUD.getInstance().Get_Groups(authToken);
		Host_Group host_Group=null;
		try {
			for(int i=0;i<list.size();i++) {
				if(groupID.equals(list.get(i).getId())) {
					host_Group=list.get(i);
				}
			}
			if(host_Group!=null) {
				request.setAttribute("data", host_Group);
				request.getRequestDispatcher("updateHostGroup.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authToken = Item_get.getInstance().authenticate("Admin", "zabbix");

		String groupID=request.getParameter("groupid");
		String groupname=request.getParameter("groupName");
		String[] hostIds = request.getParameterValues("hosts[].hostid");
        String[] hostNames = request.getParameterValues("hosts[].hostName");

        List<Host> hosts = new ArrayList<>();
        if (hostIds != null && hostNames != null) {
            for (int i = 0; i < hostIds.length; i++) {
                Host host = new Host(hostIds[i], hostNames[i]);
                hosts.add(host);
            }
        }
        try {
        	Host_Group host_group=new Host_Group(groupID, groupname, hosts);
        	Host_group_CRUD.getInstance().Update_Group(host_group, authToken);
        	response.sendRedirect("getListHostGroup");
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

}
