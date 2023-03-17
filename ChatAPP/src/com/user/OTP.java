package com.user;

import com.user.Mail;
import java.io.IOException;
//import java.math.*;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.*;
//import javax.mail.internet.*; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;  



/**
 * Servlet implementation class OTP
 */
@WebServlet("/validate")
public class OTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OTP() {
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
//		doGet(request, response);
		HttpSession session =request.getSession();
		int otp=0;
		if(session.getAttribute("otp")!=null) {
//			response.sendRedirect("OTP.jsp");
//			return;
			otp=(Integer)session.getAttribute("otp");
		}
		else {
		Random rnd = new Random();
		otp = rnd. nextInt(999999)%10000;
		session.setAttribute("otp",otp);
		System.out.println(otp);
		}
		String msgcnt="OTP "+String.valueOf(otp);
		String mail=(String) session.getAttribute("mail");
		boolean f=false;
		
		try {
			f=Mail.send("automaticmail24@gmail.com","Automate24",mail,"Dream Chat (Verification User)",msgcnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(f) {
			try {
				response.sendRedirect("OTP.jsp");
			}
			catch(IllegalStateException e) {
				
			}
		}
	
	}
}