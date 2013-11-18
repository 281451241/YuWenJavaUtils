package com.lyw.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.lyw.db.mybatis.User;


public class TestReflect {
	public void loadClass() throws Throwable {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class clazz = classLoader.loadClass("User");	
		
		Constructor constructor = clazz.getDeclaredConstructor((Class[])null);
		User users = (User) constructor.newInstance();
		
		users.setUserId(22);
		users.setPassword("01");
		users.setUserName("lyw");
		
		Method age = clazz.getMethod("setComment", String.class);
		age.invoke(users, "23");
		System.out.println(users);
	}
	
	public static void main(String[] args) {
		try {
			new TestReflect().loadClass();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
