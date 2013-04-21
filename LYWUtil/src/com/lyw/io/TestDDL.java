package com.lyw.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestDDL {
	
	public String f() {
		try {
			File file = new File("D://im.ddl");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader in = new BufferedReader(isr);
			String txt = null;
			
			while((txt = in.readLine()) != null) {
				int index = txt.indexOf("COMMENT");
				if(index != -1) {
					txt = txt.substring(0, txt.indexOf("COMMENT"));
					if(txt.length() > 2){
						txt+= ",";
					} else {
						txt += ";";
					}
				}
				txt = txt.replaceAll("AUTO_INCREMENT", "");
				txt = txt.replaceAll("DATE", "VARCHAR(50)");
				System.out.println(txt);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "nihao";
	}
	public static void main(String[] args) {
		TestDDL t = new TestDDL();
		t.f();
	}
}
