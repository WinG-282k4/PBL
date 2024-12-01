package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.parsing.Problem;

import DLL.ZabbixAPI.Event;

/**
 * Servlet implementation class problemhostid
 */
@WebServlet("/problemhostid")
public class problemhostid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public problemhostid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostid=request.getParameter("hostid");
		String token=(String)request.getSession().getAttribute("token");
		List<Model.Problem> problems=new ArrayList<Model.Problem>();
		try {
			problems=Event.getInstance().getProblems1Host(token, hostid);
			request.setAttribute("list", problems);
			request.getRequestDispatcher("Problem.jsp").forward(request, response);
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
