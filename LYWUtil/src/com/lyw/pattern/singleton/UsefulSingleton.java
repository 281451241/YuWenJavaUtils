package com.lyw.pattern.singleton;

import java.io.Serializable;

public class UsefulSingleton implements Serializable{
	/**
	 * 能应对大多数情况的单例实现
	 */
	private static class SingletonHolder {  
		/**
		 * 单例对象实例
		 */
		static final UsefulSingleton INSTANCE = new UsefulSingleton();  
	}
	
	public static UsefulSingleton getInstance() {  
		return SingletonHolder.INSTANCE;  
	}  
	/**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
	private UsefulSingleton() {}  
	/**
     * readResolve方法应对单例对象被序列化时候
     */
	private Object readResolve() {  
		return getInstance();  
	}  
}
