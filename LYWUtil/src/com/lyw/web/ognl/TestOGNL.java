package com.lyw.web.ognl;

import org.junit.Test;

import ognl.Ognl;
import ognl.OgnlException;

import com.lyw.db.mybatis.User;

public class TestOGNL {
	
	@Test
	public void testSetValue() throws Exception {
		User u = new User();
		u.setUserId(1);
		u.setUserName("liang");
		u.setPassword("123456");
		
		Ognl.setValue("comment", u, "dev");
		
		System.out.println(u.getComment());
	}
}
