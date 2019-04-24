package com.happyone.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.happyone.service.impl.ServiceBizImpl;
import com.happyone.service.inter.ServiceBiz;

public class Control {
    
	private String ip = "";
	private int post = 6666;
	ServerSocket server = null;
	
	private ExecutorService es;
	private ServiceBiz sb;
	public Control() {
		try {
			this.server = new ServerSocket(post);
			System.out.println("正在开启服务器");
			for (int i = 0; i <= 4; i++) {
				System.out.print(".");
				Thread.sleep(200);

			}
			System.out.println();
			System.out.println("服务器已开启");
			this.sb= new ServiceBizImpl();
        
        	es=Executors.newCachedThreadPool();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void start() {
		while (true) {
		try {
			Socket client=server.accept();
			System.out.println("用户:"+client.getInetAddress().getHostAddress()+"已连接");
			ControlThread ct=new ControlThread(client,sb);
			
			
//			list.add(ct);
//			System.out.println("当前在线人数："+list.size());
//		    ct.start();
		    
		    es.submit(ct);
		    int many=((ThreadPoolExecutor)es).getActiveCount();
	        System.out.println("当前在线人数："+many);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
