package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Schedule
 */
@WebServlet("/api/schedule")
public class Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schedule() {
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
		HttpSession session=request.getSession();
		String sender=(String)session.getAttribute("name");
		String reciever=request.getParameter("rec");
		String message=request.getParameter("msg");
		String date=request.getParameter("date");
		String time=request.getParameter("time");
		System.out.println(reciever+message+date+time);
		System.out.println(date.substring(0, 4)+ date.substring(5, 7)+date.substring(8, 10));
//		Calendar datetime=Calendar.getInstance();
		
//		datetime.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
//		System.out.println(datetime);
		int scheduleFlag=com.user.chats.scheduleMessage(sender, reciever, message, date+" "+time+":00");
		com.user.User.checkScheduleTime(reciever,date+" "+time+":00");
		PrintWriter out=response.getWriter();
		out.println(scheduleFlag==1?"true":"false");
	}

}
