package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;

public interface ManagerOrdersDao {
//查看所有订单
	public List<Orders> selectOrdersAll();
	//修改订单状态
	public boolean updateOrderBytype(int orderId,String newType);
	//根据表id查询
	public Orders selectOrdersByid(int orderId);
	//根据订单状态查询
	public List<Orders> selectOrdersBytype(String type);
	//打印小票
	public String printOrder(int orderId);
	//查看子订单信息
	public List<Product> selectSonOrderByOrderNo(String orderNo); 
	//创建配送订单
	public boolean insertSendOrder(String orderNo,int clerkId);
	//查看配送中的配送订单
    public List<SendOrders> selectSendOrderAll();
 	//修改配送订单状态
	public boolean updateSendOrder(int sendOrderId,String type);
	//根据员工号和订单状态查看配送订单
	public List<SendOrders> selectSendOrderByclerkid(int clerkID,String type);
	//根据订单号修改订单状态
	public boolean updateOrdersByNo(String orderNo,String newType);
	//根据订单状态查看
	public List<SendOrders> selectSendOrderBytype(String type);
}
