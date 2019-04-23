package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ManagerProductDao;
import com.happyone.domain.Product;
import com.happyone.util.DBUtil;

public class ManagerProductDaoImpl implements ManagerProductDao {
	private DBUtil db;

	public ManagerProductDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Product> selectProductAll() {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from Product order by ISSHELVES desc";
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Product p = new Product();
				// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE
				// ISSHELVES ACTIVITYID
				p.setProductId(rs.getInt("PRODUCTID"));
				p.setProductName(rs.getString("PRODUCTNAME"));
				p.setProductDescription(rs.getString("PRODUCTDESCRIPTION"));
				p.setProductPrice(rs.getDouble("PRODUCTPRICE"));
				p.setIsShelves(rs.getString("ISSHELVES"));
				list.add(p);
			}
			if (list != null) {
				return list;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}

	public boolean downProductByid(int ProductId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "update product set ISSHELVES='否' where PRODUCTID=" + ProductId;
		try {
			int q = this.db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}

		return false;
	}

	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE ISSHELVES
		// ACTIVITYID
		String sql = "insert into product values(seq_product.nextval,?,?,?,?)";
		try {
			int q = this.db.update(sql, product.getProductName(), product.getProductDescription(),
					product.getProductPrice(), product.getIsShelves());
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public boolean upProductByid(int ProductId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "update product set ISSHELVES='是' where PRODUCTID=" + ProductId;
		try {
			int q = this.db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public boolean updateProductByid(int productId, String thing, int i) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "";
		switch (i) {
		// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE ISSHELVES
		case 1:
			//修改商品姓名
			sql="update product set productname='"+thing+"' where PRODUCTID="+productId;
			break;
		case 2:
			//修改商品描述
			sql="update product set PRODUCTDESCRIPTION='"+thing+"' where PRODUCTID="+productId;
			
			break;
		case 3:
			//修改商品价格
			double price=Double.parseDouble(thing);
			sql="update product set PRODUCTPRICE="+price+" where PRODUCTID="+productId;

			break;
		default:
			
			return false;
		}
		try {
			int q = this.db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public Product selectProductByid(int productId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "select * from product where productid=" + productId;
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				Product p = new Product();
				// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE
				// ISSHELVES ACTIVITYID
				p.setProductId(rs.getInt("PRODUCTID"));
				p.setProductName(rs.getString("PRODUCTNAME"));
				p.setProductDescription(rs.getString("PRODUCTDESCRIPTION"));
				p.setProductPrice(rs.getDouble("PRODUCTPRICE"));
				p.setIsShelves(rs.getString("ISSHELVES"));
				return p;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Product> selectProductBytype(String type) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from Product where PRODUCTDESCRIPTION='"+type+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Product p = new Product();
				// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE
				// ISSHELVES ACTIVITYID
				p.setProductId(rs.getInt("PRODUCTID"));
				p.setProductName(rs.getString("PRODUCTNAME"));
				p.setProductDescription(rs.getString("PRODUCTDESCRIPTION"));
				p.setProductPrice(rs.getDouble("PRODUCTPRICE"));
				p.setIsShelves(rs.getString("ISSHELVES"));
				list.add(p);
			}
			if (list != null) {
				return list;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}

	public List<Product> selectProductByis(String isShelves) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		List<Product> list = new ArrayList<Product>();
		String sql = "select * from Product where ISSHELVES='"+isShelves+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Product p = new Product();
				// PRODUCTID PRODUCTNAME PRODUCTDESCRIPTION PRODUCTPRICE
				// ISSHELVES ACTIVITYID
				p.setProductId(rs.getInt("PRODUCTID"));
				p.setProductName(rs.getString("PRODUCTNAME"));
				p.setProductDescription(rs.getString("PRODUCTDESCRIPTION"));
				p.setProductPrice(rs.getDouble("PRODUCTPRICE"));
				p.setIsShelves(rs.getString("ISSHELVES"));
				list.add(p);
			}
			if (list != null) {
				return list;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}
	

}
