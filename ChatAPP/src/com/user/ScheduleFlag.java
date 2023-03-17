package com.user;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleFlag
 */
@WebServlet("/ScheduleFlag")
public class ScheduleFlag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleFlag() {
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
		
	}

	public void init()
    {
		new ScheduleFlag(3600);
//		new ScheduleFlag(10);            For Testing
    }
	
	

	Timer timer;

    public ScheduleFlag(int seconds) {
        timer = new Timer();
        Calendar date = Calendar.getInstance();
        timer.schedule(task,date.getTime(), seconds*1000);
	}

//    class RemindTask extends TimerTask {
//        public void run() {
//            System.out.println("Time's up!");
//            com.user.User.scheduleTheFlag();
//            timer.cancel(); 
//        }
//    }

    TimerTask task = new TimerTask() {
		
		
		public void run() {
			com.user.User.scheduleTheFlag();
			System.out.println("ScheduleFlag-ON");
		}
	};
    
	

}
