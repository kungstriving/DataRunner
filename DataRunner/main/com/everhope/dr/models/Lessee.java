package com.everhope.dr.models;

import java.util.HashMap;
import java.util.Map;

import com.everhope.dr.datastore.StoreConstants;

/**
 * 租户
 * @author kongxiaoyang
 * @date 2014年8月25日 下午2:18:05 
 * @version V1.0 
 */
public class Lessee {

	private String name = "";
	private String desc = "";
	private String createDate = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Map<String, String> toStoreMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put(StoreConstants.RKP_CREATE_DATE, this.getCreateDate());
		map.put(StoreConstants.RKP_DESC, this.getDesc());
		
		return map;
	}
}
