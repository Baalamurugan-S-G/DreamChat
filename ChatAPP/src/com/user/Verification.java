package com.user;

import com.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verification
 */
@WebServlet("/verify")
public class Verification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verification() {
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
		String name=request.getParameter("uname");
		String mail=request.getParameter("mail");
		String p1=request.getParameter("crtpwd");
		String p2=request.getParameter("conpwd");
		PrintWriter out=response.getWriter();
		HttpSession session =request.getSession();
		String printout="<div id='message'>";
		int pass_Flag=1;
		if(p1.equals(p2)) {
			if(p1.length()<4) {
	            pass_Flag=2;
			}
			
			else if(p1.length()>15) {
	            pass_Flag=3;
			}
			else {
				
				pass_Flag=0;
			}
		}
		 
		
		int user_Flag=User.verifyUser(name, "pass");
		int mail_Flag=User.verifyMail(mail);
		if(user_Flag!=0) {
			request.getRequestDispatcher("AddUser.jsp").include(request, response); 
            out.print(printout+"Username Already Exist !</div>");
			
		}
		else if(mail_Flag==1) {
			request.getRequestDispatcher("AddUser.jsp").include(request, response); 
            out.print(printout+"Mail Already Exist !</div>");
			
			
		}
		else if(pass_Flag!=0) {
			request.getRequestDispatcher("AddUser.jsp").include(request, response);
			if(pass_Flag==1)
				out.print(printout+"Check your Password !</div>");
			else if(pass_Flag==2)
				out.print(printout+"Password is not Strong !</div>");
			else if(pass_Flag==3)
				out.print(printout+"Password length Exceeded !</div>");
		}
		else if(pass_Flag==0){
			session.setAttribute("email",mail);
			session.setAttribute("pass",p1);
			session.setAttribute("uname",name);
//			request.getRequestDispatcher("validate");
			response.sendRedirect("validate");
		}
		
		
		
		
		
		
//		String url="jdbc:mysql://localhost:3306/chat";
//		String dbname="root";
//		String dbpass="";
//		String query="select * from login";
//		System.out.println("Hi");
//				
//		try {
//				Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection(url,dbname,dbpass);
//			System.out.println("HHHi");
//			Statement st=con.createStatement();
//			ResultSet rs=st.executeQuery(query);
//			System.out.println(rs);
//			boolean f=true;
//			while(rs.next()) {
////				System.out.println("iii");
//				if(rs.getString(1).equals(name)) {
//					
//					request.getRequestDispatcher("AddUser.jsp").include(request, response); 
//		            out.print("Username Already Exist !");
//		            f=false;
//				}
//				 
//				
//			}
//			if(f){
//				HttpSession session =request.getSession();
//				session.setAttribute("uname",name);
//				request.getRequestDispatcher("Signup.jsp").include(request, response);
//			}
//			System.out.println("Closed");
//			st.close();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
