package com.user;

import com.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class validate
 */
@WebFilter("/validateNo")
public class validate implements Filter {

    /**
     * Default constructor. 
     */
    public validate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// pass the request along the filter chain
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session =req.getSession();
		if(session.getAttribute("mail")!=null) {
			chain.doFilter(request, response);
			return ;
		}
		String mail=request.getParameter("mail");
		PrintWriter out=response.getWriter();
		
		int verify_Flag=User.verifyMail(mail);
		if(verify_Flag==1) {
			session.setAttribute("mail",mail);
//			req.getRequestDispatcher("OTP.jsp").forward(request, response);
			chain.doFilter(request, response);
		}
		else {
			req.getRequestDispatcher("Signup.jsp").include(request, response); 
            out.print("Mail Already Exist !");
		}
		
		
		
//		String url="jdbc:mysql://localhost:3306/chat";
//		String dbname="root";
//		String dbpass="";
//		String query="select * from users";
//		System.out.println("Hi");
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection(url,dbname,dbpass);
////			System.out.println("HHHi");
//			Statement st=con.createStatement();
//			ResultSet rs=st.executeQuery(query);
//			System.out.println(rs);
//			boolean f=true;
//			while(rs.next()) {
////				System.out.println("iii");
//				if(rs.getString(3).equals(name)) {
//					
//					req.getRequestDispatcher("Signup.jsp").include(request, response); 
//		            out.print("Mail Already Exist !");
//		            f=false;
//				}
//				 
//				
//			}
//			if(f){
//				
//				session.setAttribute("mail",name);
////				req.getRequestDispatcher("OTP").forward(request, response);
//				chain.doFilter(request, response);
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
//		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
