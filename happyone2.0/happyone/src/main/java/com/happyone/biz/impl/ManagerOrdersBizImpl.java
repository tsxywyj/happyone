package com.happyone.biz.impl;

import java.util.List;

import com.happyone.biz.inter.ManagerOrdersBiz;
import com.happyone.dao.impl.ManagerOrdersDaoImpl;
import com.happyone.dao.inter.ManagerOrdersDao;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;

public class ManagerOrdersBizImpl implements ManagerOrdersBiz {
	private ManagerOrdersDao mod;
	public ManagerOrdersBizImpl() {
		// TODO Auto-generated constructor stub
		mod = new ManagerOrdersDaoImpl();
	}
	public List<Orders> selectOrdersAll() {
		// TODO Auto-generated method stub
		return this.mod.selectOrdersAll();
	}
	public boolean updateOrdersByid(int orderId, String newType) {
		// TODO Auto-generated method stub
		if (this.mod.selectOrdersByid(orderId) != null) {
			return this.mod.updateOrderBytype(orderId, newType);
		} else {
			return false;
		}
	}
	public Orders selectOrdersByid(int orderId) {
		// TODO Auto-generated method stub
		return this.mod.selectOrdersByid(orderId);
	}
	public List<Orders> selectOrdersBytype(String type) {
		// TODO Auto-generated method stub
		return this.mod.selectOrdersBytype(type);
	}
	public String printOrder(int orderId) {
		// TODO Auto-generated method stub
		return this.mod.printOrder(orderId);
	}
	public List<Product> selectSonOrderByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return this.mod.selectSonOrderByOrderNo(orderNo);
	}
	public boolean insertSendOrder(String orderNo, int clerkId) {
		// TODO Auto-generated method stub
		return this.mod.insertSendOrder(orderNo, clerkId);
	}
	public boolean updateSendOrder(int sendOrderId, String type) {
		// TODO Auto-generated method stub
		return this.mod.updateSendOrder(sendOrderId, type);
	}
	public List<SendOrders> selectSendOrderAll() {
		// TODO Auto-generated method stub
		return this.mod.selectSendOrderAll();
	}
	public List<SendOrders> selectSendOrderByclerkid(int clerkID, String type) {
		// TODO Auto-generated method stub
		return this.mod.selectSendOrderByclerkid(clerkID, type);
	}
	public boolean updateOrdersByNo(String orderNo, String newType) {
		// TODO Auto-generated method stub
		return this.mod.updateOrdersByNo(orderNo, newType);
	}
	public List<SendOrders> selectSendOrderBytype(String type) {
		// TODO Auto-generated method stub
		return this.mod.selectSendOrderBytype(type);
	}
}
