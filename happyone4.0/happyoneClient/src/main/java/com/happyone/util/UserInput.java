package com.happyone.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

//工具类   用于处理用户输入信息的验证
public class UserInput {
	// 接收用户输入的内容String
	public String getString(String msg) {
		System.out.println(msg);
		Scanner sc = new Scanner(System.in);
		return sc.next();
		
	}
	public void println(String msg) {
		System.out.println(msg);

	}
	// 接收整数
	public int getInt(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				Scanner sc = new Scanner(System.in);
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入整数类型！");
			}
		}
	}
	//接收长整型
	public long getLong(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				Scanner sc = new Scanner(System.in);
				return sc.nextLong();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入正确的手机号！");
			}
		}
	}
	// 接收浮点数
	public double getDouble(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				Scanner sc = new Scanner(System.in);
				return sc.nextDouble();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入小数类型！");
			}
		}
	}

	// 获取订单号
	public String getUUID() {
		UUID uid = UUID.randomUUID();
		String s = uid.toString().replace("-", "").substring(0, 16);
		return s;
	}
	// 获取当前时间
	public Date getNewtime() {
		Date d = new Date();
		return d;
	}
    //时间转字符串
	public String setDateString(Date t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String s = df.format(t);
		return s;

	}
}
