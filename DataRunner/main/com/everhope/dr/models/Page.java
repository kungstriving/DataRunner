package com.everhope.dr.models;

import java.util.List;

/**
 * Page definition
 * @author kongxiaoyang
 * @date 2014年3月9日 下午1:00:55 
 * @version V1.0 
 */
public class Page {

	private String lessee;
	private String pageName;
	private List<Tag> tags;
//	private int refreshFlag = 0;
	
	public Page(String pLessee, String pPageName) {
		this.lessee = pLessee;
		this.pageName = pPageName;
	}
	
	public Page(String pPageName, List<Tag> pTags) {
		this.pageName = pPageName;
		this.tags = pTags;
	}

	public String getLessee() {
		return lessee;
	}

	public void setLessee(String lessee) {
		this.lessee = lessee;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
