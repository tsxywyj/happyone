package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Product;

public interface ProductDao {
	
	//查找所有上架商品
	public List<Product> selectAllPro();
	//通过id查找商品信息
	public Product selectProById(int proid,int pronum);

}
