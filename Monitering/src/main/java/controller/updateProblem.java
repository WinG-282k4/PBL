package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Event;
import DLL.ZabbixAPI.Item_get;

/**
 * Servlet implementation class updateProblem
 */
@WebServlet("/updateProblem")
public class updateProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProblem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String hostID=(String)request.getAttribute("hostid");
		String proID=request.getParameter("eventid");
		int seversity =Integer.parseInt(request.getParameter("seversity"));
		String message = request.getParameter("message");
		String acknowledgeValue = request.getParameter("acknowledge");
		boolean ack = (acknowledgeValue != null && acknowledgeValue.equals("true"));
	    String rs = Event.getInstance().updateProblem((String)request.getSession().getAttribute("token"), proID, seversity, message, ack);
	    request.setAttribute("result", rs);	
	    if(hostID==null) {
	    request.getRequestDispatcher("check?action=problem").forward(request, response);
	    }else {
	    	String s="check?action=problemhostid&hostid="+hostID;
	    	request.getRequestDispatcher(s).forward(request, response);
	    }
	}
}
