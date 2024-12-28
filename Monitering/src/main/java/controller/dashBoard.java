package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DLL.ZabbixAPI.Event;
import DLL.ZabbixAPI.Host_CRUD;
import Model.Host;
import Model.Problem;

/**
 * Servlet implementation class dashBoard
 */
@WebServlet("/dashBoard")
public class dashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String token = (String)request.getSession().getAttribute("token");
				List<Host> listhost = Host_CRUD.getInstance().getHosts(token);
				List<Problem> listproblem = Event.getInstance().getProblems(token);
				int rank0=0;
				int rank1=0;
				int rank2=0;
				int rank3=0;
				int rank4=0;
				int rank5=0;
				for(int i=0;i<listproblem.size();i++) {
					if(listproblem.get(i).getSeverity()==0) {
						rank0++;
					}else if(listproblem.get(i).getSeverity()==1) {
						rank1++;
					}else if(listproblem.get(i).getSeverity()==2) {
						rank2++;
					}else if(listproblem.get(i).getSeverity()==3) {
						rank3++;
					}else if(listproblem.get(i).getSeverity()==4) {
						rank4++;
					}else if(listproblem.get(i).getSeverity()==5) {
						rank5++;
					}
				}
				request.setAttribute("rank0", rank0);
				request.setAttribute("rank1", rank1);
				request.setAttribute("rank2", rank2);
				request.setAttribute("rank3", rank3);
				request.setAttribute("rank4", rank4);
				request.setAttribute("rank5", rank5);
				request.setAttribute("listhost", listhost);
				request.setAttribute("listproblem", listproblem);
				request.getRequestDispatcher("dashBoard.jsp").forward(request, response);
			}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
