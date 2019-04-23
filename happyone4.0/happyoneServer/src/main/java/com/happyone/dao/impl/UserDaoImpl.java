package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.UserDao;
import com.happyone.domain.Product;
import com.happyone.domain.Users;
import com.happyone.util.DBUtil;

public class UserDaoImpl implements UserDao {

	private DBUtil db;
	//通过手机号tel查询用户
	public Users selectUserByTel(long tel) {
		db=new DBUtil();
		String sql="select * from users where usertel="+tel;
		//执行sql语句
		
			ResultSet rs;
			try {
				rs = this.db.query(sql);
				if(rs.next()){
					Users u=new Users();
					u.setUserid(rs.getInt("userid"));
					u.setUsername(rs.getString("username"));
					u.setTel(rs.getLong("usertel"));
					u.setPassword(rs.getString("password"));
					u.setMembertypeid(rs.getInt("membertypeid"));
					u.setIntegral(rs.getInt("integral"));
					u.setAddress(rs.getString("address"));
					return u;
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
	//插入用户
	public boolean insertUser(Users u) {
		this.db=new DBUtil();
		String sql="insert into users values(seq_users.nextval,?,?,?,?,?,?)";
		int i ;
		try {
			i = db.update(sql, u.getUsername(),u.getTel(),u.getPassword(),u.getMembertypeid(),u.getIntegral(),u.getAddress());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			this.db.closed();
		}
		return i>0;
	}
	//更新会员积分和会员类型编号
	public Users updateUser(Users user,List<Product> list) {
		this.db=new DBUtil();
		//获取用户id
		int userid = user.getUserid();
		//计算总价
		double sum=0;
		for (Product product : list) {
			double sumPrice=product.getProductNum()*product.getProductPrice();
			sum=sum+sumPrice;
		}
		//积分
		int integral=(int)(user.getIntegral()+sum);
		//会员类型编号
		int Memberid = this.judgeMember(integral);
		Users u=new Users(user.getTel(), user.getPassword(), user.getUsername(), Memberid, integral, user.getAddress());
		u.setUserid(user.getUserid());
		String sql="update users set integral=?,membertypeid=? where userid=?";
		try {
			db.update(sql,integral,Memberid,userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.db.closed();
		}
		return u;
	}
	//根据会员积分判断会员类型
	public int judgeMember(int integral){
		if(integral<=100){
			return 1;
		}else if(integral<=200){
			return 2;
		}else if(integral<=500){
			return 3;
		}else if(integral<=1000){
			return 4;
		}else{
			return 5;
		}
		
	}
	//修改用户信息
	public boolean updateUserInfo(Users u) {
		this.db=new DBUtil();
		String sql="update users set username=?,password=?,address=? where usertel=?";
		try {
			int i = db.update(sql,u.getUsername(),u.getPassword(),u.getAddress(),u.getTel());
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
