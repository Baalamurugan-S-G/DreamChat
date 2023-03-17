package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.AppConstants;

/**
 * Servlet implementation class checkupdate
 */
@WebServlet("/otpverify")
public class checkupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkupdate() {
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
		System.out.println("Password Verified");
		HttpSession session =request.getSession();
		String user=(String)session.getAttribute("uname");
		String mail=(String)session.getAttribute("mail");
		String pwd=(String)session.getAttribute("pwd");
		session.invalidate();
		int c1,c2;
		String url=AppConstants.url;
		String name=AppConstants.userName;
		String pass=AppConstants.password;
		String Add1="insert into login values (?,?)";
		String Add2="insert into users (uid,email) values (?,?)";
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection(url,name,pass);
		
		PreparedStatement ps1=con.prepareStatement(Add1);
		ps1.setString(1,user);
		ps1.setString(2,pwd);
		
		c1=ps1.executeUpdate();
		ps1.close();
		
		PreparedStatement ps2=con.prepareStatement(Add2);
		ps2.setString(1,user);
		ps2.setString(2,mail);
		
		c2=ps2.executeUpdate();
		ps2.close();
		if(c1==1 && c2==1) {
			System.out.println("Values Updated");
			response.sendRedirect("Login.jsp");
		}
		
		
		}
		catch(Exception e) {
			System.out.print(true);
		}
		
	}

}
