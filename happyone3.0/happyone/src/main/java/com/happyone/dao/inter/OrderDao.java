package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface OrderDao {

//添加订单
	public String insertOrders(Users user, List<Product> list,int i);
//添加子订单
	public void inserSonOrder(List<Product> list, String orderno);
//查看该用户所有订单
	public List<Orders> selectAllOrder(Users u);
//查看未收货订单
	public List<Orders> selectOrderUnreceived(Users u);
//通过Id查询定订单
	public Orders selectOrderById(int id);
//修改订单状态	
	public boolean alterOrderStatus(int int1);

}
