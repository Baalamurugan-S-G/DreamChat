package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class RecieveMessage
 */
@WebServlet("/api/rec_msgs")
public class RecieveMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecieveMessage() {
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
		String sender=request.getParameter("rec");
		HttpSession session=request.getSession();
		String reciever=(String)session.getAttribute("name");
		int mid=com.user.chats.lastRecievedMessage(sender,reciever);
		System.out.println("Mid="+mid);
		if(mid>0) {
		com.user.chats.markAsRead(reciever,sender);
		JSONArray chats=com.user.chats.recieveMessage(sender, reciever, mid);
		PrintWriter out=response.getWriter();
		out.println(chats);
		System.out.println("Recieved Chats"+chats);
		}
	}

}
