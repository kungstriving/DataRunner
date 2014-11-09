package com.everhope.dr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.everhope.dr.datastore.DataBroker;
import com.everhope.dr.datastore.StoreConstants;
import com.google.gson.Gson;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月14日 下午4:13:18 
 * @version V1.0 
 */
public class SessionPage {

	private static final Logger logger = Logger.getLogger(SessionPage.class);
	
	private int updateFlag = 0;
	
	private Page ctxPage = null;
	
	public SessionPage(Page page) {
		this.ctxPage = page;
	}
	
	public List<Tag> getTags() {
		return this.ctxPage.getTags();
	}
	
	public int getUpdateFlag() {
		return this.updateFlag;
	}
	
	public void setUpdateFlag(int pUpdateFlag) {
		this.updateFlag = pUpdateFlag;
	}
	
	/**
	 * 更新画面，从datastore获取最新数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String update() throws Exception {
		logger.info("refreshing page");
		Map<String, Map<String,String>> tagValueMap = DataBroker.getInstance().refreshPage(this);
		String updateJson = processTagValueMap(tagValueMap);
		logger.info("refreshing page done");
		return updateJson;
		
	}

	/**
	 * 处理刷新页面后的Map数据，提取最大时间戳，同时组织返回的json字符串
	 * @param tagValueMap
	 * @return
	 */
	private String processTagValueMap(
			Map<String, Map<String, String>> newValues) {

		int maxTimeFlag = getUpdateFlag();
		List<Tag> tags = getTags();
		List<UpdatedTag> needUpdateTags = new ArrayList<>();
		
		//提取需要更新的Tag点，并计算最大timeflag
		for (Tag tag : tags) {
			if (newValues.containsKey(tag.getDBTagName())) {
				
				Map<String, String> props = 
						newValues.get(tag.getDBTagName());
				
				//如果包含该tag名称，则说明需要更新
				UpdatedTag uTag = new UpdatedTag();
				uTag.setDsName(tag.getDs());
				uTag.setTagName(tag.getTagName());
				uTag.setName(tag.getFaceTagName());		//note:使用前端的点表示方法
				
				uTag.setQuality(props.get(StoreConstants.F_TAG_QUALITY));
				uTag.setTimestamp(props.get(StoreConstants.F_TAG_TIMESTAMP));
				uTag.setValue(props.get(StoreConstants.F_TAG_VALUE));				
				
				String timeFlag = props.get(StoreConstants.F_TAG_UPDATEFLAG);
				//计算最大的timeflag
				if (NumberUtils.toInt(timeFlag) > maxTimeFlag) {
					maxTimeFlag = NumberUtils.toInt(timeFlag);
				}
				needUpdateTags.add(uTag);
			}
		}
		
		//设置新的timeflag
		setUpdateFlag(maxTimeFlag);
		
		Gson gson = new Gson();
		UpdatedPage up = new UpdatedPage();
		up.setTags(needUpdateTags);
		up.setUpdateFlag(maxTimeFlag + "");
		return gson.toJson(up);
	}
	
	class UpdatedPage {
		//tag列表
		private List<UpdatedTag> tags = new ArrayList<>();
		
		//页面刷新标识
		private String updateFlag = "";
		
		public List<UpdatedTag> getTags() {
			return tags;
		}
		public void setTags(List<UpdatedTag> tags) {
			this.tags = tags;
		}
		public String getUpdateFlag() {
			return updateFlag;
		}
		public void setUpdateFlag(String updateFlag) {
			this.updateFlag = updateFlag;
		}
	}
	
	class UpdatedTag {
		
		private String dsName = "";
		private String tagName = "";
		private String value = "";
		private String quality = "";
		private String timestamp = "";
		private String name = "";
		
		public String getDsName() {
			return dsName;
		}
		public void setDsName(String dsName) {
			this.dsName = dsName;
		}
		public String getTagName() {
			return tagName;
		}
		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getQuality() {
			return quality;
		}
		public void setQuality(String quality) {
			this.quality = quality;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
