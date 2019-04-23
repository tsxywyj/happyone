package com.happyone.domain;

import java.util.Date;

public class Orders {
    private int  orderId;
    private String orderNo;
    private int userId;
    private double sumPrice;
    private Date orderDate;
    private String isSend;
    private String status;
    
	public Orders() {
		// TODO Auto-generated constructor stub
	}
    
	public Orders(int orderId, String orderNo, int userId, double sumPrice, Date orderDate, String isSend,
			String status) {
		super();
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.userId = userId;
		this.sumPrice = sumPrice;
		this.orderDate = orderDate;
		this.isSend = isSend;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getOrderId()+"\t"+this.getOrderNo()+"\t"+this.getUserId()+"\t"+this.getOrderDate()+"\t"+this.getSumPrice()+"\t"+this.getIsSend()+"\t"+this.getStatus();
	}

}
