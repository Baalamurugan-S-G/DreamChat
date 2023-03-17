package com.user;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet Filter implementation class AddUser
 */
@WebFilter("/validate")
public class AddUser implements Filter {

    /**
     * Default constructor. 
     */
    public AddUser() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session =request.getSession();
		if(session.getAttribute("mail")!=null) {
			chain.doFilter(request, response);
			return ;
		}
		
		
		String name=request.getParameter("uname");
		String mail=request.getParameter("mail");
		String p1=request.getParameter("crtpwd");
		String p2=request.getParameter("conpwd");
		PrintWriter out=response.getWriter();
		
		String printout="<div id='message'>";
		int pass_Flag=1;
		System.out.println("bubub");
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
			session.setAttribute("mail",mail);
			session.setAttribute("pwd",p1);
			session.setAttribute("uname",name);
//			request.getRequestDispatcher("validate");
//			response.sendRedirect("validate");
			chain.doFilter(req, res);
		}
		
//		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
