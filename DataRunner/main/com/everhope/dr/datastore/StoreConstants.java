package com.everhope.dr.datastore;
/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月19日 下午2:05:04 
 * @version V1.0 
 */
public class StoreConstants {

	/**
	 * 脚本timeflag-key
	 */
	public static final String SCRIPTKEY_GETKEYS_LAGER_TIMEFLAG = "larger_time";
	
	/**
	 * 获取大于时间标识的所有key
	 */
	public static final String SCRIPT_GETKEYS_LAGER_TIMEFLAG =
			"--SCRIPT_GETKEYS_LAGER_TIMEFLAG--\r\n"
			+ "local index=1;"
			+ "local resultKeys={};"
			+ "for k,v in ipairs(KEYS) do "
			+ "	local fieldExist = redis.call('hexists', v, 'uc');"
			+ "	if fieldExist == 1 then "
			+ "		local tmpTimeFlag = redis.call('hget', v, 'uc');"
			+ "		if (tmpTimeFlag+0) > (ARGV[1]+0) then "
			+ "			table.insert(resultKeys,v);"
			+ "		end;"
			+ "	end;"
			+ "end;"
			+ "return resultKeys;";
	
	/**
	 * 最大实例个数
	 */
	public static final int CONFIG_MAX_ACTIVE = 8;
	
	/**
	 * 超时 3秒
	 */
	public static final int TIMEOUT_DATA_ACCESS = 3000;
	
	/**
	 * 数据连接名称 dr-client
	 */
	public static final String DR_CLIENT_NAME = "dr-client";
	
	/**
	 * DataEngine中key分隔符: 用户名称和数据源名称不能包含'_' ':'
	 */
	public static final String PDE_SEPERATOR = ":";
	
	/**
	 * tag标识
	 */
	public static final String PDE_TAG_KEY = "tag";
	
	/****************************************************************
	 * 数据存储中各种key和通道定义 rk=redis key; rkp=redis key prop;
	 **************************************************************/
	///////////////////////////// sys //////////////////////////////
	/**
	 * 系统key前缀 sys
	 */
	public static final String RK_SYS = "sys";
	/**
	 * 租户key lessee
	 */
	public static final String RK_LESSEE = "lessee";
	
	/**
	 * 所有租户key alllessees
	 */
	public static final String RK_ALLLESSEES = "alllessees";
	
	/**
	 * 租户详情key lesseeinfo
	 */
	public static final String RK_LESSEEINFO = "lesseeinfo";
	
	/**
	 * 全局刷新计数 globaluc
	 */
	public static final String RK_GUC = "globaluc";
	
	///////////////////////// data ////////////////////////////////////
	
	/**
	 * 数据前缀 data
	 */
	public static final String RK_DATA = "data";
	
	/**
	 * 数据代理 da
	 */
	public static final String RK_DA = "da";
	
	/**
	 * 所有数据源 allsources
	 */
	public static final String RK_ALLDA = "allsources";
	
	/**
	 * 数据代理详情 dainfo
	 */
	public static final String RK_DAINFO = "dainfo";
	
	/**
	 * DB中的Tag key: tag
	 */
	public static final String RK_TAG = "tag";
	
	/**
	 * 数据代理下所有点 datags
	 */
	public static final String RK_DATAGS = "datags";
	
	/**
	 * 实时数据 real
	 */
	public static final String RK_DATA_REAL = "real";
	
	/**
	 * 实时数据value属性 value
	 */
	public static final String RKP_TAG_VALUE = "value";
	
	/**
	 * 实时数据updatecount
	 */
	public static final String RKP_TAG_UC = "updatecount";
	
	/**
	 * 创建时间 yyyy-MM-dd HH:mm:ss
	 */
	public static final String RKP_CREATE_DATE = "createdate";
	
	/**
	 * 描述 desc
	 */
	public static final String RKP_DESC = "desc";
	
	////////////////////////// auth //////////////////////////////////
	
	/**
	 * 权限前缀 auth
	 */
	public static final String RK_AUTH = "auth";
	
	/**
	 * 所有群组 groups
	 */
	public static final String RK_GROUPS = "groups";
	
	/**
	 * 群组成员 groupmem
	 */
	public static final String RK_GROUPMEM = "groupmem";
	
	public static final String RK_USERS = "users";
	
	public static final String RK_USERINFO = "userinfo";
	
	public static final String RK_USER_GROUPS = "usergroups";
	
	public static final String RK_AUTHITEMS = "authitems";
	
	/**
	 * 授权项详情 authinfo
	 */
	public static final String RK_AUTHINFO = "authinfo";
	
	/**
	 * 授权群组关系 authrel
	 */
	public static final String RK_AUTHREL = "authrel";
	
	////////////////////// channel //////////////////////////////
	
	/**
	 * 监听通道 agent
	 */
	public static final String RC_AGENT = "agent";
	
	/**
	 * 登录通道
	 */
	public static final String C_LOGIN = "login";
	
	/**
	 * 登录响应通道
	 */
	public static final String C_LOGIN_ACK = "login:ack";
	
	/**********************************************************
	 * DB中的hash field定义
	 *********************************************************/
	
	public static final String F_TAG_VALUE = "v";
	
	public static final String F_TAG_QUALITY = "q";
	
	public static final String F_TAG_TIMESTAMP = "t";
	
	public static final String F_TAG_UPDATEFLAG = "uc";
	

}
