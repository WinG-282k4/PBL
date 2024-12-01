package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Item_get;
import DLL.ZabbixAPI.getInfor;
import Model.Device;
import Model.Disk;
import Model.Host;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getthongtin
 */
public class getthongtin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getthongtin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String authToken =(String)request.getSession().getAttribute("token");
			List<Host> hosts=Host_CRUD.getInstance().getHosts(authToken);
			request.setAttribute("list", hosts);
			request.getRequestDispatcher("listhost.jsp").forward(request, response);
			
		}catch(Exception e) {
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
	
//	  public static void main(String[] args) { try { String authToken =
//	  Item_get.getInstance().authenticate("Admin","zabbix"); 
//		List<Host> hosts=Host_CRUD.getInstance().getHosts(authToken);
//		List<Device> listDevice=new ArrayList<Device>();
//		for(int i=0;i<hosts.size();i++) {
////			if(hosts.get(i).getSNMP().equals("1")) {
//			Device dv=getInfor.getInstance().getFull_Infor(hosts.get(i).getHostid(),authToken);
//			dv.Display();
//			listDevice.add(dv);
////			}
//		}
//	  }catch(Exception e) { e.printStackTrace(); } 
//	  }
	 
}
