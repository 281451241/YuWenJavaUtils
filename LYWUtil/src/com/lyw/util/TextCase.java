package com.lyw.util;

public class TextCase {
	
	/**
	 * 比系统的要快得多,当然必须保证传入的为大写
	 * @param ch
	 * @return
	 */
	public char UpToLowerCase(char ch) {
		if (ch >= 'A' && ch <= 'Z') {
			ch -= 'A' - 'a';  
		}
		return ch;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new TextCase().UpToLowerCase('G'));
	}
}
