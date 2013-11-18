package com.lyw.xmlparse;

import java.util.LinkedList;

import com.lyw.io.ReadFile;

public class MainGetFullName {

	public MainGetFullName() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		int count = 0;
		ReadFile mReadFile = new ReadFile();
		LinkedList<String> list = mReadFile.getFileList(
				"D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml",
				"language_", ".xml");

		for (String str : list) {
			String tempStr = DOMParseImpl
					.DOMParse(
							"D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml/"
									+ str).get(0).getName();

			System.out.print("<string name=\"app_full_name_");
			System.out.print(DOMParseImpl
					.DOMParse(
							"D:/projects/AlphabeticIME_2/AlphabeticIME_MASTER/res/xml/"
									+ str).get(0).getLength().toLowerCase());
			System.out.print("\">");
			System.out.print("Guobi ");
			if (tempStr.indexOf("(") < 0) {
				System.out.print(tempStr);
			} else
				System.out.print(tempStr.substring(tempStr.indexOf("(") + 1,
						tempStr.indexOf(")")));
			
			System.out.println(" Keyboard</string>");
			count++;
		}
		System.out.println("count: " + count);
	}
}
