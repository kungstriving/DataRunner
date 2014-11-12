package com.everhope.dr.face;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.everhope.dr.datastore.DataBroker;
import com.everhope.dr.face.utils.I18nMessages;
import com.everhope.dr.models.Lessee;
import com.everhope.dr.models.Page;
import com.everhope.dr.models.PageContainer;

/**
 * Nost System listener
 *
 */
@WebListener
public class DRInitListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(DRInitListener.class);
	
    /**
     * Default constructor. 
     */
    public DRInitListener() {
    	logger.info("PageLoadListener()");
    }

	/**
	 * init to load the pages
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent ctxEve) {
    	logger.info("DR starting...");
    	
		//获取页面容器
		PageContainer<Page> pageContainer = new PageContainer<>();
		pageContainer.init();
		//添加页面容器到系统context中
		ctxEve.getServletContext().setAttribute(FaceConstants.CTX_K_PAGES, pageContainer);
    	
		//添加数据库初始函数
		DataBroker broker = DataBroker.getInstance();
		
		try {
			//加载系统函数
			broker.loadScripts();
			
			//添加初始租户 user
			Lessee lessee = new Lessee();
			lessee.setName("user");
			lessee.setDesc("默认租户");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			lessee.setCreateDate(sdf.format(new Date()));
			broker.createLessee(lessee);
			
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			logger.fatal(ExceptionUtils.getStackTrace(e));
			RuntimeException re = new RuntimeException(e);
			throw re;
		}
		
		//初始化国际化消息对象
		I18nMessages.init(Locale.getDefault());
		
		//TODO 加入初始租户
    	//loadServerPages(pageMap, context);
    	
    	logger.info("DR started");
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
