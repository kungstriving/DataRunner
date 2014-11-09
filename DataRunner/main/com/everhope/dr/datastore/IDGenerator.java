package com.everhope.dr.datastore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ID生成器，线程安全
 * 
 * @author kongxiaoyang
 * @date 2014年3月26日 上午10:51:57 
 * @version V1.0 
 */
public class IDGenerator {

	private static final AtomicInteger ID = new AtomicInteger();
	
	/**
	 * 线程安全获取新ID
	 * @return
	 */
	public static int getID() {
		return ID.getAndAdd(1);
	}
}
