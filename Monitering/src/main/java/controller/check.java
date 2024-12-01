package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Host_group_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host;
import Model.Host_Group;

/**
 * Servlet implementation class check
 */
@WebServlet("/check")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String token=(String)session.getAttribute("token");
		if(token.contains("error")) {
			request.setAttribute("token", token);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			String action=request.getParameter("action");
			if(action.equals("addhost")) {
				request.getRequestDispatcher("addHost").forward(request, response);
			}else if(action.equals("deletehost")) {
				request.getRequestDispatcher("deleteHost").forward(request, response);
			}else if(action.equals("updatehost")) {
				String authToken = token;
				String hostid=request.getParameter("hostid");
				List<Host_Group> groups=Host_group_CRUD.getInstance().Get_Groups(authToken);
				try {
					Host host=null;
					List<Host> list=Host_CRUD.getInstance().getHosts(authToken);
					for(int i=0;i<list.size();i++) {
						if(hostid.equals(list.get(i).getHostid())) {
							host=list.get(i);
						}
					}
					if(host!=null) {
						request.setAttribute("list", groups);
						request.setAttribute("host", host);
						request.getRequestDispatcher("updateHost.jsp").forward(request, response);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else if(action.equals("listhost")) {
				response.sendRedirect("getthongtin");
			}else if(action.equals("addhostgroup")){
				 request.getRequestDispatcher("addHostGroup.jsp").forward(request, response);
			}else if(action.equals("deletehostgroup")) {
				request.getRequestDispatcher("deleteHostGroup").forward(request, response);
			}else if(action.equals("updatehostgroup")) {
				String groupID=request.getParameter("groupid");
				String authToken = token;
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
			}else if(action.equals("listgrouphost")) {
				request.getRequestDispatcher("getListHostGroup").forward(request, response);
			}else if(action.equals("detailDevice")) {
				request.getRequestDispatcher("controller.C_Graph").forward(request, response);
			}else if(action.equals("problemhostid")) {
				request.getRequestDispatcher("problemhostid").forward(request, response);
			}else if(action.equals("problem")) {
				request.getRequestDispatcher("problem").forward(request, response);
			}else if(action.equals("updateproblem")) {
				String proID=request.getParameter("eventid");
				
			}
			else {
				response.sendRedirect("login.jsp");
			}
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
