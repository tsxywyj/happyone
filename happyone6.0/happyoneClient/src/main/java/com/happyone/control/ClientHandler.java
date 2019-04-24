package com.happyone.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientHandler implements InvocationHandler {
	private String ip;
	private int post;

	public ClientHandler(String ip, int post) {
		super();
		this.ip = ip;
		this.post = post;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Socket client = new Socket(ip, post);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
	    
		oos.writeUTF(method.getName());
		oos.flush();
		oos.writeObject(method.getParameterTypes());
		oos.writeObject(args);
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

		return ois.readObject();
	
	}

	
	

}
