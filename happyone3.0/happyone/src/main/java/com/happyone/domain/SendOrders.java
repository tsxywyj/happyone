package com.happyone.domain;

public class SendOrders {
    private int sendOrdersId;
    private String orderNo;
    private String clerkName;
    private String status;
    private long userTel;
    private String address;
	public SendOrders() {
		// TODO Auto-generated constructor stub
	}
	public int getSendOrdersId() {
		return sendOrdersId;
	}
	public void setSendOrdersId(int sendOrdersId) {
		this.sendOrdersId = sendOrdersId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getUserTel() {
		return userTel;
	}
	public void setUserTel(long userTel) {
		this.userTel = userTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SendOrders(int sendOrdersId, String orderNo, String clerkName, String status, long userTel, String address) {
		super();
		this.sendOrdersId = sendOrdersId;
		this.orderNo = orderNo;
		this.clerkName = clerkName;
		this.status = status;
		this.userTel = userTel;
		this.address = address;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getOrderNo()+"\t"+this.getUserTel()+"\t"+this.getAddress()+"\t"+this.getClerkName()+"\t"+this.getStatus();
	}

}
