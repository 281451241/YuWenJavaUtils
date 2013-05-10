package com.lyw.io.socket;

public class TestSocketServer
{

	public static void main(String[] argvs)
	{
		SocketServer server = new SocketServer(12345);
		server.beginListen();
	}
}
