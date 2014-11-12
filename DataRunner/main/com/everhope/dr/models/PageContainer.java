package com.everhope.dr.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 画面容器
 * 
 * @author kongxiaoyang
 * @date 2014-02-26
 * @version V1.0 
 */
public class PageContainer<E extends Page> {
	
	private static final Logger logger = Logger.getLogger(PageContainer.class);

	private List<E> pages = new ArrayList<>();

//	private static PageContainer<Page> singleInstance = null;
	
	public PageContainer() {}
	
	
	public void init() {}
	
	
	/**
	 * 根据租户和画面名称获取画面对象
	 * 
	 * @param lessee
	 * @param pageName
	 * @return
	 */
	public E getByNameAndLessee(String lessee, String pageName) {
		if (logger.isDebugEnabled()) {
			logger.debug("get page from contianer by lessee = " + lessee + " and page = " + pageName);
		}
		
		for(E page : pages) {
			if (pageName.equals(page.getPageName()) && lessee.equals(page.getLessee())) {
				//画面名称相同且租户相同
				return page;
			}
		}
		
		return null;
	}
	
	/**
	 * 根据画面名称从容器中获取该画面
	 * 如果未找到 则返回null
	 * @param name
	 * @return
	 */
	public E getByName(String name) {
		if (logger.isDebugEnabled()) {
			logger.debug("get page from container name = " + name);
			logger.debug("container size " + pages.size());
		}
		for (E page : pages) {
			if (name.equals(page.getPageName())) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("return page");
				}
				
				return page;
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("return null page");
		}
		return null;
	}
	
	/**
	 * 添加画面对象到全局容器中
	 * @param pPage
	 */
	public void addPage(E page) {
		pages.add(page);
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<PageContainer<Page>>(){}.getType();
		return gson.toJson(this, type);
	}
}
