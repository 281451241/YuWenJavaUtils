package com.lyw.util;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	
	Map entity = new HashMap();
	public Map test(boolean b) {
		
		
		entity.put("error", "0");
//		entity.put("success", 0);
		if(b) {
			entity.put("success", "1");
		} else {
			entity.put("error", "1");
		}
		return entity;
	}
	public static void main(String[] args) {
		TestMap tm = new TestMap();
		String error = (String) tm.test(true).get("error");
		System.out.println(tm.test(true).get("success"));
		System.out.println((String) tm.test(true).get("error") == "1");
	}
}
