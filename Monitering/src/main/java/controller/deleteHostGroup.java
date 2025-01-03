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
import Model.Host_Group;

/**
 * Servlet implementation class deleteHostGroup
 */
public class deleteHostGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteHostGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupid=request.getParameter("groupid");
		String token=(String)request.getSession().getAttribute("token");
		List<Host> hosts=Host_CRUD.getInstance().getHosts(token); 
		boolean check=true;
		for(int i=0;i<hosts.size();i++) {
			if(hosts.get(i).getGroupid().equals(groupid)) {
				check=false;
				break;
			}
		}
		if(check==true) {
			Host_group_CRUD.getInstance().Delete_Group(groupid, token);
			request.getRequestDispatcher("check?action=listgrouphost").forward(request, response);
		}else {
			request.setAttribute("error", "Trong Group có dữ liệu không thể xóa");
			request.getRequestDispatcher("check?action=listgrouphost").forward(request, response);
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
