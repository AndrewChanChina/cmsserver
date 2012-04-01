package com.smit.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {

	private ArrayList<String> urls;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		String url = request.getServletPath();
		url = url.substring(url.lastIndexOf("/")+1);
		//System.out.println("session url is "+url);
		if(urls.contains(url)){
			Object name = request.getSession().getAttribute("username");
			System.out.println(name);
			if(request.getSession().getAttribute("username")==null){
				response.sendRedirect("main.jsp");
				return;
			}
		}
		arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		String url = arg0.getInitParameter("allow_url");
        StringTokenizer token = new StringTokenizer(url, ",");
 
        urls = new ArrayList<String>();
 
        while (token.hasMoreTokens()) {
            urls.add(token.nextToken().trim());
        }   
	}

}
