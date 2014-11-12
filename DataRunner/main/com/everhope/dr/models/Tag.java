package com.everhope.dr.models;

import com.everhope.dr.datastore.StoreConstants;
import com.everhope.dr.face.FaceConstants;

/**
 * Tag definition
 * @author kongxiaoyang
 * @date 2014年3月9日 下午1:05:35 
 * @version V1.0 
 */
public class Tag {

	private String tenant;
	private String ds;
	private String tagName;
	
	/**
	 * 获取DB中的实时数据tag 点表示 data:real:dsName:TagName
	 * 注：确实最前面的租户部分
	 * @return
	 */
	public String getDBTagName() {
		return StoreConstants.RK_DATA + StoreConstants.PDE_SEPERATOR
				+ StoreConstants.RK_DATA_REAL + StoreConstants.PDE_SEPERATOR
				+ ds + StoreConstants.PDE_SEPERATOR
				+ tagName;
	}
	
	/**
	 * 获取前端需要的tag点表示 DS_TagName
	 * @return
	 */
	public String getFaceTagName() {
		return this.ds + FaceConstants.FACE_SEPERATOR + this.tagName;
	}
	
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String getDs() {
		return ds;
	}
	public void setDs(String ds) {
		this.ds = ds;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public Tag() {}
	
	public Tag(String userPart, String ds, String tagName) {
		super();
		this.tenant = userPart;
		this.ds = ds;
		this.tagName = tagName;
	}
	
	
}
