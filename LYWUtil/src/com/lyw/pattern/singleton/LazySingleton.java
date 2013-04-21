package com.lyw.pattern.singleton;

public class LazySingleton {
	private LazySingleton(){}
	private static LazySingleton l = null;
	public static LazySingleton getSingleton() {
		if(l == null) {
			l = new LazySingleton();
		}
		return l;
	}
}
