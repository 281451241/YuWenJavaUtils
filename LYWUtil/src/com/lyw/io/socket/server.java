package com.lyw.io.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server
{
	private static int SERVER_POINT = 5778;
	private static server myserver;
	static ExecutorService serviceExecutors;

	public static void main(String[] args)
	{
		serviceExecutors = Executors.newFixedThreadPool(10);
		myserver = new server();
		new Thread()
		{
			@Override
			public void run()
			{
				ServerSocket tcpSocket = null;
				try
				{

					tcpSocket = new ServerSocket(SERVER_POINT);
					System.out.println("开始监听..." + SERVER_POINT);
					while (true)
					{
						Socket scoket = tcpSocket.accept();
						Thread clientThread = myserver.new SingleClientThread(
								scoket);
						serviceExecutors.execute(clientThread);
					}
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					try
					{
						tcpSocket.close();
						System.out.println("停止监听..." + SERVER_POINT);
					}
					catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}.start();
	}

	class SingleClientThread extends Thread
	{
		Socket scoketClient;

		SingleClientThread(Socket client)
		{
			this.scoketClient = client;
		}

		public void run()
		{
			try
			{
				System.out.println("新连接..." + scoketClient.toString());
				ObjectInputStream is = new ObjectInputStream(
						scoketClient.getInputStream());
				DataOutputStream os = new DataOutputStream(
						scoketClient.getOutputStream());
				User u = null;
				try
				{
					if(is.readObject() instanceof User)
						u = (User) is.readObject();
					System.out.println("得到字符串..." + u.getName());
				}
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("准备返回结果...");
				os.writeUTF(scoketClient.toString() + "result_ok");
				System.out.println("成功返回结果...");
				os.close();
				scoketClient.close();
				System.out.println("连接关闭..." + SERVER_POINT);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}