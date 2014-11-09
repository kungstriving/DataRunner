package com.everhope.dr.face.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月19日 下午1:30:42 
 * @version V1.0 
 */
public class FaceCommonUtils {

	public static String getErrorMsg(String errorCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Gson gson = new Gson();
		ErrorMessage em = new ErrorMessage(I18nMessages.getErrMsg(errorCode), errorCode, sdf.format(new Date()));
		return gson.toJson(em);
	}
	
	public static String getInfoMsg(String infoCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Gson gson = new Gson();
		InfoMessage im = new InfoMessage(I18nMessages.getText(infoCode), infoCode, sdf.format(new Date()));
		return gson.toJson(im);
	}
	
	/**
	 * 根据infocode和消息内容构造字符串
	 * 
	 * @param infoCode
	 * @param msgContent
	 * @return
	 */
	public static String getInfoMsg(String infoCode, String msgContent) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Gson gson = new Gson();
		InfoMessage im = new InfoMessage(msgContent, infoCode, sdf.format(new Date()));
		return gson.toJson(im);
	
	}
	
	static class InfoMessage {

		private String msg;
		private String code;
		private String datetime;
		
		public InfoMessage(String msg, String code, String datetime) {
			super();
			this.msg = msg;
			this.code = code;
			this.datetime = datetime;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
	
	}
	
	static class ErrorMessage {

		private String msg;
		private String code;
		private String datetime;
		
		public ErrorMessage(String msg, String code, String datetime) {
			super();
			this.msg = msg;
			this.code = code;
			this.datetime = datetime;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
	}
}
