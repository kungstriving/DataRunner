package com.everhope.nost.face;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.everhope.nost.datastore.DataBroker;
import com.everhope.nost.face.utils.I18nMessages;
import com.everhope.nost.models.Page;

/**
 * Nost System listener
 *
 */
@WebListener
public class NostInitListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(NostInitListener.class);
	
    /**
     * Default constructor. 
     */
    public NostInitListener() {
    	logger.info("PageLoadListener()");
    }

	/**
	 * init to load the pages
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent ctxEve) {
    	logger.info("NOST starting...");
    	
    	//添加context画面容器
    	Map<String, Page> pageMap = new HashMap<String, Page>();
    	
    	ServletContext context = ctxEve.getServletContext();
    	context.setAttribute(FaceConstants.CTX_K_PAGES, pageMap);
    	
		//添加数据库初始函数
		DataBroker broker = DataBroker.getInstance();
		//加载系统函数
		try {
			broker.loadScripts();
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			logger.fatal(ExceptionUtils.getStackTrace(e));
			RuntimeException re = new RuntimeException(e);
			throw re;
		}
		
		//初始化国际化消息对象
		I18nMessages.init(Locale.getDefault());
		
    	//loadServerPages(pageMap, context);
    	
    	logger.info("NOST started");
    }

//	private void loadServerPages(Map<String, Page> pageMap,
//			ServletContext context) {
//		//look for all the pages
//    	String pagesPath = context.getRealPath("/pages");
//    	File[] dirs = new File(pagesPath).listFiles((FileFilter)FileFilterUtils.directoryFileFilter());
//    	for (File pageDir : dirs) {
//			//only the directoris
//    		File pageJson = new File(pageDir.getAbsolutePath() + "/" + pageDir.getName() + ".server.json");
//    		try {
//				String fileContent = FileUtils.readFileToString(pageJson);
//				//
//				Gson gson = new Gson();
//				Page page = gson.fromJson(fileContent, Page.class);
//				pageMap.put(page.getPageName(), page);
//			} catch (IOException e) {
//				logger.error(e.getMessage());
//				logger.error("load pages error, system exit!");
//				throw new RuntimeException(e);
//			}
//		}
//	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
