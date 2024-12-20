package controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class getListHostGroup
 */
public class getListHostGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getListHostGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authToken = (String)request.getSession().getAttribute("token");
		String grouphost=request.getParameter("groupHost");
		List<Host_Group> list=new ArrayList<>();
		if(grouphost==null || grouphost.isEmpty()) {
		list= Host_group_CRUD.getInstance().Get_Groups(authToken);
		}else {
			List<Host_Group> groups=Host_group_CRUD.getInstance().Get_Groups(authToken);
			for(int i=0;i<groups.size();i++) {
				if(groups.get(i).getName().contains(grouphost)) {
					list.add(groups.get(i));
				}
			}
		}
		try {
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.getRequestDispatcher("listHostGroup.jsp").forward(request, response);
			}
		}catch(Exception e) {
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
