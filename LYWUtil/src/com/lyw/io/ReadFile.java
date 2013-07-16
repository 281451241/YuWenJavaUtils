package com.lyw.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadFile {
	public void read() {
		String string, str = null;
		try {
			FileReader in = new FileReader("E:/a.html");
			BufferedReader br = new BufferedReader(in);
			string = "";
			str = "";
			while ((string = br.readLine()) != null) {
				str += string;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str);
	}
	
	LinkedList<String> list = new LinkedList<String>();
	private void readName(File target, boolean onlyName) {
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					readName(f,onlyName);
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
	}
	private void readName(File target, String startStr) {
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					readName(f, startStr);
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
	}
	private void readName(File target, String startStr, String endStr) {
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					readName(f, startStr, endStr);
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
	}
	
	public LinkedList<String> getFileList(String path, String startStr, String endStr) {
		File target = new File(path);
		readName(target, startStr, endStr);
		return list;
	}
	
	public LinkedList<String> getFileList(String path, String startStr) {
		File target = new File(path);
		readName(target, startStr);
		return list;
	}
	
	public LinkedList<String> getFileList(String path, boolean onlyName) {
		File target = new File(path);
		readName(target, onlyName);
		return list;
	}
	
	public LinkedList<String> getFileList(File target, boolean onlyName) {
		readName(target, onlyName);
		return list;
	}
	
	public LinkedList<String> getFileList(File target) {
		readName(target, false);
		return list;
	}
	
	public static void main(String[] args) {
		for(String str : new ReadFile().getFileList(new File("D:/IDE"), true))
			System.out.println(str);
	}
}
