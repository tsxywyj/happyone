package com.happyone.biz.impl;

import java.util.List;

import com.happyone.biz.inter.ManagerProductBiz;
import com.happyone.dao.impl.ManagerProductDaoImpl;
import com.happyone.dao.inter.ManagerProductDao;
import com.happyone.domain.Product;

public class ManagerProductBizImpl implements ManagerProductBiz {
	private ManagerProductDao mpd;

	public ManagerProductBizImpl() {
		// TODO Auto-generated constructor stub
		mpd = new ManagerProductDaoImpl();
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
		return this.mpd.insertProduct(new Product(productId, productName, productDescription, 1,productPrice, isShelves));
	}

	public boolean upProductByid(int ProductId) {
		// TODO Auto-generated method stub
		if (this.mpd.selectProductByid(ProductId) != null) {
			return this.mpd.upProductByid(ProductId);

		} else {
			return false;
		}
	}

	public boolean updateProductByid(int productId, String thing,int i) {
		// TODO Auto-generated method stub
		if (this.mpd.selectProductByid(productId) != null) {
			return this.mpd.updateProductByid(productId, thing,i);

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

}
