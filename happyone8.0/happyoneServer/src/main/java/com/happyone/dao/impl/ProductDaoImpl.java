package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ProductDao;
import com.happyone.domain.Product;
import com.happyone.util.DBUtil;

public class ProductDaoImpl implements ProductDao{
	
	private DBUtil db;
	
	//查找所有已上架的商品
		public List<Product> selectAllPro() {
			List<Product> list=new ArrayList<Product>();
			this.db=new DBUtil();
			String sql="select productid,productname,productdescription,productprice,isshelves from product where isshelves='是'";
			try {
				ResultSet rs = db.query(sql);
				while(rs.next()){
					list.add(new Product(rs.getInt("productid"),rs.getString("productname"),rs.getString("productdescription"),1,rs.getDouble("productprice"),rs.getString("isshelves")));
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

	//通过id查询商品信息
	public Product selectProById(int proid,int pronum) {
		db=new DBUtil();
		String sql="select * from product where productid="+proid;
		//执行sql语句
			ResultSet rs;
			try {
				rs = this.db.query(sql);
				if(rs.next()){
					Product pro=new Product();
					pro.setProductId(rs.getInt("productid"));
					pro.setProductName(rs.getString("productname"));
					pro.setProductNum(pronum);
					pro.setProductDescription(rs.getString("productdescription"));
					pro.setProductPrice(rs.getDouble("productprice"));
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

}
