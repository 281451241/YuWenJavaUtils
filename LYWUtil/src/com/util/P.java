package com.util;

public class P
{
	public static void print(String str)
	{
		System.out.println(str);
	}
	public static void p(String str)
	{
		System.out.print(str);
	}

	public static void print(int i)
	{
		System.out.println(i);
	}

	public static void print(char c)
	{
		System.out.println(c);
	}

	public static void print(boolean b)
	{
		System.out.println(b);
	}
	
	/**
	 * 打印数组并换行
	 * @param str
	 */
	public static void print(String[] str) {
		for(String s : str) {
			p(s);
		}
		print("");
	}
	
	/**
	 * 打印数组并以sep分隔
	 * @param str
	 * @param sep
	 */
	public static void print(String[] str, String sep) {
		
		for(int i=0,n=str.length; i<n; i++) {
			p(str[i]);
			if(i == n-1)
				break;
			p(sep);
		}
		print("");
	}
	
	
	/**
	 * 打印数组并以sep/startStr/endStr 分隔/开头/结尾
	 * @param str
	 * @param sep
	 * @param startStr
	 * @param endStr
	 */
	public static void print(String[] str, String sep, String startStr, String endStr) {
		p(startStr);
		for(int i=0,n=str.length; i<n; i++) {
			p(str[i]);
			if(i == n-1)
				break;
			p(sep);
		}
		p(endStr);
		print("");
	}
	
	/**
	 * 打印数组并以sep/startStr/endStr 分隔/开头/结尾
	 * 为每个元素前后加上roundStart/roundEnd
	 * @param str
	 * @param sep
	 * @param startStr
	 * @param endStr
	 * @param roundStart
	 * @param roundEnd
	 */
	public static void printSurroundBy(String[] str, String sep,
			String startStr, String endStr,
			String roundStart, String roundEnd) {
		p(startStr);
		for(int i=0,n=str.length; i<n; i++) {
			p(roundStart+str[i]+roundEnd);
			if(i == n-1)
				break;
			p(sep);
		}
		p(endStr);
		print("");
	}
	
	
}
