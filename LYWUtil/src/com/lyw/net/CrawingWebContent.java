package com.lyw.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class CrawingWebContent {
	
	private final static String mBaseUrl = "http://book.kanunu.org/files/chinese/201104/2603/";

	private String mNextUriItem = "62542.html";

	public final static List<String> getWebContent(String domain, String charSet) {
		BufferedReader in = null;
		List<String> res = new LinkedList<String>();
		try {
			URL url = new URL(domain);
			in = new BufferedReader(new InputStreamReader(url.openStream(),
					charSet));

			String line;
			while ((line = in.readLine()) != null) {
				res.add(line);
			}
			
			System.out.println(domain + " 读取 ok!");
		} catch (Exception e) { // Report any errors that arise
			System.err.println("读取内容...( " + domain + " ) 失败");
			System.err.println(e);
		}
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String[] args) {
		CrawingWebContent g = new CrawingWebContent();

		List<String> content = getWebContent(mBaseUrl + g.mNextUriItem, "gbk");

		for (String line : content) {
			System.out.println(line);
		}
	}
}