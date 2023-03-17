package com.user;

import org.json.simple.JSONArray;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoadMsg
 */
@WebServlet(name = "/api/msgs", urlPatterns = { "/api/msgs" })
public class LoadMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String reciever=request.getParameter("id");
//		if(reciever.equals("1")) {
//			doPost(request,response);
//		}
//		else {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-gePrintWriternerated method stub
		System.out.println("Correct");
		String reciever=request.getParameter("rec");
		int msgcount=Integer.parseInt(request.getParameter("count"));
		String url=request.getRequestURI();
//		StringTokenizer st=new StringTokenizer(url,"/");
//		String reciever=null;
		PrintWriter out=response.getWriter();
//		while(st.hasMoreTokens()) {
//			reciever=st.nextToken();
//		}
		HttpSession session=request.getSession();
		String sender=(String)session.getAttribute("name");
		com.user.chats.markAsRead(sender,reciever);
		JSONArray chats=com.user.chats.getChats(sender, reciever,msgcount);
		int i=0;
		System.out.println(chats);
		out.println(chats);
		out.close();
		
	}

}
