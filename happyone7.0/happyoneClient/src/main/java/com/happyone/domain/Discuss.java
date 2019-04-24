package com.happyone.domain;

import java.io.Serializable;

public class Discuss implements Serializable{
    private int discussId;
    private String orderNo;
    private String discussType;
    private String discussContent;
	public Discuss() {
		// TODO Auto-generated constructor stub
	}
	public int getDiscussId() {
		return discussId;
	}
	public void setDiscussId(int discussId) {
		this.discussId = discussId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDiscussType() {
		return discussType;
	}
	public void setDiscussType(String discussType) {
		this.discussType = discussType;
	}
	public String getDiscussContent() {
		return discussContent;
	}
	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}
	public Discuss(int discussId, String orderNo, String discussType, String discussContent) {
		super();
		this.discussId = discussId;
		this.orderNo = orderNo;
		this.discussType = discussType;
		this.discussContent = discussContent;
	}
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.getDiscussType()+"\t"+this.getDiscussContent();
    }
}
