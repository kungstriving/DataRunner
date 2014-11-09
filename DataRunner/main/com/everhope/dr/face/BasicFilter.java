package com.everhope.dr.face;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class BasicFilter
 */
@WebFilter("/drd/*")
public class BasicFilter implements Filter {

	private static final Logger logger = Logger.getLogger(BasicFilter.class);
	
    /**
     * Default constructor. 
     */
    public BasicFilter() {
    	logger.info("init basic filter");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		logger.info("destroy basic filter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
