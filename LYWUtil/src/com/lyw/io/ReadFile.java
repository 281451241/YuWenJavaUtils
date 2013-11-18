package com.lyw.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadFile {
	public String read() {
		String string, str = null;
		try {
			FileReader in = new FileReader("E:/a.html");
			BufferedReader br = new BufferedReader(in);
			string = "";
			str = "";
			while ((string = br.readLine()) != null) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 读取文件
	 * 并根据其格式返回字符串
	 * @param fileName 文件绝对路径+文件名
	 * @return
	 */
	public String read(String fileName) {
		String string, str = null;
		try {
			FileReader in = new FileReader(fileName);
			BufferedReader br = new BufferedReader(in);
			string = "";
			str = "";
			while ((string = br.readLine()) != null) {
				str += string + '\n';
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	public LinkedList<String> read(String fileName, boolean flag) {
		String string, str = null;
		LinkedList<String> list = new LinkedList<String>();
		try {
			FileReader in = new FileReader(fileName);
			BufferedReader br = new BufferedReader(in);
			string = "";
			str = "";
			while ((string = br.readLine()) != null) {
				list.add(string);
//				str += string + '\n';
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private LinkedList<String> readName(File target, boolean onlyName) {
		LinkedList<String> list = new LinkedList<String>();
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					list.addAll(readName(f,onlyName));
				}
			} else {
				String path = target.getAbsolutePath();
				if(onlyName) 
					list.add(path.substring(path.lastIndexOf(File.separator)+1));
				else
					list.add(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private LinkedList<String> readName(File target, String startStr) {
		LinkedList<String> list = new LinkedList<String>();
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					list.addAll(readName(f, startStr));
				}
			} else {
				String path = target.getAbsolutePath();
				String name = path.substring(path.lastIndexOf(File.separator)+1);
				if(name.startsWith(startStr)) 
					list.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private LinkedList<String> readName(File target, String startStr, String endStr) {
		LinkedList<String> list = new LinkedList<String>();
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					list.addAll(readName(f, startStr, endStr));
				}
			} else {
				String path = target.getAbsolutePath();
				String name = path.substring(path.lastIndexOf(File.separator)+1);
				if(name.startsWith(startStr) && name.endsWith(endStr)) 
					list.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取目录下所有以startStr/endStr开头结尾的文件名(不包括路径)
	 * @param path
	 * @param startStr
	 * @param endStr
	 * @return
	 */
	public LinkedList<String> getFileList(String path, String startStr, String endStr) {
		return readName(new File(path), startStr, endStr);
	}
	
	/**
	 * 获取目录下所有以startStr开头的文件名(不包括路径)
	 * @param path
	 * @param startStr
	 * @return
	 */
	public LinkedList<String> getFileList(String path, String startStr) {
		return readName(new File(path), startStr);
	}
	
	/**
	 * 获取目录下所有的文件名(包括路径)
	 * 若变量为文件的话,则返回该文件名(包括路径)
	 * 若onlyName为true的话,则返回文件名(不包括路径)
	 * @param path
	 * @param onlyName
	 * @return
	 */
	public LinkedList<String> getFileList(String path, boolean onlyName) {
		return readName(new File(path), onlyName);
	}
	
	/**
	 * 获取目录下所有的文件名(包括路径)
	 * 若变量为文件的话,则返回该文件名(包括路径)
	 * 若onlyName为true的话,则返回文件名(不包括路径)
	 * @param target
	 * @param onlyName
	 * @return
	 */
	public LinkedList<String> getFileList(File target, boolean onlyName) {
		return readName(target, onlyName);
	}
	
	/**
	 * 获取目录下所有的文件名(包括路径)
	 * 若变量为文件的话,则返回该文件名(包括路径)
	 * @param target
	 * @return
	 */
	public LinkedList<String> getFileList(File target) {
		return readName(target, false);
	}
	
	public static void main(String[] args) {
//		for(String str : new ReadFile().getFileList("D:\\IDE", "m"))
//			System.out.println(str);
		ReadFile readFile = new ReadFile();
		
		LinkedList<String> list1 = readFile.read("D:/projects/AlphabeticIME_2/doc/语言中文名.txt", true);
		LinkedList<String> list2 = readFile.read("D:/projects/AlphabeticIME_2/doc/语言简称.txt", true);
		int count = list1.size();
		for(int i=0; i<count; i++) {
			System.out.print("<string name=\"app_full_name_");
			System.out.println(list2.get(i) + "\">" + list1.get(i)+"键盘</string>");
		}
		System.out.println(count);
	}
}
