package com.lyw.test;

public class TestString {
	
	private static char[] c = {'a','b','c'}; 
	private static String[] s = {"a","b","c"}; 
	private static String str = "good";
	private static int i = 2;
	
	private void isMatchWord() {
		boolean b = "你好你好你好".matches("\\w+");
		System.out.println(b);
	}
	
	private void isMatchWord(String str) {
		boolean b = "你好你好你好".matches("\\w+");
		b = str.matches("[\\d]{4}\\-[\\d]{1,2}\\-[\\d]{1,2}");
		System.out.println(b);
	}
	
	
	
	private void replace() {
		String str = "		id                            		INT(10)		 NOT NULL AUTO_INCREMENT	,".replaceAll("AUTO_INCREMENT", "AUTOINCREMENT");
		System.out.println(str);
	}
	
	private void testStr() {
		int i = 1;
		String str = " " + (i+1);
		System.out.println(str);
	}
	
	private void change(String str, int i) {
		str = "test ok";
		i = 1;
	}
	private void change() {	
		str = "test ok";
		i = 1;
	}
	public static void main(String[] args) {
		TestString ts = new TestString();
//		ts.isMatchWord("1991-2-1");
		ts.change(str, i);
		System.out.println(str + ":" + i);
		ts.change();
		System.out.println(str + ":" + i);
	}
}
