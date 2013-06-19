package com.lyw.io.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.json.JSONObject;

public class Client
{
	private String hostname;
	private int port;

	public Client(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}

	public static void main(String[] args)
	{
		String hostname = "192.168.1.100";
		int port = 8234;
		Student stu = new Student();
		stu.setId(849);
		stu.setName("Squall");
		JSONObject obj = new JSONObject();
		obj.put("a", "bb");
		String jsonStr = obj.toString();
		Client client = new Client(hostname, port);
		try
		{
			client.write(jsonStr);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(Object obj) throws IOException
	{
		SocketChannel channel = null;
		try
		{
			channel = SocketChannel.open(new InetSocketAddress(hostname, port));
			ByteBuffer buf = Client.getByteBuffer(obj);
			channel.write(Client.getByteBuffer(obj));
			channel.write(Client.getByteBuffer(obj));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			channel.close();
		}
	}

	public static ByteBuffer getByteBuffer(Object obj) throws IOException
	{
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bOut);
		out.writeObject(obj);
		out.flush();
		byte[] arr = bOut.toByteArray();
		System.out.println("Object in " + arr.length + " bytes");
		ByteBuffer bb = ByteBuffer.wrap(arr);
		out.close();
		return bb;
	}
}