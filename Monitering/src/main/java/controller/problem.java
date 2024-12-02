package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Event;
import DLL.ZabbixAPI.Item_get;
import Model.Problem;

/**
 * Servlet implementation class problem
 */
public class problem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public problem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String destination = "/Problem.jsp";
		String proID = request.getParameter("proID");
		
		if(proID == null) {
			List<Problem> problem = Event.getInstance().getProblems((String)request.getSession().getAttribute("token"));
			request.setAttribute("list", problem);
		}else {
			int seversity =Integer.parseInt(request.getParameter("seversity"));
			String message = request.getParameter("message");
			String ackParam = request.getParameter("acknowledge");
		    boolean ack = "true".equalsIgnoreCase(ackParam);
		    String rs = Event.getInstance().updateProblem(Item_get.getToken(), proID, seversity, message, ack);
		    request.setAttribute("result", rs);			
		}
		
		forwardRequest(request, response, destination);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    // Phương thức chuyển tiếp đến trang đích
    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String destination) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            throw e; // Ném ngoại lệ để xử lý thêm (nếu cần)
        }
    }

}
