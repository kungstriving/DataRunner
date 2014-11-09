package com.everhope.nost.models;

import org.apache.commons.lang.StringUtils;

import com.everhope.nost.face.FaceConstants;


/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月14日 下午4:15:14 
 * @version V1.0 
 */
public class UserControlCmd {

	String mid = "";
	String name = "";
	String field = "";
	String value = "";

	public String getControlDS() {
		return StringUtils.substringBefore(name, FaceConstants.FACE_SEPERATOR);
	}
	
	public void setMid(String pMid) {
		this.mid = pMid;
	}
}
