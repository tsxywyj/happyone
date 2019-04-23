package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ManagerOrdersDao;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.util.DBUtil;

public class ManagerOrdersDaoImpl implements ManagerOrdersDao {
    private DBUtil db;
    
	public ManagerOrdersDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Orders> selectOrdersAll() {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="select * from orders";
		List<Orders> list=new ArrayList<Orders>();
		Orders order;
		try {
			ResultSet rs = db.query(sql);
			while(rs.next()){
				order=new Orders();
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderNo(rs.getString("orderno"));
				order.setUserId(rs.getInt("userid"));
				order.setOrderDate(rs.getDate("orderdate"));
				order.setSumPrice(rs.getDouble("sumprice"));
				order.setIsSend(rs.getString("issend"));
				order.setStatus(rs.getString("status"));
				list.add(order);
			}
			if(list!=null){
				return list;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closed();
			
		}
		return null;
		
		
	}

	public boolean updateOrderBytype(int orderId, String newType) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="update orders set STATUS='"+newType+"' where ORDERID="+orderId;
		try {
			int q = this.db.update(sql);
			if(q==0){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closed();
		}
		return false;
	}

	public Orders selectOrdersByid(int orderId) {
		// TODO Auto-generated method stub
		Orders order;
		this.db=new DBUtil();
		String sql="select * from orders where ORDERID="+orderId;
		try {
			ResultSet rs = this.db.query(sql);
			if(rs.next()){
				order=new Orders();
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderNo(rs.getString("orderno"));
				order.setUserId(rs.getInt("userid"));
				order.setOrderDate(rs.getDate("orderdate"));
				order.setSumPrice(rs.getDouble("sumprice"));
				order.setIsSend(rs.getString("issend"));
				order.setStatus(rs.getString("status"));
				return order;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closed();
		}
		return null;
	}

	public List<Orders> selectOrdersBytype(String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="select * from orders where STATUS='"+type+"'";
		List<Orders> list=new ArrayList<Orders>();
		Orders order;
		try {
			ResultSet rs = db.query(sql);
			while(rs.next()){
				order=new Orders();
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderNo(rs.getString("orderno"));
				order.setUserId(rs.getInt("userid"));
				order.setOrderDate(rs.getDate("orderdate"));
				order.setSumPrice(rs.getDouble("sumprice"));
				order.setIsSend(rs.getString("issend"));
				order.setStatus(rs.getString("status"));
				list.add(order);
			}
			if(list!=null){
				return list;	
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closed();
			
		}
		return null;
		
	}

	public String printOrder(int orderId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public List<Product> selectSonOrderByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		List<Product> list = new ArrayList<Product>();
		String sql="select * from sonorder s,product p where s.PRODUCTID=p.PRODUCTID and orderno='"+orderNo+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				Product p = new Product();
				// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE
				// ISSHELVES ACTIVITYID
				p.setProductId(rs.getInt("PRODUCTID"));
				p.setProductName(rs.getString("PRODUCTNAME"));
				p.setProductDescription(rs.getString("PRODUCTDESCRIPTION"));
				p.setProductPrice(rs.getDouble("PRODUCTPRICE"));
				p.setIsShelves(rs.getString("ISSHELVES"));
				p.setProductNum(rs.getInt("PRODUCTNUM"));
				list.add(p);
				
			}
			if (list != null) {
				return list;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public boolean insertSendOrder(String orderNo, int clerkId) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql ="insert into sendorder values (seq_sendorder.nextval,?,?,'配送中')";
	    try {
			int q = this.db.update(sql, orderNo,clerkId);
			if(q==0){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<SendOrders> selectSendOrderAll() {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		List<SendOrders> list=new ArrayList<SendOrders>();
		String sql="select u.USERTEL,u.ADDRESS,s.SENDORDERID,s.ORDERNO,s.STATUS,c.clerkname  from users u,sendorder s,orders o,clerk c where u.USERID=o.USERID and o.orderno=s.orderno and c.clerkid=s.clerkid";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				SendOrders s=new SendOrders();
				s.setSendOrdersId(rs.getInt("SENDORDERID"));
				s.setOrderNo(rs.getString("ORDERNO"));
				s.setClerkName(rs.getString("clerkName"));
				s.setStatus(rs.getString("STATUS"));
				s.setUserTel(rs.getLong("USERTEL"));
				s.setAddress(rs.getString("ADDRESS"));
				list.add(s);
			}
			if(list!=null){
				return list;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateSendOrder(int sendOrderId, String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="update sendorder set STATUS='"+type+"' where SENDORDERID="+sendOrderId;
		try {
			int q = this.db.update(sql);
			if(q==0){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public List<SendOrders> selectSendOrderByclerkid(int clerkID, String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		List<SendOrders> list=new ArrayList<SendOrders>();
		String sql="select u.USERTEL,u.ADDRESS,s.SENDORDERID,s.ORDERNO,s.STATUS,c.clerkname  from users u,sendorder s,orders o,clerk c where u.USERID=o.USERID and o.orderno=s.orderno and c.clerkid=s.clerkid and s.clerkid="+clerkID+" and s.STATUS='"+type+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				SendOrders s=new SendOrders();
				s.setSendOrdersId(rs.getInt("SENDORDERID"));
				s.setOrderNo(rs.getString("ORDERNO"));
				s.setClerkName(rs.getString("clerkName"));
				s.setStatus(rs.getString("STATUS"));
				s.setUserTel(rs.getLong("USERTEL"));
				s.setAddress(rs.getString("ADDRESS"));
				list.add(s);
			}
			if(list!=null){
				return list;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateOrdersByNo(String orderNo, String newType) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="update orders set STATUS='"+newType+"' where ORDERNO='"+orderNo+"'";
		try {
			int q = this.db.update(sql);
			if(q==0){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closed();
		}
		return false;
	}

	public List<SendOrders> selectSendOrderBytype(String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		List<SendOrders> list=new ArrayList<SendOrders>();
		String sql="select u.USERTEL,u.ADDRESS,s.SENDORDERID,s.ORDERNO,s.STATUS,c.clerkname  from users u,sendorder s,orders o,clerk c where u.USERID=o.USERID and o.orderno=s.orderno and c.clerkid=s.clerkid and s.STATUS='"+type+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				SendOrders s=new SendOrders();
				s.setSendOrdersId(rs.getInt("SENDORDERID"));
				s.setOrderNo(rs.getString("ORDERNO"));
				s.setClerkName(rs.getString("clerkName"));
				s.setStatus(rs.getString("STATUS"));
				s.setUserTel(rs.getLong("USERTEL"));
				s.setAddress(rs.getString("ADDRESS"));
				list.add(s);
			}
			if(list!=null){
				return list;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
