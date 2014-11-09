package com.everhope.nost.models.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.everhope.nost.exceptions.LoginException;
import com.everhope.nost.models.User;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月13日 下午1:52:12 
 * @version V1.0 
 */
public class UserTest {

	private static User testUser = null;
	
	@BeforeClass
	public static void init() {
		
	}
	
	@AfterClass
	public static void done() {
		
	}
	
	@Test
	public void testLoadPage() {
		testUser = new User("root", "root");
		try {
			String res = testUser.loadPage("F:\\git\\Everhope\\nono\\WebContent\\pages\\tom");
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
//	@Test
	public void testLogin() {
		testUser = new User("admin", "admin");
		try {
			testUser.login();
		} catch (LoginException e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
