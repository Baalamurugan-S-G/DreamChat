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
 * Servlet Filter implementation class Login
 */
@WebFilter(filterName = "login", urlPatterns = { "/login" })
public class Login implements Filter {

    /**
     * Default constructor. 
     */
    public Login() {
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
		System.out.println("Login");
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		HttpSession session =request.getSession();
		String LoginFlag=(String) session.getAttribute("name");
		String name=request.getParameter("uname");
		String pass=request.getParameter("pwd");
		PrintWriter out=response.getWriter(); 
		
		if(LoginFlag!=null) name=LoginFlag;
		
		int verify_Flag=User.verifyUser(name, pass);
		if(verify_Flag==1 || LoginFlag!=null) {
			session.setAttribute("name",name);
			chain.doFilter(req, res);
		}
		else {
			request.getRequestDispatcher("Login.jsp").include(request, response); 
            out.print("Sorry,Incorrect username or password !!");
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
