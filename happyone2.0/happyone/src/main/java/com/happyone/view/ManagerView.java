package com.happyone.view;

public class ManagerView {

	public ManagerView() {
		// TODO Auto-generated constructor stub
	}
	public void println(String msg) {
		System.out.println(msg);

	}
    public void managerViewAll(){
    	System.out.println("1.职工管理系统");
    	System.out.println("2.商品管理系统");
    	System.out.println("3.活动管理系统");
    	System.out.println("4.订单管理系统");
    	System.out.println("5.业绩统计");
    	System.out.println("0.返回登录页面");
    }
    public void managerViewClerk(){
    	System.out.println("欢迎进入职工管理系统");
    	System.out.println("1.查看所有职工");
    	System.out.println("2.按照手机号查找员工");
    	System.out.println("3.按照员工类型查找员工");
    	System.out.println("4.添加新的职工");
    	System.out.println("5.修改自己的职工信息");
    	System.out.println("6.修改职工工作类型");
    	System.out.println("0.返回上一层");
    }
    public void managerViewProduct(){
    	System.out.println("欢迎进入商品管理系统");
    	System.out.println("1.查看所有商品");
    	System.out.println("2.按照商品类型查找商品");
    	System.out.println("3.添加新的商品");
    	System.out.println("4.修改商品信息");
    	System.out.println("5.上架老商品");
    	System.out.println("6.下架在售商品");
    	System.out.println("0.返回上一层");
    }
    public void managerViewActivity(){
    	System.out.println("欢迎进入活动管理系统");
      	System.out.println("1.查看所有活动");
    	System.out.println("2.添加新的活动");
    	System.out.println("3.修改活动信息");
    	System.out.println("4.上架老活动");
    	System.out.println("5.下架新的活动");
    	System.out.println("0.返回上一层");
    }
    
    public void managerViewOrder(){
    	System.out.println("欢迎进入订单管理系统");
       	System.out.println("1.查看所有订单");
    	System.out.println("2.查看所有配送订单");
       	System.out.println("0.返回上一层");
    }
}
