package com.everhope.nost.face;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.everhope.nost.face.utils.FaceCommonUtils;
import com.everhope.nost.models.SessionPage;

/**
 * 页面刷新
 */
@WebServlet("/refresh")
public class RefreshPageServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5809478464452228334L;
	
	private static final Logger logger = Logger.getLogger(RefreshPageServlet.class);

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshPageServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("refreshing page @ " + request.getSession().getId());
		
		PrintWriter writer = response.getWriter();
		
		String pageName = request.getParameter(FaceConstants.REQ_K_PAGENAME);
		String refreshFlag = request.getParameter(FaceConstants.REQ_K_REFFLAG);
		
		Map<String, SessionPage> pageMap = (Map<String,SessionPage>)
				request.getSession().getAttribute(FaceConstants.SEN_K_PAGES);
		if (pageMap == null) {
			logger.warn(String.format("Session Page Container is null"));
			writer.write(FaceCommonUtils.getErrorMsg(FaceConstants.EC_SE_PAGE_CONT_NULL));
			writer.flush();
			return;
		}
		SessionPage page = pageMap.get(pageName);
		if (page == null) {
			//服务器不包含该sessionpage
			logger.warn(String.format("Session Page[%s] is null", pageName));
			writer.write(FaceCommonUtils.getErrorMsg(FaceConstants.EC_SESSION_PAGE_NULL));
			writer.flush();
			return;
		}
		
		page.setUpdateFlag(Integer.parseInt(refreshFlag));
		
		String refreshData;
		try {
			refreshData = page.update();
			if (logger.isDebugEnabled()) {
				logger.debug("got updated tags : " + refreshData);
			}
			writer.write(refreshData);
			writer.flush();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			logger.error(e.getMessage());
			
			//response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			writer.write(FaceCommonUtils.getErrorMsg(FaceConstants.EC_REFRESH_ERROR));
			writer.flush();
		}
		
		logger.info("refresh page done");
		
	}

}
