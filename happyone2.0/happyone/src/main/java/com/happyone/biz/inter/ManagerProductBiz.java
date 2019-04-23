package com.happyone.biz.inter;

import java.util.List;

import com.happyone.domain.Product;

public interface ManagerProductBiz {
    //查看所有商品
	public List<Product> selectProductAll();
	//按照商品id下架商品
	public boolean downProductByid(int ProductId);
	//添加商品
	public boolean insertProduct(int productId, String productName, String productDescription, double productPrice,
			String isShelves);
	//按照商品id上架商品
	public boolean upProductByid(int ProductId);
	//根据商品编号修改商品
	public boolean updateProductByid(int productId,String thing,int i);
	//根据商品id查找
	public Product selectProductByid(int productId);
	//根据商品类型查找
	public List<Product> selectProductBytype(String type);
	//查看商品在售状态
	public List<Product> selectProductByis(String isShelves);
}
