package com.happyone.biz.impl;

import java.util.List;

import com.happyone.biz.inter.OrdersBiz;
import com.happyone.dao.impl.OrderDaoImpl;
import com.happyone.dao.inter.OrderDao;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public class OrderBizImpl implements OrdersBiz {
	private OrderDao odao;
	
	
	public OrderBizImpl() {
			super();
			this.odao = new OrderDaoImpl();
		}


//添加订单
	public String insertOrders(Users user, List<Product> list,int i) {
		
		return this.odao.insertOrders(user,list,i);
	}

//添加子订单
	public boolean insertSonOrder(List<Product> list, String orderno) {
		return this.odao.inserSonOrder(list,orderno);
	}

//查找固定用户的订单
	public List<Orders> selectAllOrder(Users u) {
		
		return this.odao.selectAllOrder(u);
	}

//查找未收货的订单
	public List<Orders> selectOrderUnreceived(Users u) {
		
		return this.odao.selectOrderUnreceived(u);
	}

//通过id查询订单
	public Orders selectOrderById(int id) {
		// TODO Auto-generated method stub
		return this.odao.selectOrderById(id);
	}

//改变订单状态
	public String alterOrderStatus(int int1) {
		
		return this.odao.alterOrderStatus(int1)?"收货成功":"收货失败";
	}

}
