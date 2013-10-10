package com.lyw.io;

import java.io.*;

public class WriteFile {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/test/a.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("您好！"+'\n');
		bw.write("您好！"+'\n');
		bw.write("您好！"+'\n');
		bw.write("您好！"+'\n');
		bw.close();

		FileInputStream fis = new FileInputStream("D:/test/a.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(br.readLine());
		br.close();
	}
	
	public final static BufferedWriter openFile(String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		return new BufferedWriter(osw);
	}
	
	public final static void closeFile(BufferedWriter writer) {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void writeToFile(String content, String fileName) {
		
	}
}