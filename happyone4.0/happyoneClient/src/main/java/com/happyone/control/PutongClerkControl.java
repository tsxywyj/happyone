package com.happyone.control;

import java.util.List;


import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.UserInput;
import com.happyone.view.PutongClerkView;

public class PutongClerkControl {
	private ServiceBiz sb;
	private UserInput ui;
	private PutongClerkView pcv;
	private Clerk clerk;
	

	public PutongClerkControl(Clerk clerk,ServiceBiz sb) {
		// TODO Auto-generated constructor stub
		this.clerk = clerk;
		this.sb=sb;
		this.ui = new UserInput();
		this.pcv = new PutongClerkView();
		
	}

	public void putongClerkStart() {
		while (true) {
			this.pcv.PutongClerkAll();
			int clerkStart = this.ui.getInt("请选择");
			switch (clerkStart) {
			case 1:
				this.selectOrderBytype();
				break;
			case 2:
				this.printlnSonOrder();
				break;
			case 3:
				this.Send();
				break;
			case 4:
				this.updatePutongClerk();
				break;
			case 5:
				this.selectSendOrder();
				break;
			default:
				break;
			}

			if (clerkStart == 0) {
				this.sb.updateClerkStatus(clerk.getClerkId(), "下班");
				return;
			}

		}

	}

	// 查看已付款订单
	public void selectOrderBytype() {
		List<Orders> list = this.sb.selectOrdersBytype("已付款");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).getOrderNo() + "\t" + list.get(i).getUserId() + "\t"
						+ list.get(i).getOrderDate() + "\t" + list.get(i).getSumPrice() + "\t" + list.get(i).getIsSend()
						+ "\t" + list.get(i).getStatus());
			}

		} else {
			System.out.println("暂无交易中的订单");
			return;
		}

	}

	// 打印小票
	public void printlnSonOrder() {
		List<Orders> list = this.sb.selectOrdersBytype("已付款");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).getOrderNo() + "\t" + list.get(i).getUserId() + "\t"
						+ list.get(i).getOrderDate() + "\t" + list.get(i).getSumPrice() + "\t" + list.get(i).getIsSend()
						+ "\t" + list.get(i).getStatus());
			}

		} else {
			System.out.println("暂无交易中的订单");
			return;
		}
		int chooseOrderid = this.ui.getInt("请选择订单编号");
		String orderNo = list.get(chooseOrderid - 1).getOrderNo();

		this.ui.println("麦当鸡etc欢迎您");
		this.ui.println("--------------------------");
		this.ui.println("交易号:" + list.get(chooseOrderid - 1).getOrderNo());
		this.ui.println("收银员:" + clerk.getClerkName() + "  交易时间" + list.get(chooseOrderid - 1).getOrderDate());
		this.ui.println("商品如下");
		List<Product> plist = this.sb.selectSonOrderByOrderNo(list.get(chooseOrderid - 1).getOrderNo());
		this.ui.println("商品编号\t商品名称\t商品数量\t商品总价");
		for (int i = 0; i < plist.size(); i++) {
			System.out.println((i + 1) + "\t" + plist.get(i).getProductName() + "\t" + plist.get(i).getProductNum()
					+ "\t" + plist.get(i).getProductPrice());
		}
		this.ui.println("商品总价:" + list.get(chooseOrderid - 1).getSumPrice());
		this.ui.println("是否需要配送:" + list.get(chooseOrderid - 1).getIsSend());
		this.ui.println("用户积分增加:" + (int) list.get(chooseOrderid - 1).getSumPrice());
		if (list.get(chooseOrderid - 1).getIsSend().equals("否")) {
			this.sb.updateOrdersByid(list.get(chooseOrderid - 1).getOrderId(), "交易完成");
			this.ui.println("打印完成");
		} else {
			// 生成配送订单
			this.sb.updateOrdersByid(list.get(chooseOrderid - 1).getOrderId(), "等待配送");
			this.ui.println("打印完成,请及时安排配送");
		}
	}

	// 安排配送
	public void Send() {
		List<Orders> list = this.sb.selectOrdersBytype("等待配送");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).getOrderNo() + "\t" + list.get(i).getUserId() + "\t"
						+ list.get(i).getOrderDate() + "\t" + list.get(i).getSumPrice() + "\t" + list.get(i).getIsSend()
						+ "\t" + list.get(i).getStatus());
			}

		} else {
			System.out.println("暂无需要安排配送的订单");
			return;
		}
		int chooseOrder = this.ui.getInt("选择订单编号");
		String orderNo = list.get(chooseOrder - 1).getOrderNo();
		this.ui.println("推荐空闲的配送员");
		Clerk sendClerk = this.sb.selectSendClerkBytype("工作中空闲");
		if (sendClerk == null) {
			this.ui.println("暂时没有空闲的配送员，请稍后再试");
			return;
		}
		this.ui.println("配送员信息如下");
		this.ui.println("职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
		this.ui.println(sendClerk.toString());
		if (this.sb.insertSendOrder(orderNo, sendClerk.getClerkId())
				&& this.sb.updateClerkStatus(sendClerk.getClerkId(), "工作中忙碌")
				&& this.sb.updateOrdersByid(list.get(chooseOrder - 1).getOrderId(), "配送中")) {

			this.ui.println("安排配送成功");
		} else {
			this.ui.println("安排配送失败");

		}

	}

	// 修改个人信息
	public void updatePutongClerk() {
		System.out.println("您的信息如下");
		this.ui.println("职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
		this.ui.println(clerk.toString());
		this.ui.println("请选择您要修改的信息字段");
		this.ui.println("1.职工姓名");
		this.ui.println("2.职工手机号");
		this.ui.println("3.职工密码");
		int updatei = this.ui.getInt("请选择");
		String updates = this.ui.getString("请输入新的信息");
		if (this.sb.updateClerkByid(clerk.getClerkId(), updates, updatei)) {
			System.out.println("修改成功,重新登陆后生效");

		} else {
			System.out.println("修改失败");
		}
	}

	public void selectSendOrder() {
		List<SendOrders> list = this.sb.selectSendOrderBytype("配送中");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t订单电话\t\t订单地址\t配送员姓名\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).toString());
			}

		} else {
			System.out.println("暂无交易中的订单");
			return;
		}
	}
}
