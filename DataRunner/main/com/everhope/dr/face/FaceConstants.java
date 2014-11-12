package com.everhope.dr.face;
/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月9日 下午1:16:25 
 * @version V1.0 
 */
public class FaceConstants {

	/******************************************************
	 * context keys definition
	 ******************************************************/
	
	/**
	 * context中的pages=Map<String, Page>
	 */
	public static final String CTX_K_PAGES = "ctxpages";
	
	/*******************************************************
	 * session 变量
	 ******************************************************/
	/**
	 * session中的page容器key定义 sepages
	 */
	public static final String SEN_K_PAGES = "sepages";
	
	/**
	 * session中的user对象key定义 user
	 */
	public static final String SEN_K_USER = "user";
	
	/**
	 * session中的租户key se-lessee
	 */
	public static final String SEN_K_LESSEE = "se-lessee";
	
	/************************************************************
	 * request url keys definition
	 ************************************************************/
	
	/**
	 * request url data key for action : action
	 */
	public static final String REQ_K_ACTIONS = "action";
	
	/**
	 * page name key : pageName
	 */
	public static final String REQ_K_PAGENAME = "pageName";
	
	/**
	 * tags key in url : tags
	 */
	public static final String REQ_K_TAGS = "tags";
	
	/**
	 * lessee key : lessee
	 */
	public static final String REQ_K_LESSEE = "lessee";
	
	/**
	 * page refresh time flag
	 */
	public static final String REQ_K_REFFLAG = "refreshFlag";
	
	/**
	 * 登录用户名
	 */
	public static final String REQ_K_USERNAME = "username";
	
	/**
	 * 登录用户密码
	 */
	public static final String REQ_K_PWD_SECRET = "secretPwd";
	
	/**
	 * 用户控制命令
	 */
	public static final String REQ_K_CONTROL = "cmds";
	
	
	/***************************************************************
	 * request key [action] enum definition
	 *****************************************************************/
	
	/**
	 * request action type-loadPage
	 */
	public static final String REQ_ACTION_LOADPAGE = "loadPage";
	
	/**
	 * request action type-register
	 */
	public static final String REQ_ACTION_REGISTER = "register";
	
	/**
	 * request action type-refresh
	 */
	public static final String REQ_ACTION_REFRESH = "refresh";
	
	/**
	 * 用户登录
	 */
	public static final String REQ_ACTION_LOGINPAGE = "pageLogin";
	
	/**
	 * 控制命令
	 */
	public static final String REQ_ACTION_CONTROL = "control";
	
	public static final String REQ_ACTION_LOADLOGIN = "loadLogin";
	
	/***************************************************************
	 * 错误消息key定义
	 ****************************************************************/
	
	/**
	 * session 画面为空
	 */
	public static final String EC_SESSION_PAGE_NULL = "10000";
	
	/**
	 * 页面刷新错误 10001
	 */
	public static final String EC_REFRESH_ERROR = "10001";
	
	/**
	 * 用户登录密码错误 10002
	 */
	public static final String EC_LOGIN_WRONG_PWD = "10002";
	
	/**
	 * 用户登录用户名不存在
	 */
	public static final String EC_LOGIN_NO_USER = "10003";
	
	/**
	 * 会话画面容器为空
	 */
	public static final String EC_SE_PAGE_CONT_NULL = "10004";
	
	/**
	 * 服务器内部错误
	 */
	public static final String EC_SERVER_ERROR = "10005";
	
	/****************************************************************
	 * 消息key定义
	 *****************************************************************/
	
	/**
	 * 操作正常 1000
	 */
	public static final String IC_OK = "0";
	
	/*****************************************************************
	 * others
	 ****************************************************************/
	
	/**
	 * front seperator  _
	 */
	public static final String FACE_SEPERATOR = "_";
	
}
