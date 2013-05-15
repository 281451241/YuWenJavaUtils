package com.lyw.io.socket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrintServlet")
public class PrintServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String age = new String(request.getParameter("age").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("姓名:"+name+"\n年龄："+age);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("姓名:"+request.getParameter("name")+"\n年龄："+request.getParameter("age"));
	}
}
