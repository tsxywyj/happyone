package com.happyone.control;

import java.util.List;


import com.happyone.domain.Clerk;
import com.happyone.domain.SendOrders;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.UserInput;
import com.happyone.view.PutongClerkView;

public class SendClerkControl {
	private ServiceBiz sb;
	private UserInput ui;
	private PutongClerkView pcv;
	private Clerk clerk;
	

	public SendClerkControl(Clerk clerk,ServiceBiz sb) {
		// TODO Auto-generated constructor stub
		this.clerk = clerk;
		this.sb=sb;
		this.ui = new UserInput();
		this.pcv = new PutongClerkView();
		
	}

	public void sendClerkStart() {
		while (true) {
			this.pcv.sendClerkAll();
			int clerkStart = this.ui.getInt("请选择");
			switch (clerkStart) {
			case 1:
				this.selectNewSend();
				break;
			case 2:
				this.sendOK();
				break;
			case 3:
                this.selectAllSendorderByid();
				break;
			case 4:
                this.updatePutongClerk();
				break;
			case 5:

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

	public void selectNewSend() {
		List<SendOrders> list = this.sb.selectSendOrderByclerkid(clerk.getClerkId(), "配送中");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t\t\t订单电话\t\t订单地址\t配送员姓名\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).toString());
			}

		} else {
			System.out.println("暂无配送中的订单");
			return;
		}

	}

	public void sendOK() {
		List<SendOrders> list = this.sb.selectSendOrderByclerkid(clerk.getClerkId(), "配送中");
		if (list.size() != 0) {
			this.ui.println("订单编号\t订单号\t\t\t订单电话\t\t订单地址\t配送员姓名\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).toString());
			}

		} else {
			System.out.println("暂无配送中的订单");
			return;
		}
		int chooseSend = this.ui.getInt("请选择送达的配送订单");
		if (this.sb.updateSendOrder(list.get(chooseSend - 1).getSendOrdersId(), "已送达")
				&& this.sb.updateOrdersByNo(list.get(chooseSend - 1).getOrderNo(), "配送完成")) {
         this.ui.println("配送完成");
		}else{
			this.ui.println("配送失败");
			
		}

	}
	public void selectAllSendorderByid(){
		List<SendOrders> list = this.sb.selectSendOrderByclerkid(clerk.getClerkId(), "配送中");
		List<SendOrders> list2 = this.sb.selectSendOrderByclerkid(clerk.getClerkId(), "已送达");
		if (list.size() != 0||list2.size()!=0) {
			this.ui.println("订单编号\t订单号\t\t\t订单电话\t\t订单地址\t配送员姓名\t订单状态");
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "\t" + list.get(i).toString());
			}
			for (int i = 0; i < list2.size(); i++) {
				System.out.println((i + 1) + "\t" + list2.get(i).toString());
			}

		} else {
			System.out.println("暂无配送中的订单");
			return;
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
}
