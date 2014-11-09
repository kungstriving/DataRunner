package com.everhope.nost.exceptions;
/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月8日 上午10:54:23 
 * @version V1.0 
 */
public class LoginException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7711437957989103746L;

	private String loginErrType = "";
	
	public LoginException() {
		super();
	}
	
	public void setLoginErrType(String errType) {
		this.loginErrType = errType;
	}
	
	public String getLoginErrType() {
		return this.loginErrType;
	}
	

}
