package com.lyw.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lyw.io.WriteFile;

public class GetWebContent {
	// http://book.kanunu.org/files/chinese/201104/2603/62540.html
	// http://book.kanunu.org/files/chinese/201104/2603/
	private final static String mBaseUrl = "http://book.kanunu.org/files/chinese/201104/2603/";
	private final static String mNextIndexStr = "<a href='";
	private final static String suffix = ".txt";
	private int mFileName = 0;
	
	private boolean flag = false;
	
	private String mNextUriItem = "62540.html";
	
	public void getWebCon(String domain) {
		// System.out.println("开始读取内容...("+domain+")");
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			java.net.URL url = new java.net.URL(domain);
			in = new BufferedReader(new InputStreamReader(
					url.openStream(), "gbk"));
			
			out = new WriteFile().openFile("D:/test/" + mFileName + suffix);
			String line;
			while ((line = in.readLine()) != null) {
				if(line.contains("<p>"))
					flag = true;
				
				if(line.contains("</td>"))
					flag = false;
				
				if(flag) {
					out.write(line + "\n");
				}
				if(line.contains("下一页")) {
					System.out.println(line);
					mNextUriItem = line.substring(line.indexOf(mNextIndexStr)+ mNextIndexStr.length(), line.indexOf("'>"));
					System.out.println(mNextUriItem);
					mFileName++;
				}
			}
			System.out.println(domain + " 读取 ok!");
		} catch (Exception e) { // Report any errors that arise
//			sb.append(e.toString());
			System.out.println("读取内容...( "+domain+" ) 失败");
			System.err.println(e);
			System.err
					.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private final boolean hasNextPage(String str) {
		if(str == null || str.startsWith("../"))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		GetWebContent g = new GetWebContent();
		
		while (g.hasNextPage(g.mNextUriItem)) {
			g.getWebCon(mBaseUrl + g.mNextUriItem);
		}
	}
}