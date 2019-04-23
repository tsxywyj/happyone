package com.happyone.domain;

import java.io.Serializable;

public class Product implements Serializable{

	public Product() {
		// TODO Auto-generated constructor stub
	}
	private int productId;
	private String productName;
	private String productDescription;
	private int productNum;
	private double productPrice;
	private String isShelves;
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public Product(int productId, String productName, String productDescription,int  productNum,double productPrice,
			String isShelves) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productNum=1;
		this.productPrice = productPrice;
		this.isShelves = isShelves;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getIsShelves() {
		return isShelves;
	}
	public void setIsShelves(String isShelves) {
		this.isShelves = isShelves;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getProductName()+"\t"+this.getProductDescription()+"\t"+this.getProductPrice();
	}
	public String toString2() {
		// TODO Auto-generated method stub
		return this.getProductId()+"\t"+this.getProductName()+"\t"+this.getProductDescription()+"\t"+this.getProductPrice();
	}

}
