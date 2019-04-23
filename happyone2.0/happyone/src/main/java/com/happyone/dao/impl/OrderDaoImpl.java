package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.happyone.dao.inter.OrderDao;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.Users;
import com.happyone.util.DBUtil;
import com.happyone.util.UserInput;

public class OrderDaoImpl implements OrderDao {
	private DBUtil db;
	private UserInput ui;
	
	public OrderDaoImpl() {
		super();
		this.ui=new UserInput();
	}

	//添加订单
	public String insertOrders(Users user, List<Product> list,int i) {
		this.db=new DBUtil();
		//获取一个订单号
		String uuid = ui.getUUID();
		//是否需要配送
		 String issend=null;
		 //获取用户id
		 int userid = user.getUserid();
		 //是否配送
		if(i==1){
			issend="否";
		}else if(i==2){
			issend="是";
		}
		//获取当前日期
		Date d = ui.getNewtime();
		String date = ui.setDateString(d);
		//获取订单价格
		double sum=0;
		for (Product product : list) {
			double sumPrice=product.getProductNum()*product.getProductPrice();
			sum=sum+sumPrice;
		}
		String sql="insert into orders values(seq_orders.nextval,?,?,to_date(?,'yyyy-mm-dd'),?,?,'已付款')";
		try {
			int update = db.update(sql,uuid,userid,date,sum,issend);
			return uuid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
	}
//添加子订单
	public void inserSonOrder(List<Product> list, String orderno) {
		this.db=new DBUtil();
		//遍历集合
		for (Product product : list) {
			//获取商品id
			int productId = product.getProductId();
			//获取商品数量
			int productNum = product.getProductNum();
			String sql="insert into sonorder values(seq_sonorder.nextval,?,?,?)";
			try {
				db.update(sql,orderno,productId,productNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.db.closed();
		
	}
//查询所有订单
	public List<Orders> selectAllOrder(Users u) {
		List<Orders> listorder=new ArrayList<Orders>();
		//获取用户id
		int id = u.getUserid();
		this.db=new DBUtil();
		String sql="select * from orders where userid="+id;
		try {
			ResultSet rs = db.query(sql);
			while(rs.next()){
				
				listorder.add(new Orders(rs.getInt("orderid"),rs.getString("orderno"),rs.getInt("userid"),rs.getDouble("sumprice"),rs.getDate("orderdate"),rs.getString("issend"),rs.getString("status")));
			}
			return listorder;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
	}
//查看未收货订单
	public List<Orders> selectOrderUnreceived(Users u) {
		List<Orders> listunrcv=new ArrayList<Orders>();
		//获取用户id
		int id = u.getUserid();
		this.db=new DBUtil();
		String sql="select * from orders where userid=? and issend='是' and status='已付款'";
		try {
			ResultSet rs = db.query(sql,id);
			while(rs.next()){
				
				listunrcv.add(new Orders(rs.getInt("orderid"),rs.getString("orderno"),rs.getInt("userid"),rs.getDouble("sumprice"),rs.getDate("orderdate"),rs.getString("issend"),rs.getString("status")));
			}
			return listunrcv;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
		
	}
//通过id查询订单
	public Orders selectOrderById(int id) {
		db=new DBUtil();
		String sql="select * from orders where orderid="+id;
		//执行sql语句
			ResultSet rs;
			try {
				rs = this.db.query(sql);
				if(rs.next()){
					Orders od=new Orders();
					od.setOrderId(rs.getInt("orderid"));
					od.setOrderNo(rs.getString("orderno"));
					od.setUserId(rs.getInt("userid"));
					od.setOrderDate(rs.getDate("orderdate"));
					od.setSumPrice(rs.getDouble("sumprice"));
					od.setIsSend(rs.getString("issend"));
					od.setStatus(rs.getString("status"));
					return od;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}finally{
				this.db.closed();
			}
			return null;
	}
//修改订单状态
	public boolean alterOrderStatus(int int1) {
		this.db=new DBUtil();
		String sql="update orders set status='交易完成' where orderid="+int1;
		try {
			int i = db.update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
	}

}
