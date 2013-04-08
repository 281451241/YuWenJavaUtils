package com.lzx.util.io;

import java.io.File;
import java.util.List;

public class Test extends AbstractCopyUtil{
	
	public Test(File sourceFile, File targetFile) {
		super(sourceFile, targetFile);
		// TODO Auto-generated constructor stub
	}

	public Test(String sourcePath, String targetPath) {
		super(sourcePath, targetPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String changeFolderTitle(File sourceFile, String allPath) {
		// TODO Auto-generated method stub
		System.out.println(allPath);
		return allPath+"bbb";
	}

	@Override
	public String changeFileTitle(File sourceFile, String allPath) {
		System.out.println(allPath);
		// TODO Auto-generated method stub
		return allPath+"aaaa";
	}

	@Override
	public String changeContent(String content) {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public String[] setCharPostfix(List<String> charPostfixList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] setBytePostfix(List<String> bytePostfixList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		Test test = new Test("D:/pic", "E:/pic");
	}

}
