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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class checkpass
 */
@WebFilter("/pass")
public class checkpass implements Filter {

    /**
     * Default constructor. 
     */
    public checkpass() {
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
		HttpServletRequest req=(HttpServletRequest) request;
		PrintWriter out=response.getWriter();
		String p1=request.getParameter("crtpwd");
		String p2=request.getParameter("conpwd");
		if(p1.equals(p2)) {
			if(p1.length()<4) {
				req.getRequestDispatcher("CreatePass.jsp").include(request, response); 
	            out.print("Password is not Strong !");
			}
			
			else if(p1.length()>15) {
				req.getRequestDispatcher("CreatePass.jsp").include(request, response); 
	            out.print("Password Length exceeded !");
			}
			else {
				HttpSession session =req.getSession();
				session.setAttribute("pwd",p2);
				chain.doFilter(request, response);
			}
		}
		
		// pass the request along the filter chain
		else {
			
			req.getRequestDispatcher("CreatePass.jsp").include(request, response); 
            out.print("Password Does not matches !");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
