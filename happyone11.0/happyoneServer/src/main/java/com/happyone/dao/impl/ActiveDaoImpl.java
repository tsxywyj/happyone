package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ActiveDao;
import com.happyone.domain.Activity;
import com.happyone.domain.Product;
import com.happyone.domain.Users;
import com.happyone.util.DBUtil;

public class ActiveDaoImpl implements ActiveDao {
	private DBUtil db;
	//查找指定用户可以查看的活动
	public List<Activity> selectAllAct(Users u) {
		this.db=new DBUtil();
		//获取该用户的会员等级
		int memberid = u.getMembertypeid();
		
		//创建一个集合用来存储活动
		List<Activity> list=new ArrayList<Activity>();
		String sql="select a.*,p.productname from activity a,product p where a.productid=p.productid and a.isshelves='是' and ?>=a.membertypeid";
		try {
			ResultSet rs = this.db.query(sql,memberid);
			while(rs.next()){
				list.add(new Activity(rs.getInt("activityid"),rs.getString("activityname"),rs.getString("activitycontent"),rs.getInt("productid"),rs.getDouble("activityprice"),rs.getInt("membertypeid"), rs.getString("productname"), rs.getString("isshelves")));
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.db.closed();
		}
	}
	//查询活动商品信息的方法
	public Product selectActiveById(int actid, int pronum) {
		this.db=new DBUtil();
		//通过actid查到proid
		String s="select productid from activity where activityid="+actid;
		ResultSet r;
		int proid=0;
		try {
			r = this.db.query(s);
			if(r.next()){
				proid=r.getInt("productid");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//通过proid查询到活动价格
		String sql="select activityprice from activity where productid="+proid;
		try {
			ResultSet rs1 = this.db.query(sql);
			double activityprice=0;
			if(rs1.next()){
				activityprice=rs1.getDouble("activityprice");
			}
			String sql2="select * from product where productid="+proid;
			ResultSet rs = this.db.query(sql2);
			if(rs.next()){
				Product pro=new Product();
				pro.setProductId(rs.getInt("productid"));
				pro.setProductName(rs.getString("productname"));
				pro.setProductNum(pronum);
				pro.setProductDescription(rs.getString("productdescription"));
				pro.setProductPrice(activityprice);
				pro.setIsShelves(rs.getString("isshelves"));
				return pro;
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
	//通过活动id查询商品id
		public int selectProIdByActid(int actid) {
			this.db=new DBUtil();
			String sql="select productid from activity where activityid="+actid;
			try {
				ResultSet rs = this.db.query(sql);
				if(rs.next()){
					int productid = rs.getInt("productid");
					return productid;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.db.closed();
			}
			return 0;
		}
}
