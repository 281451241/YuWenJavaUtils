package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import pojo.Users;

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
	
	public static void main(String[] args) {
		try {
			new TestReflect().loadClass();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
