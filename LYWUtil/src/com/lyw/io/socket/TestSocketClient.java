package com.lyw.io.socket;

public class TestSocketClient
{

	public static void main(String[] args)
	{

//		SocketClient client = new SocketClient("http://www.eoeandroid.com", 80);
//		System.out.println(client.sendMsg("nimei1"));
//		client.closeSocket();
		SocketClient client1 = new SocketClient("127.0.0.1", 12345);
		System.out.println(client1.sendMsg("nimei1111"));
		client1.closeSocket();

		SocketClient client11 = new SocketClient("127.0.0.1", 12345);
		System.out.println(client11.sendMsg("nimei11111111"));
		client11.closeSocket();
		SocketClient client111 = new SocketClient("127.0.0.1", 12345);
		System.out.println(client111.sendMsg("ni           mei11111111111111111"));
		client111.closeSocket();

	}
}