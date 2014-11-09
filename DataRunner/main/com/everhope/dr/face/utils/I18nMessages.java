package com.everhope.dr.face.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月19日 下午1:31:45 
 * @version V1.0 
 */
public class I18nMessages {

	private static ResourceBundle textbundle = null;
	private static ResourceBundle errbundle = null;
	
	public static void init(Locale locale) {
		textbundle = ResourceBundle.getBundle("textmsg", locale);
		errbundle = ResourceBundle.getBundle("errormsg", locale);
	}
	
	public static String getText(String key) {
		return textbundle.getString(key);
	}
	
	public static String getErrMsg(String key) {
		return errbundle.getString(key);
	}
	

}
