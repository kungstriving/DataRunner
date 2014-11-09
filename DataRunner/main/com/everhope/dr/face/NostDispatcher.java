package com.everhope.dr.face;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * main dispatcher
 */
@WebServlet("/nost")
public class NostDispatcher extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6487309972038551486L;

	private static final Logger logger = Logger.getLogger(NostDispatcher.class);
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public NostDispatcher() {
        super();
        logger.info("create NostDispatcher");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter(FaceConstants.REQ_K_ACTIONS);
		ServletContext sc = getServletContext();
		RequestDispatcher dispatcher = null;
		
		if (StringUtils.isEmpty(action)) {
			logger.warn("Bad request! action = null");
			return;
		}
		
		switch (action) {
		case FaceConstants.REQ_ACTION_REFRESH:
			dispatcher = sc.getRequestDispatcher("/refresh");
			break;
		case FaceConstants.REQ_ACTION_REGISTER:
			//dispatch to register service
			dispatcher = sc.getRequestDispatcher("/register");
			break;
		case FaceConstants.REQ_ACTION_LOADLOGIN:
			dispatcher = sc.getRequestDispatcher("/pages/login/");
			break;
		case FaceConstants.REQ_ACTION_LOGINPAGE:
			dispatcher = sc.getRequestDispatcher("/pageLogin");
			break;
		case FaceConstants.REQ_ACTION_LOADPAGE:
			dispatcher = sc.getRequestDispatcher("/loadPage");
//			response.sendRedirect(request.getServletContext().getContextPath() + "/pages/tom/");
//			return;
			break;
		case FaceConstants.REQ_ACTION_CONTROL:
			dispatcher = sc.getRequestDispatcher("/control");
			break;
		default:
			dispatcher = sc.getRequestDispatcher("/error.html");
			break;
		}
		
		if (logger.isInfoEnabled()) {
			logger.info(String.format("dispatch request.[sessionid=%s,fromIP=%s,action=%s]", 
					request.getSession().getId(),request.getRemoteAddr(),action));
		}
		
		dispatcher.forward(request, response);
	}

}
