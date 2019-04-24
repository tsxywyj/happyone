package com.happyone.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlThread extends Thread {

	private Socket client;
	public static final String ip = "10.10.49.106";
	public static final int post = 6666;
	private Object o;

	public ControlThread(Socket client, Object o) {
		super();

		this.client = client;
		this.o = o;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
        try {
			ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
	        String methodName=ois.readUTF();
	        Class<?>[] methodTypes=(Class<?>[]) ois.readObject();
	        Object[] methodParmes=(Object[]) ois.readObject();
	        Method method = o.getClass().getMethod(methodName, methodTypes);
	        Object result = method.invoke(o, methodParmes);
	        ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
	        oos.writeObject(result);
	        oos.flush();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
