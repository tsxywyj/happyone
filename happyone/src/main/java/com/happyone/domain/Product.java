package com.happyone.domain;

public class Product {
	private int productId;
	private String productName;
	private String productDescription;
	private int productNum;
	private double productPrice;
	private String isShelves;
	public Product() {
		super();
	}
	public Product(int productId, String productName, String productDescription, int productNum, double productPrice,
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
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
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
		
		return this.getProductId()+"\t\t"+this.getProductName()+"\t"+this.getProductDescription()+"\t\t"+this.getProductPrice();
	}

}
