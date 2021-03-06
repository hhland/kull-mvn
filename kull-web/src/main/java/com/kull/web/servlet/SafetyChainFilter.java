package com.kull.web.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kull.Stringz;

public class SafetyChainFilter extends HttpServletFilter {

	String errorUrl,errorContext;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		// TODO Auto-generated method stub
           errorUrl=fc.getInitParameter("errorUrl");
           errorContext=fc.getInitParameter("errorContext");
	}

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub
        String referer=req.getHeader("referer");
        if(Stringz.isBlank(referer)||!referer.contains(req.getServerName())){
        	if(Stringz.isNotBlank(errorUrl)){
        		res.sendRedirect(errorUrl);
        	}else if(Stringz.isNotBlank(errorContext)){
        		res.getWriter().write(errorContext);
        	}else{
        		res.getWriter().write("error");
        	}
        	
        }
	}

}
