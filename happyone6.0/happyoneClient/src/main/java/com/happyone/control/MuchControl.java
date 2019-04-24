package com.happyone.control;

import java.util.ArrayList;
import java.util.List;

import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.domain.Users;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.UserInput;
import com.happyone.view.ManagerView;

public class MuchControl {
	private ServiceBiz sb;
	private ManagerView mv;
	private UserInput ui;

	public MuchControl(ServiceBiz sb) {
		// TODO Auto-generated constructor stub
		this.sb = sb;
		this.mv = new ManagerView();
		this.ui = new UserInput();
	}

	// System.out.println("2.本月最大的订单");
	// System.out.println("3.最忙碌的配送员");
	// System.out.println("4.按照会员等级查询会员");
	// System.out.println("5.最受欢迎的商品");
	// System.out.println("0.返回上一层");
	public void muchStart() {
		while (true) {
			this.mv.managerViewMuch();
			int chooseMuch = this.ui.getInt("请选择");
			switch (chooseMuch) {
			case 1:
				this.maxUser();
				break;

			case 2:
				this.maxOrder();
				break;
			case 3:
				this.busySend();
				break;
			case 4:
				this.usersLevel();
				break;
			case 5:
				this.goodProduct();
				break;
			}
			if (chooseMuch == 0) {

				return;
			}
		}

	}

	public void maxUser() {
		List<Users> list = this.sb.selectUsersAll();
		int jifen = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIntegral() > jifen) {
				jifen = list.get(i).getIntegral();
			}

		}
		Users user = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIntegral() == jifen) {
				user = list.get(i);
			}
		}
		this.mv.println("历史鸡王是:" + user.getUsername() + "  他的积分为：" + user.getIntegral());
	}

	public void maxOrder() {
		int month = this.ui.getInt("输入查询的月份");
		List<Orders> list = this.sb.selectOrdersAll();
		List<Orders> newlist = new ArrayList<Orders>();
		for (int i = 0; i < list.size(); i++) {
			String time = list.get(i).getOrderDate().toString();
			String[] arr = time.split("-");
			int m = Integer.parseInt(arr[1]);
			if (m == month) {
				newlist.add(list.get(i));
			}
		}
		if (newlist.size() == 0) {
			this.ui.println("该月没有订单");
			return;
		}
		Orders order = null;
		double sum = 0;
		for (int i = 0; i < newlist.size(); i++) {
			if (newlist.get(i).getSumPrice() > sum) {
				sum = newlist.get(i).getSumPrice();
			}
		}
		for (int i = 0; i < newlist.size(); i++) {
			if (newlist.get(i).getSumPrice() == sum) {
				order = newlist.get(i);
			}
		}
		this.ui.println(month + "月最大的订单是：");
		this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
		this.ui.println(order.toString());

	}

	public void busySend() {
		List<Clerk> clist = this.sb.selectClerkBytype("配送员");
		List<SendOrders> slist = this.sb.selectSendOrderAll();
		int[] arr = new int[clist.size()];
		for (int i = 0; i < clist.size(); i++) {
			int many = 0;
			for (int j = 0; j < slist.size(); j++) {
				if (clist.get(i).getClerkName().equals(slist.get(j).getClerkName())) {
					many++;
				}
			}
			arr[i] = many;
		}
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > temp) {
				temp = arr[i];
			}
		}
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (temp == arr[i]) {
				index = i;
			}
		}
		this.ui.println("配送订单最多的配送员是：" + clist.get(index).getClerkName() + "   他配送了" + temp + "单，大家要向他努力哦");
	}
	public void usersLevel() {
		int level = this.ui.getInt("请输入会员等级");
		if (level < 1 || level > 5) {
			System.out.println("会员等级不合法");
			return;
		}
		List<Users> list = this.sb.selectUsersAll();
        List<Users> newlist=new ArrayList<Users>();
        for(int i=0;i<list.size();i++){
        	if(level==list.get(i).getMembertypeid()){
        	newlist.add(list.get(i));}
        }
        // this.getUsername()+"\t\t"+this.getTel()+"\t\t"+this.getPassword()+"\t"+this.getMembertypeid()+"\t\t"+this.getIntegral()+"\t\t"+this.getAddress();
        if(newlist.size()==0){
        	this.ui.println("没有"+level+"级会员");
        	return;
        }
        this.ui.println("会员姓名\t会员手机\t会员等级\t会员积分");
        for(int i=0;i<newlist.size();i++){
        	this.ui.println(newlist.get(i).getUsername()+"\t"+newlist.get(i).getTel()+"\t\t"+newlist.get(i).getMembertypeid()+"\t"+newlist.get(i).getIntegral());
        	
        }
 	}
	public void goodProduct() {
		List<Product> sonlist = this.sb.selectSonOrderAll();
		List<Product> plist = this.sb.selectProductAll();
		int[] arr = new int[plist.size()];
		for (int i = 0; i < plist.size(); i++) {
			int many = 0;
			for (int j = 0; j < sonlist.size(); j++) {
				if (plist.get(i).getProductId() == sonlist.get(j).getProductId()) {
					many = many + sonlist.get(j).getProductNum();
				}
				arr[i] = many;
			}
		}
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > temp) {
				temp = arr[i];
			}
		}
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (temp == arr[i]) {
				index = i;
			}
		}
		this.ui.println("最受欢迎的商品是：" + plist.get(index).getProductName() + "   累计销售了" + temp + "件");

	}
}
