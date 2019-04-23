package com.happyone.domain;

public class Activity {
    private int activityId;
    private String activityName;
    private String activityContent;
    private int productId;
    private double activityPrice;
    //参加活动的会员限制
    private int userTypeId;
    private String productName;
    private String isshelves;
	public String getIsshelves() {
		return isshelves;
	}
	public void setIsshelves(String isshelves) {
		this.isshelves = isshelves;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Activity() {
		// TODO Auto-generated constructor stub
	}
	public Activity(int activityId, String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId,String productName,String isshelves) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.productId = productId;
		this.activityPrice = activityPrice;
		this.userTypeId = userTypeId;
		this.productName=productName;
		this.isshelves=isshelves;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getActivityName()+"\t"+this.getActivityContent()+"\t"+this.getProductName()+"\t"+this.getActivityPrice();
	}

}
