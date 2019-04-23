package com.happyone.biz.inter;

import java.util.List;

import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface OrdersBiz {

	//添加订单
	public String insertOrders(Users user, List<Product> list,int i);

	//添加子订单
	public void insertSonOrder(List<Product> list, String orderno);
//查看所有订单
	public List<Orders> selectAllOrder(Users u);
//查看未收货的订单
	public List<Orders> selectOrderUnreceived(Users u);
//通过订单id查找订单

	public Orders selectOrderById(int id);
//改变订单状态
	public String alterOrderStatus(int int1);
	

}
