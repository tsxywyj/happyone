package com.happyone.service.impl;

import java.util.List;

import com.happyone.biz.inter.ActiveBiz;
import com.happyone.biz.inter.ManagerActivityBiz;
import com.happyone.biz.inter.ManagerClerkBiz;
import com.happyone.biz.inter.ManagerOrdersBiz;
import com.happyone.biz.inter.ManagerProductBiz;
import com.happyone.biz.inter.OrdersBiz;
import com.happyone.biz.inter.ProductBiz;
import com.happyone.biz.inter.UserBiz;
import com.happyone.domain.Activity;
import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.domain.Users;

public interface ServiceBiz extends ManagerActivityBiz, ManagerClerkBiz, ManagerOrdersBiz, ManagerProductBiz, ActiveBiz,
		OrdersBiz, ProductBiz, UserBiz {

	Users login(long tel, String password);

	String addUsers(Users u);

	void updateUser(Users user, List<Product> list);

	Users selectUserByTel(long tel);

	String updateUserInfo(Users u);

	void showPro();

	Product selectProById(int proid, int pronum);

	String insertOrders(Users user, List<Product> list, int i);

	void insertSonOrder(List<Product> list, String orderno);

	List<Orders> selectAllOrder(Users u);

	List<Orders> selectOrderUnreceived(Users u);

	Orders selectOrderById(int id);

	String alterOrderStatus(int int1);

	void showAct(Users u);

	Product selectActiveById(int actid, int pronum);

	List<Product> selectProductAll();

	boolean downProductByid(int ProductId);

	boolean insertProduct(int productId, String productName, String productDescription, double productPrice,
			String isShelves);

	boolean upProductByid(int ProductId);

	boolean updateProductByid(int productId, String thing, int i);

	Product selectProductByid(int productId);

	List<Product> selectProductBytype(String type);

	List<Product> selectProductByis(String isShelves);

	List<Orders> selectOrdersAll();

	boolean updateOrdersByid(int orderId, String newType);

	Orders selectOrdersByid(int orderId);

	List<Orders> selectOrdersBytype(String type);

	String printOrder(int orderId);

	List<Product> selectSonOrderByOrderNo(String orderNo);

	boolean insertSendOrder(String orderNo, int clerkId);

	List<SendOrders> selectSendOrderAll();

	boolean updateSendOrder(int sendOrderId, String type);

	List<SendOrders> selectSendOrderByclerkid(int clerkID, String type);

	boolean updateOrdersByNo(String orderNo, String newType);

	List<SendOrders> selectSendOrderBytype(String type);

	Clerk denglu(long tel, String password);

	Clerk selectClerkBytel(long tel);

	Clerk selectClerkByid(int clerkId);

	List<Clerk> selectClerkAll();

	boolean updateClerkByid(int clerkId, String thing, int i);

	boolean updateClerkStatus(int clerkId, String Status);

	boolean insertClerk(int clerkId, String clerkName, String clerkType, long clerkTel, String password, String status);

	boolean deleteClerk(int clerkId);

	List<Clerk> selectClerkBytype(String type);

	Clerk selectSendClerkBytype(String type);

	List<Activity> selectActivityAll();

	Activity selectActivityByid(int activityId);

	boolean downActivityByid(int activityId);

	boolean upActivityByid(int activityId);

	boolean updateActivityByid(int activityId, String thing, int i);

	boolean insertActivity(String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId, String isshelves);

	List<Activity> selectActivityByis(String isShelves);

}
