package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Event;
import DLL.ZabbixAPI.Host_CRUD;
import DLL.ZabbixAPI.Item_get;
import Model.Host;
import Model.Problem;

/**
 * Servlet implementation class dasdboard
 */
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String destination = "/dashboard";
		String token = Item_get.getToken();
		List<Host> listhost = Host_CRUD.getInstance().getHosts(token);
		List<Problem> listproblem = Event.getInstance().getProblems(token);
		request.setAttribute("listhost", listhost);
		request.setAttribute("listproblem", listproblem);
		
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
