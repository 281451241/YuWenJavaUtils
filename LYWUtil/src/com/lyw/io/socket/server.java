package com.lyw.io.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class server {
        private static int SERVER_POINT = 5778;
        private static server myserver;
        static ExecutorService serviceExecutors;
 
        public static void main(String[] args) {
                serviceExecutors = Executors.newFixedThreadPool(10);
                myserver = new server();
                new Thread() {
                        @Override
                        public void run() {
                                ServerSocket tcpSocket = null;
                                try {
 
                                        tcpSocket = new ServerSocket(SERVER_POINT);
                                        System.out.println("开始监听..." + SERVER_POINT);
                                        while (true) {
                                                Socket scoket = tcpSocket.accept();
                                                Thread clientThread=myserver.new SingleClientThread(scoket);
                                                serviceExecutors.execute(clientThread);
                                        }
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                        try {
                                                tcpSocket.close();
                                                System.out.println("停止监听..." + SERVER_POINT);
                                        } catch (IOException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                        }
                                }
 
                        }
                }.start();
        }
 
        class SingleClientThread extends Thread {
                Socket scoketClient;
 
                SingleClientThread(Socket client) {
                        this.scoketClient = client;
                }
 
                public void run() {
                        try {
                                System.out.println("新连接..." + scoketClient.toString());
                                DataInputStream is = new DataInputStream(
                                                scoketClient.getInputStream());
                                DataOutputStream os = new DataOutputStream(
                                                scoketClient.getOutputStream());
                                String getStr = is.readUTF();
                                System.out.println("得到字符串..." + getStr);
                                System.out.println("准备返回结果...");
                                os.writeUTF(scoketClient.toString()+"result_ok");
                                System.out.println("成功返回结果...");
                                os.close();
                                scoketClient.close();
                                System.out.println("连接关闭..." + SERVER_POINT);
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } 
                }
        }
}