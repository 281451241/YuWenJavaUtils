package com.lyw.util;

import java.util.HashMap;
import java.util.LinkedList;
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
		LinkedHashMap lhMap = tm.new LinkedHashMap();
		
		lhMap.put("1", "value");
		lhMap.put("1", "value2");
		
		System.out.println(lhMap.get("1"));
		System.out.println(lhMap.remove("1"));
		System.out.println(lhMap.list.size());
//		String error = (String) tm.test(true).get("error");
//		System.out.println(tm.test(true).get("success"));
//		System.out.println((String) tm.test(true).get("error") == "1");
	}
	
	class LinkedHashMap<K, V> extends HashMap<K, V> {
		
		LinkedList<K> list;
		
		public LinkedHashMap() {
			list = new LinkedList<K>();
		}
		
		@Override
		public V put(K key, V value) {
			V v = super.put(key, value);
			if(v == value)
				list.add(key);
			return v;
		}
		
		@Override
		public V remove(Object key) {
			// TODO Auto-generated method stub
			V value = super.get(key);
			V v = super.remove(key);
			if(v == value)
				list.remove(key);
			return v;
		}
	}
}
