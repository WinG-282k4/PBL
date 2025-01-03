package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Host_group_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host_Group;

/**
 * Servlet implementation class addHostGroup
 */
public class addHostGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addHostGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authToken = (String)request.getSession().getAttribute("token");
		String groupName=request.getParameter("groupName");
		List<Host_Group> list=Host_group_CRUD.getInstance().Get_Groups(authToken);
		boolean check=false;
		try {
			for(int i=0;i<list.size();i++) {
				if(groupName.equals(list.get(i).getName())) {
					check=true;
				}
			}
			if(check==false) {
				Host_group_CRUD.getInstance().Create_Group(groupName, authToken);
				response.sendRedirect("getListHostGroup");
				
			}
		}catch (Exception e) {
			e.printStackTrace();
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
