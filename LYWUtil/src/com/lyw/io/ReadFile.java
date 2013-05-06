package com.lyw.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

	public boolean readName(File target) {
		boolean flag = false;
		try {
			if (target.isDirectory()) {
				File[] files = target.listFiles();
				for (File f : files) {
					readName(f);
				}
			} else {
				System.out.println(target.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void main(String[] args) {
		new ReadFile().readName(new File("D:/ant/vss2005"));
	}
}
