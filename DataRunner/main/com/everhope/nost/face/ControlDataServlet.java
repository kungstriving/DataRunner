package com.everhope.nost.face;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.everhope.nost.face.utils.FaceCommonUtils;
import com.everhope.nost.models.User;
import com.everhope.nost.models.UserControlCmd;
import com.google.gson.Gson;

/**
 * 控制数据命令实现
 */
@WebServlet("/control")
public class ControlDataServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3895256446659212978L;
	
	private static final Logger logger = Logger.getLogger("AUTH_LOG");

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControlDataServlet() {
        super();
    }

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 控制命令下发
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		//获取用户信息
		User user = (User)session.getAttribute(FaceConstants.SEN_K_USER);
		//获取控制命令内容
		String cmdStr = request.getParameter(FaceConstants.REQ_K_CONTROL);
		Gson gson = new Gson();
		UserControlCmd userControlCmd = gson.fromJson(cmdStr, UserControlCmd.class);
		try {
			user.sendCommand(userControlCmd);
		} catch (Exception e) {
			logger.warn(ExceptionUtils.getStackTrace(e));
			logger.warn(String.format("user[%s] send control command failed.[ec=%s]", user.getUsername(), e.getMessage()));
			writer.write(FaceCommonUtils.getErrorMsg(e.getMessage()));
			writer.flush();
		}
	}
}
