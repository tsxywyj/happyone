package com.happyone.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.impl.ActiveDaoImpl;
import com.happyone.dao.impl.ManagerActivityDaoImpl;
import com.happyone.dao.impl.ManagerClerkDaoImpl;
import com.happyone.dao.impl.ManagerOrdersDaoImpl;
import com.happyone.dao.impl.ManagerProductDaoImpl;
import com.happyone.dao.impl.OrderDaoImpl;
import com.happyone.dao.impl.ProductDaoImpl;
import com.happyone.dao.impl.UserDaoImpl;
import com.happyone.dao.inter.ActiveDao;
import com.happyone.dao.inter.ManagerActivityDao;
import com.happyone.dao.inter.ManagerClerkDao;
import com.happyone.dao.inter.ManagerOrdersDao;
import com.happyone.dao.inter.ManagerProductDao;
import com.happyone.dao.inter.OrderDao;
import com.happyone.dao.inter.ProductDao;
import com.happyone.dao.inter.UserDao;
import com.happyone.domain.Activity;
import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.domain.Users;
import com.happyone.service.inter.ServiceBiz;

public class ServiceBizImpl implements ServiceBiz{

	private ActiveDao adao;
	private ManagerActivityDao mao;
	private ManagerClerkDao mcd;
	private ManagerOrdersDao mod;
	private ManagerProductDao mpd;
	private OrderDao odao;
	private ProductDao pdao;
	private UserDao udao;

	public ServiceBizImpl() {
		super();
		this.adao = new ActiveDaoImpl();
		this.mao = new ManagerActivityDaoImpl();
		this.mcd = new ManagerClerkDaoImpl();
		this.mod = new ManagerOrdersDaoImpl();
		this.mpd = new ManagerProductDaoImpl();
		this.odao = new OrderDaoImpl();
		this.pdao = new ProductDaoImpl();
		this.udao = new UserDaoImpl();
	}

	// 用户登录
	public Users login(long tel, String password) {
		Users user = udao.selectUserByTel(tel);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	// 用户的注册
	public String addUsers(Users u) {

		return this.udao.insertUser(u) ? "注册成功" : "注册失败";
	}

	// 更新会员积分和会员类型编号
	public Users updateUser(Users user, List<Product> list) {
		return this.udao.updateUser(user, list);
	}

	public String updateUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	// 通过tel查找用户
	public Users selectUserByTel(long tel) {
		// TODO Auto-generated method stub
		return this.udao.selectUserByTel(tel);
	}

	// 修改用户信息
	public String updateUserInfo(Users u) {

		return this.udao.updateUserInfo(u) ? "修改成功" : "修改失败";
	}

	// 显示菜单
	public List<Product> showPro() {
		return this.pdao.selectAllPro();
		
	}

	// 通过id查询商品信息
	public Product selectProById(int proid, int pronum) {

		return this.pdao.selectProById(proid, pronum);
	}

	// 添加订单
	public String insertOrders(Users user, List<Product> list, int i) {

		return this.odao.insertOrders(user, list, i);
	}

	// 添加子订单
	public boolean insertSonOrder(List<Product> list, String orderno) {
		return this.odao.inserSonOrder(list,orderno);
	}

	// 查找固定用户的订单
	public List<Orders> selectAllOrder(Users u) {

		return this.odao.selectAllOrder(u);
	}

	// 查找未收货的订单
	public List<Orders> selectOrderUnreceived(Users u) {

		return this.odao.selectOrderUnreceived(u);
	}

	// 通过id查询订单
	public Orders selectOrderById(int id) {
		// TODO Auto-generated method stub
		return this.odao.selectOrderById(id);
	}

	// 改变订单状态
	public String alterOrderStatus(int int1) {

		return this.odao.alterOrderStatus(int1) ? "收货成功" : "收货失败";
	}

	public List<Product> selectProductAll() {
		// TODO Auto-generated method stub
		return this.mpd.selectProductAll();
	}

	public boolean downProductByid(int ProductId) {
		// TODO Auto-generated method stub
		if (this.mpd.selectProductByid(ProductId) != null) {
			return this.mpd.downProductByid(ProductId);

		} else {
			return false;
		}
	}

	public boolean insertProduct(int productId, String productName, String productDescription, double productPrice,
			String isShelves) {
		// TODO Auto-generated method stub
		return this.mpd
				.insertProduct(new Product(productId, productName, productDescription, 1, productPrice, isShelves));
	}

	public boolean upProductByid(int ProductId) {
		// TODO Auto-generated method stub
		if (this.mpd.selectProductByid(ProductId) != null) {
			return this.mpd.upProductByid(ProductId);

		} else {
			return false;
		}
	}

	public boolean updateProductByid(int productId, String thing, int i) {
		// TODO Auto-generated method stub
		if (this.mpd.selectProductByid(productId) != null) {
			return this.mpd.updateProductByid(productId, thing, i);

		} else {
			return false;
		}
	}

	public Product selectProductByid(int productId) {
		// TODO Auto-generated method stub
		return this.mpd.selectProductByid(productId);
	}

	public List<Product> selectProductBytype(String type) {
		// TODO Auto-generated method stub
		return this.mpd.selectProductBytype(type);
	}

	public List<Product> selectProductByis(String isShelves) {
		// TODO Auto-generated method stub
		return this.mpd.selectProductByis(isShelves);
	}

	public Clerk denglu(long tel, String password) {
		// TODO Auto-generated method stub
		if (this.selectClerkBytel(tel) != null) {
			return this.mcd.denglu(tel, password);
		} else {
			return null;
		}
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

	public List<Product> selectSonOrderAll() {
		// TODO Auto-generated method stub

		return this.mod.selectSonOrderAll();
	}

	public Clerk selectClerkBytel(long tel) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkBytel(tel);
	}

	public Clerk selectClerkByid(int clerkId) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkByid(clerkId);
	}

