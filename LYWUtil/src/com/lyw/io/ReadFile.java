package com.lyw.io;

import java.io.BufferedReader;
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
			while((string=br.readLine())!=null){
				str+=string;
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
}
