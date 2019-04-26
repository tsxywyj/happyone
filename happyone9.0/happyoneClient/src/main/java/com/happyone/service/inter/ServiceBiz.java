package com.happyone.service.inter;

import java.util.List;

import com.happyone.domain.Activity;
import com.happyone.domain.Clerk;
import com.happyone.domain.Discuss;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.domain.Users;

public interface ServiceBiz {

	Users login(long tel, String password);

	String addUsers(Users u);

	Users updateUser(Users user, List<Product> list);

	Users selectUserByTel(long tel);

	String updateUserInfo(Users u);

	List<Product> selectSonOrderAll();

	List<Product> showPro();

	Product selectProById(int proid, int pronum);

	String insertOrders(Users user, List<Product> list, int i);

	boolean insertSonOrder(List<Product> list, String orderno);

	List<Orders> selectAllOrder(Users u);

	List<Orders> selectOrderUnreceived(Users u);

	Orders selectOrderById(int id);

	String alterOrderStatus(int int1);

	List<Activity> showAct(Users u);

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

	public List<Users> selectUsersAll();
	
	int selectProId(int actid);
	public Discuss selectDiscussByno(String orderNo);
	//插入评价
	public String insertDiscuss(Discuss dis);
}
