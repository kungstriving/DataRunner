package com.everhope.nost.models;
/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月13日 下午4:25:43 
 * @version V1.0 
 */
public class AuthItem {

	private String name = "";
	private String id = "";

	public String getName() {
		return name;
	}
	public void setItemName(String itemName) {
		this.name = itemName;
	}
	public String getId() {
		return id;
	}
	public void setItemCode(String itemCode) {
		this.id = itemCode;
	}
	public AuthItem(String pName, String pId) {
		super();
		this.name = pName;
		this.id = pId;
	}
}
