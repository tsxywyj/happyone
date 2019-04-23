package com.happyone.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.happyone.biz.inter.ProductBiz;
import com.happyone.dao.impl.ProductDaoImpl;
import com.happyone.dao.inter.ProductDao;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public class ProductBizImpl implements ProductBiz {

	private ProductDao pdao;
	
	
	public ProductBizImpl() {
		super();
		this.pdao = new ProductDaoImpl();
	}
	//显示菜单
		public void showPro() {
			List<Product> list=new ArrayList<Product>();
			list=this.pdao.selectAllPro();
			for (Product product : list) {
				System.out.println(product.toString2());
			}
		}
	//通过id查询商品信息
		public Product selectProById(int proid,int pronum) {
			
			return this.pdao.selectProById(proid,pronum);
		}
		
}
