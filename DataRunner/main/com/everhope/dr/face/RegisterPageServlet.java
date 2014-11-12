package com.everhope.dr.face;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.everhope.dr.face.utils.FaceCommonUtils;
import com.everhope.dr.models.Page;
import com.everhope.dr.models.PageContainer;
import com.everhope.dr.models.SessionPage;
import com.everhope.dr.models.Tag;
import com.google.gson.Gson;

/**
 * Servlet implementation class RegisterPageServlet
 */
@WebServlet("/register")
public class RegisterPageServlet extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 6473804317047526877L;

	private static final Logger logger = Logger.getLogger(RegisterPageServlet.class);
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPageServlet() {
        super();
        logger.info("create register servlet");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(String.format("register page @ %s", request.getSession().getId()));
		
		PrintWriter writer = response.getWriter();
		
		//获取画面名称
		String pageName = request.getParameter(FaceConstants.REQ_K_PAGENAME);
		//获取租户
		String lessee = request.getParameter(FaceConstants.REQ_K_LESSEE);
		//TODO 租户为空，默认添加user
		if (StringUtils.isEmpty(lessee)) {
			lessee = "user";
		}
		//获取注册点json字符串
		String tagsJson = request.getParameter(FaceConstants.REQ_K_TAGS);
		
		if (logger.isDebugEnabled()) {
			logger.debug("register page " + pageName);
		}
		
		//resolve the tags
		List<Tag> regTags = resolveTags(tagsJson);
		
		//check if the system already load this page
		PageContainer<Page> container = (PageContainer<Page>)getServletContext().getAttribute(FaceConstants.CTX_K_PAGES);
		Page page = container.getByNameAndLessee(lessee, pageName);
		if (page == null) {
			page = new Page(lessee, pageName);
			container.addPage(page);
		}
		//更新当前画面所有tag点
		page.setTags(regTags);
		
		HttpSession session = request.getSession();
		Map<String, SessionPage> seContainer = (Map<String, SessionPage>)session.getAttribute(FaceConstants.SEN_K_PAGES);
		//判断session中是否包含该page
		if (seContainer == null) {
			//当前sessionContainer为空
			seContainer = new HashMap<String, SessionPage>();
			//添加sessionContainter到session对象中
			session.setAttribute(FaceConstants.SEN_K_PAGES, seContainer);
		}
		
		SessionPage sessionPage = seContainer.get(pageName);
		
		if (sessionPage == null) {
			//如果当前session无该页面对象 新建page并加入
			//用已有的context page 构建sessionpage对象
			SessionPage regPage = new SessionPage(container.getByNameAndLessee(lessee, pageName));
			seContainer.put(pageName, regPage);
		}
		
		logger.info("register done");
		writer.write(FaceCommonUtils.getInfoMsg(FaceConstants.IC_OK));
		writer.flush();
	}
	
	private List<Tag> resolveTags(String tagsJson) {
		Gson gson = new Gson();
		List<String> tagNames = gson.fromJson(tagsJson, RegisterTags.class).tags;
		
		List<Tag> pageTags = new ArrayList<>();
		
		for (String tagStr : tagNames) {
			
			if (!tagStr.contains(FaceConstants.FACE_SEPERATOR)) {
				continue;
			}
			//tasStr should like ds_tagname
			String[] tagsPart = tagStr.split(FaceConstants.FACE_SEPERATOR);
			if (tagsPart == null || tagsPart.length != 2) {
				logger.warn(String.format("Resolve Tag[%s] name rror", tagStr));
				continue;
			}
			
			Tag tmp = new Tag();
			tmp.setDs(tagsPart[0]);	//设置数据源名称
			tmp.setTagName(tagsPart[1]);	//设置tag名称
			
			pageTags.add(tmp);
		}
		
		return pageTags;
	}
	
	class RegisterTags {
		public List<String> tags;
	}

}
