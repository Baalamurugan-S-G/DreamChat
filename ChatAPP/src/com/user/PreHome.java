package com.user;

import com.user.Messages;
import com.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PreHome
 */
@WebServlet(name = "/login", urlPatterns = { "/login" })
public class PreHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		HttpSession session =request.getSession();
		String user=(String)session.getAttribute("name");
		Map<String,Messages> users=User.getChats(user);
//		for(Map.Entry<String,Messages> contact:users.entrySet()){
////			out.println("<a href='#'>III</a><br>");
//			out.println("<a id="+contact.getKey()+"><ul class='info'>");
//			Messages msg=(Messages)contact.getValue();
//			out.println("<li class='name'>"+contact.getKey()+"</li>");
//			out.println("<li class='msg'><table><tr><td id='m'>"+msg.getMessage()+"</td>");
//			out.println("<td id='d' >"+msg.getTime()+"</td></tr></table></li></ul></a>");
//		}
		session.setAttribute("Chats", users);
//		users=(HashMap<String,Messages>)session.getAttribute("Chats");
//		for(Map.Entry<String,Messages> m:users.entrySet()) {
			System.out.println("PreHome"+users);
//		}
			request.getRequestDispatcher("Home.jsp").include(request, response);
	}

}
