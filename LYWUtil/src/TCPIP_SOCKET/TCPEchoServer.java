package TCPIP_SOCKET;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import com.util.P;

public class TCPEchoServer
{
	private static final int BUFSIZE = 32;

	private void f(int servPort) throws Exception
	{
		if(servPort <= 0)
			throw new IllegalAccessException("Parameter(s): <Port>"); 
		
		ServerSocket servSocket = new ServerSocket(servPort);
		
		int recvMsgSize;
		byte[] receiveBuf = new byte[BUFSIZE];
		
		while(true){
			Socket clntSock = servSocket.accept();
			SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
			
			P.print("Handling client at " + clientAddress);
			
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			
			while((recvMsgSize = in.read(receiveBuf)) != -1)
			{
				out.write(receiveBuf, 0, recvMsgSize);
			}
			P.print("Received: " + new String(receiveBuf));
			clntSock.close();
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			new TCPEchoServer().f(9999);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
