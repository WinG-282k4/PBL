package controller;

import java.io.IOException;

import java.util.List;

import DLL.ZabbixAPI.getInfor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
			String authToken = getInfor.getInstance().authenticate();
			String host="10084";
			getInfor.getInstance().getHosts(authToken);
			String nameDevice=getInfor.getInstance().getName(host);
			String CPU= getInfor.getInstance().getCpuLoad(host);
			
            // Lấy thông tin băng thông mạng
            String Receive= getInfor.getInstance().getNetworkTraffic_recei(host);
            String Send= getInfor.getInstance().getNetworkTraffic_send(host);
            
            
            //Lấy thông tin RAM
            String RAM=getInfor.getInstance().getRAMInfo_total(host);
            String RAM_used=  getInfor.getInstance().getRAMInfo_used(host);
            String RAM_util= getInfor.getInstance().getRAMInfo_util(host);
            
            //Thời gian sử dụng
            String Time_hardware= getInfor.getInstance().getTime_hardware(host);
            String Time_network= getInfor.getInstance().getTime_network(host);
            
            //Các ổ đĩa
            List<Disk> disk = getInfor.getInstance().getDiskInfo(host);
            
			devide dv=new devide(host,"hoa", nameDevice, RAM, CPU, Receive, Send, RAM, RAM_used, RAM_util, Time_hardware, disk);
			request.setAttribute("data", dv);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