	public List<Clerk> selectClerkAll() {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkAll();
	}

	public boolean updateClerkByid(int clerkId, String thing, int i) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkByid(clerkId) != null) {
			return this.mcd.updateClerkByid(clerkId, thing, i);
		} else {
			return false;
		}
	}

	public boolean updateClerkStatus(int clerkId, String Status) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkByid(clerkId) != null) {
			return this.mcd.updateClerkStatus(clerkId, Status);
		} else {
			return false;
		}
	}

	public boolean insertClerk(int clerkId, String clerkName, String clerkType, long clerkTel, String password,
			String status) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkBytel(clerkTel) == null) {
			return this.mcd.insertClerk(new Clerk(clerkId, clerkName, clerkTel, password, clerkType, status));
		} else {
			return false;

		}

	}

	public boolean deleteClerk(int clerkId) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkByid(clerkId) != null) {
			return this.mcd.deleteClerk(clerkId);
		} else {
			return false;
		}
	}

	public List<Clerk> selectClerkBytype(String type) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkBytype(type);
	}

	public Clerk selectSendClerkBytype(String type) {
		// TODO Auto-generated method stub
		return this.mcd.selectSendClerkBytype(type);
	}

	// 展示活动商品
	public List<Activity> showAct(Users u) {

		 return this.adao.selectAllAct(u);
		
	}

	// 查询活动商品信息的方法
	public Product selectActiveById(int actid, int pronum) {
		return this.adao.selectActiveById(actid, pronum);
	}

	public List<Activity> selectActivityAll() {
		// TODO Auto-generated method stub
		return this.mao.selectActivityAll();
	}

	public Activity selectActivityByid(int activityId) {
		// TODO Auto-generated method stub
		return this.mao.selectActivityByid(activityId);
	}

	public boolean downActivityByid(int activityId) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.downActivityByid(activityId);
		} else {
			return false;

		}
	}

	public boolean upActivityByid(int activityId) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.upActivityByid(activityId);
		} else {
			return false;

		}
	}

	public boolean updateActivityByid(int activityId, String thing, int i) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.updateActivityByid(activityId, thing, i);
		} else {
			return false;

		}
	}

	public boolean insertActivity(String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId, String isshelves) {
		// TODO Auto-generated method stub
		return this.mao.insertActivity(activityName, activityContent, productId, activityPrice, userTypeId, isshelves);
	}

	public List<Activity> selectActivityByis(String isShelves) {
		// TODO Auto-generated method stub
		return this.mao.selectActivityByis(isShelves);
	}

	public List<Users> selectUsersAll() {
		// TODO Auto-generated method stub
		return this.udao.selectUsersAll();
	}

}
