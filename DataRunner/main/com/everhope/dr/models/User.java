package com.everhope.dr.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.everhope.dr.datastore.DataBroker;
import com.everhope.dr.datastore.LoginResponseMessage;
import com.everhope.dr.exceptions.LoginException;
import com.google.gson.Gson;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月8日 上午10:50:12 
 * @version V1.0 
 */
public class User {

	private static final Logger logger = Logger.getLogger(User.class);
	
	private String username;
	private String secPwd;
	
	public User(String pUserName, String pSecPwd) {
		this.username = pUserName;
		this.secPwd = pSecPwd;
	}

	/**
	 * 获取系统初始画面
	 * @return
	 */
	public String getInitPageName() {
		//TODO 根据用户信息获取当前群组的初始画面名称
		return "tom";
	}
	
	/**
	 * 用户登录
	 * 
	 * @throws LoginException
	 * @throws Exception
	 */
	public void login() throws LoginException, Exception{
		DataBroker broker = DataBroker.getInstance();
		try {
			LoginResponseMessage resMsg = broker.login(this);
			if (resMsg.getCode().trim().equals(ModelConstants.OK_CODE)) {
				//登录正常
				return;
			} else {
				//登录出错
				LoginException le = new LoginException();
				le.setLoginErrType(resMsg.getCode());
				throw le;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private List<String> checkTheAuthType(List<AuthItem> items) {
		DataBroker broker = DataBroker.getInstance();
		List<String> authResList = broker.getAuthType(this.username, items);
		return authResList;
	}
	
	/**
	 * 当前用户加载画面，如果没有权限访问则返回空字符串
	 * 如果有权限访问，则返回经过过滤的HTML页面内容
	 * 
	 * @param absPagePath 所要加载文件的绝对路径 直到.html
	 * @return
	 * @throws IOException 
	 */
	public String loadPage(String absPagePath) throws IOException {
		String fileContent  = "";
		File htmlFile = new File(absPagePath);
		try {
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			Elements elements = doc.select("page");
			Element pageEle = elements.first();
			String authJson = pageEle.attr("authItem");
			
			Gson gson = new Gson();
			AuthItem ai = gson.fromJson(authJson, AuthItem.class);
			List<AuthItem> list = new ArrayList<>();
			list.add(ai);
			String authType = this.checkTheAuthType(list).get(0);
			//TODO 未加权限限制
			authType = ModelConstants.AUTH_TYPE_ENABLE;
			switch (authType) {
			case ModelConstants.AUTH_TYPE_NONE:
				//返回空字符串 表明无访问权限
				fileContent = "";
				break;
			case ModelConstants.AUTH_TYPE_DISABLE:
				//TODO 不可用 则将页面所有可接受用户动作的标签全部设为不可用
				fileContent = doc.outerHtml();
				break;
			case ModelConstants.AUTH_TYPE_ENABLE:
				//可用
				fileContent = doc.outerHtml();
				break;
			default:
				logger.warn(String.format("user[%s] load page[%s] find no auth_type equals [%s]", 
						this.username, absPagePath, authType));
				fileContent = "";
				break;
			}
			
		} catch (IOException e) {
			throw e;
		}
		
		return fileContent;
	}
	
	public void sendCommand(UserControlCmd controlCmd) throws Exception{
		DataBroker broker = DataBroker.getInstance();
		broker.sendCommond(this, controlCmd);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSecPwd() {
		return secPwd;
	}

	public void setSecPwd(String secPwd) {
		this.secPwd = secPwd;
	}
	
	class UserLoginMsg {
		String mid = "";
		String username = "";
		String secPwd = "";
	}
	
}
