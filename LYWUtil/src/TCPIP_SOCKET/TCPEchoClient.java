package TCPIP_SOCKET;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.util.P;

public class TCPEchoClient
{
	public static void main(String[] args) throws Exception
	{
		new TCPEchoClient().f(new String[]{"127.0.0.1", "aaaaaa", "9999"});
	}
	public void f(String[] msg) throws Exception{
		if(msg.length<2 || msg.length>3)
			throw new IllegalAccessException("Parameter(s):<Server><Word>[<Port>]");
		
		String server = msg[0];
		byte[] data = msg[1].getBytes();
		int servPort = msg.length == 3 ? Integer.parseInt(msg[2]) : 7;
		
		Socket socket = new Socket(server, servPort);
		P.print("Connected to server....");
		
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		//发送字符串到回馈服务器
		out.write(data);
		
		
		
		int totalBytesRcvd = 0;
		int bytesRcvd;
		byte[] recevData = new byte[data.length];
		while (totalBytesRcvd < data.length) 
		{
			//从服务器接收回馈信息
//			if((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1)
//					throw new SocketException("Connection closed prematurely");
			if((bytesRcvd = in.read(recevData, totalBytesRcvd, data.length - totalBytesRcvd)) == -1)
				throw new SocketException("Connection closed prematurely");
			totalBytesRcvd += bytesRcvd;
//			totalBytesRcvd += bytesRcvd;
//			in.read(recevData);
		}
		
		P.print("Received: " + new String(recevData));
		
		socket.close();
	}
}
