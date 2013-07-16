package com.lyw.xmlparse;

import java.util.LinkedList;

import com.lyw.io.ReadFile;

public class MainTest
{
	public static void main(String[] args)
	{
		int count = 0;
		ReadFile mReadFile = new ReadFile();
		LinkedList<String> list = mReadFile.getFileList(
				"D:/projects/AlphabeticIME/res/xml", 
				"language_", 
				".xml");
		
		for(String str : list) {
			System.out.print("<item>");
			System.out.print(DOMParseImpl.DOMParse("D:/projects/AlphabeticIME/res/xml/" + str).get(0).getName());
			System.out.println("</item>");
			count++;
		}
		System.out.println("count: " + count);
	}
}
