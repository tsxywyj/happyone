package com.happyone.biz.inter;

import java.util.List;

import com.happyone.domain.Product;

public interface ProductBiz {
	//展示菜单
	public List<Product> showPro();

	//通过id查询商品信息
	public Product selectProById(int proid,int pronum);

	

}
