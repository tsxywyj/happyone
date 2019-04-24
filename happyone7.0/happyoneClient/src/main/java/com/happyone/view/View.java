package com.happyone.view;

public class View {
	//欢迎视图
	public void welcome(){
		System.out.println("\t欢迎使用麦当鸡自助点餐系统");
		System.out.println("-----------------------------");
		System.out.println("1.用户登录");
		System.out.println("2.用户注册");
		System.out.println("3.职工登录");
		System.out.println("4.退出系统");
	}
	//用户登录成功后视图
	public void userLogin(){
		System.out.println("1.查看菜单");
		System.out.println("2.修改个人信息");
		System.out.println("3.查看活动");
		System.out.println("4.查看我的购物车");
		System.out.println("5.查看我的订单");
		System.out.println("6.查看个人信息");
		System.out.println("7.查看我的评价");
		System.out.println("8.返回上一层");
		System.out.println("0.直接退出系统");
	}
	//打印方法
	public void println(String msg){

		System.out.println(msg);
	}
	//下单
	public void buy(){
		System.out.println("1.我要下单");
		System.out.println("2.返回上一层");
	}
	

}
