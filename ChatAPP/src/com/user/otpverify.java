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
 * Servlet Filter implementation class otpverify
 */
@WebFilter("/otpverify")
public class otpverify implements Filter {

    /**
     * Default constructor. 
     */
    public otpverify() {
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
		HttpServletResponse res=(HttpServletResponse) response;
		PrintWriter out=response.getWriter();
		int arr[]=new int[4];
		try {
		arr[0]=Integer.parseInt(req.getParameter("c1"));
		arr[1]=Integer.parseInt(req.getParameter("c2"));
		arr[2]=Integer.parseInt(req.getParameter("c3"));
		arr[3]=Integer.parseInt(req.getParameter("c4"));
		}
		catch(NumberFormatException e) {
			req.getRequestDispatcher("OTP.jsp").include(req, res);
			out.print("Enter Valid OTP !!");
			return;
		}
		HttpSession session =req.getSession();
		Integer otp=(Integer)(session.getAttribute("otp"));
		System.out.println(session.getAttribute("otp"));
		boolean f=true;
		int i=4;
		while(otp>0) {
			i--;
			if(otp%10!=arr[i]) {
				request.getRequestDispatcher("OTP.jsp").include(request, response); 
				 
	            out.print("Incorrect OTP !!");
	            f=false;
	            return ;
			}otp/=10;
		}
		if(f) {
//			res.sendRedirect("CreatePass.jsp");
			chain.doFilter(request, response);
		}
		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
