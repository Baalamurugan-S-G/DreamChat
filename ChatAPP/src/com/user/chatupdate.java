package com.user;

import com.user.chats;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class chatupdate
 */
@WebServlet(name = "/api/newmsg", urlPatterns = { "/api/newmsg" })
public class chatupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chatupdate() {
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
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String sender=(String)session.getAttribute("name");
		String contact=(String)request.getParameter("rec");
		String txt=(String)request.getParameter("msg");
		LocalDateTime now = LocalDateTime.now(); 
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("hh:mm a");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String time=dtf1.format(now);
		String date=dtf2.format(now);
		System.out.println("updated");  
		int updateflag=chats.updatechat(sender,contact,txt);
		int notifyflag=chats.updateState(contact);
		if(updateflag==1 && notifyflag==1)
			out.println("[{\"date\":\""+date+"\",\"msg\":\""+txt+"\",\"time\":\""+time+"\",\"type\":\"sender\"}]");
//			out.println("<div class='mes'><ul class='rm'><li class='msg'>"+txt+"</li><li class='time'>"+dtf1.format(now)+"</li></ul>");
		out.close();
	}

}
