package com.lyw.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class TestReflect {
	public void loadClass() throws Throwable {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class clazz = classLoader.loadClass("pojo.Users");	
		
		Constructor constructor = clazz.getDeclaredConstructor((Class[])null);
		Users users = (Users) constructor.newInstance();
		
		users.setAge("22");
		users.setId("01");
		users.setName("lyw");
		
		Method age = clazz.getMethod("setAge", String.class);
		age.invoke(users, "23");
		System.out.println(users);
	}
	
	private void testReflect() {
		try {
			Class<?> clazz = Class.forName("com.lyw.reflect.ReflectTarget");
			Constructor<?> constructor = clazz.getConstructor(new Class[]{String.class});
			ReflectTarget tar = (ReflectTarget)constructor.newInstance(new Object[] {"haha"});
			System.out.println(tar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
//			new TestReflect().loadClass();
			new TestReflect().testReflect();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
