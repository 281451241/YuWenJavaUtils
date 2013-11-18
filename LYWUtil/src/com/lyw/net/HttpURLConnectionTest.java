package com.lyw.net;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionTest {

	
	public static boolean testConnect(String surl) throws IOException {
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(3000);
//		connection.setReadTimeout(3000);
		try {
			connection.connect();
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
				return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	public static void testConnect2(String surl) throws IOException {
		URL url = new URL(surl);
		String userInfo = url.getUserInfo();
		if (userInfo != null && userInfo.length() > 0) {
			String string = surl.replace(userInfo, "");
			url = new URL(string);
		}
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		
		connection.setDoOutput(true);
		StringBuffer sb = new StringBuffer();
		sb.append("userName=" + "aaa");
		sb.append("&password=" + "bbb");
		//post信息
		OutputStream os = connection.getOutputStream();
		os.write(sb.toString().getBytes("GBK"));
		os.close();
		connection.connect();		
		System.out.println(connection.getResponseCode());//如果hhtp服务器需要验证时，输出401

	}
	

	public static void main(String args[]) throws IOException {
//		String url = "http://files.3g.guobi.cn/appfiles/GBIME/Guobi_Keyboards.apk";
		String url = "https://github.com/";
		System.out.println(testConnect(url));
	}

}

