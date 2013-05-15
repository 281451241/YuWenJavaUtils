package com.lyw.io.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class TestSocket
{

	@Test
	public final void test() throws UnknownHostException, IOException
	{
		Socket socket = new Socket("www.eoeandroid.com", 80);
//		System.out.println(socket.getPort());
		new TestSocket().te(new String("sss"));
	}
	
	public final void te(Object obj) {}
	
	@Test
	public void f()
	{
		 //你想获取代码的网站
        String strServer = "softfile.3g.qq.com/android/35678/650488/2468_1001_3.1._android.apk";
        //起始页面，/为根页
        String strPage = "/android/35678/650488/2468_1001_3.1._android.apk";
        try
        {
            //设置端口，通常http端口不就是80罗，你在地址栏上没输就是这个值
            int port = 80;
            //用域名反向获得IP地址
//            InetAddress addr = InetAddress.getByName(strServer);
            
            //建立一个Socket 
            Socket socket = new Socket("3g.qq.com", port);
            //发送命令,无非就是在Socket发送流的基础上加多一些握手信息，详情请了解HTTP协议
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            wr.write("GET " + strPage + " HTTP/1.0\r\n");
            wr.write("HOST:" + strServer + "\r\n");
            wr.write("Accept:*/*\r\n");
            wr.write("\r\n");
            wr.flush();
            
            //接收Socket返回的结果,并打印出来
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                System.out.println(line);
            }
            wr.close();
            rd.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
