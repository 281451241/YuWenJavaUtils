package com.lyw.net;

import java.io.DataInputStream;

import java.io.IOException;

import java.net.MalformedURLException;

import java.net.URL;

public class UrlHtml {

	@SuppressWarnings("deprecation")
	public static void main(String[] s) throws IOException {

		try {

			URL url = new URL("http://book.kanunu.org/files/chinese/201104/2603/62540.html");

			DataInputStream in = new DataInputStream(url.openStream());

			String inputStream = null;

			inputStream = in.readLine();

			while (inputStream != null) {

				System.out.println(inputStream);

				inputStream = in.readLine();

			}

		} catch (MalformedURLException e) {

			// TODO 自动生成 catch 块

			e.printStackTrace();

		}

	}

}