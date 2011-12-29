package com.smit.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smit.util.Constants;

/**
 * redirect to login page if user have not login
 * @author Andrew
 * 
 */
public class LoginFilter implements Filter {

	private ArrayList<String> urlList;
	
	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("reecived msg !");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		request.setCharacterEncoding("utf-8"); 
		
		String url = request.getServletPath();
		url = url.substring(url.lastIndexOf("/")+1);
		System.out.println("loginFilter url = " + url);
		boolean allowedRequest = false;
		
        if(urlList.contains(url)) {
            allowedRequest = true;
        }
        //TODO test now
        //allowedRequest = true;
        // skip 
		if(allowedRequest == false){
			HttpSession session = request.getSession();
			String str = (String)session.getAttribute(Constants.LOGIN_SUC);
			if(Constants.SUCCESS.equals(str) == false){
				 response.sendRedirect("login_jsp.do");
				 System.out.println("catch you");
			}
			
		}
		
		arg2.doFilter(arg0, arg1);

	}

	public void init(FilterConfig arg0) throws ServletException {
		String urls = arg0.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
 
        urlList = new ArrayList<String>();
 
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken().trim());
        }       

	}

}
