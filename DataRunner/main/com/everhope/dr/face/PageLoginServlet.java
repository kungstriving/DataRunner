package com.everhope.dr.face;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.everhope.dr.face.utils.FaceCommonUtils;
import com.everhope.dr.models.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class PageLoginServlet
 */
@WebServlet("/pageLogin")
public class PageLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2276692690347646815L;
	
	private static final Logger logger = Logger.getLogger("AUTH_LOG");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageLoginServlet() {
        super();
    }

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//handle the user login service
		HttpSession session = request.getSession();
		String userName = request.getParameter(FaceConstants.REQ_K_USERNAME);
		String secPwd = request.getParameter(FaceConstants.REQ_K_PWD_SECRET);
		PrintWriter writer = response.getWriter();
		//create the user object
		User user = new User(userName, secPwd);
		/*
		try {
			user.login();
		} catch (LoginException e) {
			logger.warn(e.getMessage());
			logger.warn(String.format("user[%s] login failed.[code=%s]", userName, e.getLoginErrType()));
			writer.write(FaceCommonUtils.getErrorMsg(e.getLoginErrType()));
			writer.flush();
			return;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			logger.warn(String.format("user[%s] login failed.[msg=%s]", userName, e.getMessage()));
			writer.write(FaceCommonUtils.getErrorMsg(FaceConstants.EC_SERVER_ERROR));
			writer.flush();
			return;
		
		}
		*/
		//login ok
		session.setAttribute(FaceConstants.SEN_K_USER, user);

		if (logger.isInfoEnabled()) {
			logger.info(String.format("user[%s] logged in system [sessionid=%s,fromIP=%s]", userName,session.getId(),request.getRemoteAddr()));
		}
		
		//get the init page
		InitPageMsg pageMsg = new InitPageMsg();
		pageMsg.initPage = user.getInitPageName();
		pageMsg.initPage = "tom";
		Gson gson = new Gson();
		String msg = gson.toJson(pageMsg, InitPageMsg.class);
		writer.write(FaceCommonUtils.getInfoMsg(FaceConstants.IC_OK, msg));
		writer.flush();
	}

	class InitPageMsg {
		String initPage;
	}
}
