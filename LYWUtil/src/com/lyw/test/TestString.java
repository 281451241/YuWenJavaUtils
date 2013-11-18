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
	
	/**
	 * 字符串匹配数字
	 */
	private static void isMatchNumber() {
		long cur = System.currentTimeMillis();
		boolean b = "15".matches("\\d+");
		System.out.println(System.currentTimeMillis() - cur);
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
	
	private void test() {
		String packageName = "com.guobi.gbime.alph";
		String packageFullName = "com.guobi.gbime.alph.ca";
		
		System.out.println(packageFullName.substring(packageName.length() + 1));
		System.out.println(packageFullName.substring(packageFullName.lastIndexOf(".") + 1));
	}
	
	public class Goding {
		//获取字符的unicode编码
		public void g() {
		char word = 'a';
		char word1 = 'Y';
		char word2 = 'k';
		char word3 ='l';
		int p = 211;
		int p1 = 76;
		int p2 = 79;
		int p3=36;
		System.out.println("a在unicode表中的顺序位置是：" + (int) word); 
		System.out.println("Y在unicode表中的顺序位置是：" + (int) word1); 
		System.out.println("k在unicode表中的顺序位置是：" + (int) word2);
		System.out.println("l在unicode表中的顺序位置是：" + (int) word3);
		System.out.println("unicode表中的第211位是：" + (char) p);
		System.out.println("unicode表中的第76位是：" + (char) p1);
		System.out.println("unicode表中的第79位是：" + (char) p2);
		System.out.println("unicode表中的第36位是：" + (char) p3);
		}
		}
	
	public static void main(String[] args) {
//		TestString ts = new TestString();
//		ts.test();
		
//		ts.new Goding().g();
		
		System.out.println("\u4fd3");
//		isMatchNumber();
//		ts.isMatchWord("1991-2-1");
//		Integer.parseInt(null);
//		ts.change(str, i);
//		System.out.println(str + ":" + i);
//		ts.change();
//		System.out.println(str + ":" + i);
	}
}
