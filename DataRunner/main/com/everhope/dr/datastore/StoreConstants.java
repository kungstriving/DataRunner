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
			+ "	local fieldExist = redis.call('hexists', v, 'updateFlag');"
			+ "	if fieldExist == 1 then "
			+ "		local tmpTimeFlag = redis.call('hget', v, 'updateFlag');"
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
	 * DataEngine中key分隔符: 用户名称和数据源名称不能包含'_' ':'
	 */
	public static final String PDE_SEPERATOR = ":";
	
	/**
	 * tag标识
	 */
	public static final String PDE_TAG_KEY = "tag";
	
	/************************************************************
	 * DB中的key定义
	 *************************************************************/
	
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
	
	public static final String F_TAG_VALUE = "value";
	
	public static final String F_TAG_QUALITY = "quality";
	
	public static final String F_TAG_TIMESTAMP = "timestamp";
	
	public static final String F_TAG_UPDATEFLAG = "updateFlag";
	

}
