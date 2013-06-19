package com.lyw.io.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class server
{
	public static void main(String[] args)
	{
		System.out.println("in server!");
		ServerThread server = new ServerThread();
		new Thread(server).start();
	}

	static class ServerThread implements Runnable
	{
		public void run()
		{
			try
			{
				ServerSocketChannel sc = ServerSocketChannel.open();
				ServerSocket s = sc.socket();
				s.bind(new InetSocketAddress(8234));
				while (true)
				{
					Socket incoming = s.accept();
					Runnable r = new GetObjThread(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	static class GetObjThread implements Runnable
	{
		public GetObjThread(Socket s)
		{
			incoming = s;
		}

		public void run()
		{
			try
			{
				SocketChannel sc = incoming.getChannel();
				ByteBuffer bbIn = ByteBuffer.allocate(1024);
				sc.read(bbIn);
				sc.close();
				bbIn.flip();
				ByteArrayInputStream bIn = new ByteArrayInputStream(
						bbIn.array());
				ObjectInputStream in = new ObjectInputStream(bIn);
				String nStu = (String) in.readObject();
				System.out.println("student id is " + nStu+ "\n"
						+ "student name is ");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		private Socket incoming;
	}
}