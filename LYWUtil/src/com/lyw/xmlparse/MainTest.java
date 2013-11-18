package com.lyw.xmlparse;

import java.util.LinkedList;

import com.lyw.io.ReadFile;

/**
 * 构造语言列表<item></item>
 * @author GB
 *
 */
public class MainTest
{
	public static void main(String[] args)
	{
		int count = 0;
		ReadFile mReadFile = new ReadFile();
		LinkedList<String> list = mReadFile.getFileList(
				"D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml", 
				"language_", 
				".xml");
		for(String str : list) {
			System.out.print("<item>");
			System.out.print(DOMParseImpl.DOMParse("D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml/" + str).get(0).getName());
			System.out.print("_"+DOMParseImpl.DOMParse("D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml/" + str).get(0).getLength());
			System.out.println("</item>");
			count++;
		}
		System.out.println("count: " + count);
	}
}
