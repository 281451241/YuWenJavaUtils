package com.lyw.pattern.singleton;

public class HungrySingleton {
	private HungrySingleton(){}
	private static HungrySingleton s = new HungrySingleton();
	public static HungrySingleton getSingleton() {
		return s;
	}
}
