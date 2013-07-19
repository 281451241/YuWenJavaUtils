package com.lyw.util;


public class TextReplace
{
	/**
	 * 用数组中index字符替换
	 * 字符串中对应的第index个find字符
	 * @param prev
	 * @param find
	 * @param want
	 * @return
	 */
	private String replaceString(String prev, String find, String[] want) {
		String res = prev;
		for(int i=0; i<want.length; i++) {
			res = res.replaceFirst(find, want[i]);
		}
		return res;
	}
	
	/**
	 * 为数组项目字符首尾加上startStr/endStr
	 * @param str
	 * @param startStr
	 * @param end
	 * @return
	 */
	private static String[] replaceString(String[] str, String startStr, String endStr) {
		
		int length = str.length;
		for(int i=0; i<length; i++) {
			str[i] = startStr + str[i] + endStr;
		}
		
		return str;
	}
	
	
	/**
	 * 测试用
	 */
	private void replaceTest() {
		String str = "mSymbol[index++],mBaseKey_R2,MajinKeyCodeDef.SYM_NORMAL), ";
		//这里不能用replaceAll, 因为会用到正则表达式相关的东西,
		//导致[index++]匹配的是"e"
		String res = str.replace("[index++]", "..."); 
		
		System.out.println(res);
	}
	
	public static void main(String[] args)
	{
//		String prevStr = new ReadFile().read("D:/test/MajinControllerSymPageThree.java");
//		System.out.println(prevStr);
//		String[] sym = DOMParseImpl.DOMParse("D:/other/xml/majin_string.xml", "string-array", "item");
//		int symLen = sym.length;
//		String[][] syms = new String[symLen][];
//		for(int i=0; i<symLen; i++) {
//			System.out.println(sym[i]);
//			syms[i] = replaceString(sym[i].split(" "), "\"","\"");
//		}		
//		System.out.println(new TextReplace().f(prevStr, "mSymbol0000000", syms[3]));
		
		new TextReplace().replaceTest(); // 测试用代码
	}
	
	private String[] mSymbolArray;
	private int index = 0;
}
