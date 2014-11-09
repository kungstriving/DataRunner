package com.everhope.dr.face;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.everhope.dr.models.User;

/**
 * 加载画面实现
 */
@WebServlet("/loadPage")
public class LoadPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2567317191488244187L;
	
	private static final Logger logger = Logger.getLogger(LoadPageServlet.class);
	
    /**
     * 
     */
    public LoadPageServlet() {
        super();
    }

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 处理用户访问页面的请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//获取用户
		User user = (User)session.getAttribute(FaceConstants.SEN_K_USER);
		//获取到要访问的画面
		String pageName = request.getParameter(FaceConstants.REQ_K_PAGENAME);
		//TODO 添加租户
		String lessee = (String)session.getAttribute(FaceConstants.SEN_K_LESSEE);
		if (StringUtils.isEmpty(lessee)) {
			lessee = "user";		//默认为user租户
		}
		String pageAbsPath = request.getServletContext().getRealPath("/show/" + lessee + "/"+ pageName + ".html");
		//首先解析是否有访问该画面的权限
		String fileContent = user.loadPage(pageAbsPath);
		
		if (StringUtils.isEmpty(fileContent)) {
			logger.warn(String.format("user[%s] has no access right to page[%s]", user.getUsername(), pageName));
			response.sendRedirect(request.getServletContext().getContextPath() + "/401.html");
			return;
		} else {
			if (logger.isInfoEnabled()) {
				logger.info(String.format("user[%s] request to load page[%s]", user.getUsername(), pageName));
			}
			response.getWriter().write(fileContent);
			response.getWriter().flush();
		}
		return;
	}

}
