package com.happyone.domain;

public class Clerk {
	private int clerkId;
	private String clerkName;
	//普通员工，经理，业务员
	private String clerkType;
	private long clerkTel;
	private String password;
	
	private String status;

	public Clerk() {
		// TODO Auto-generated constructor stub
	}

	public Clerk(int clerkId, String clerkName, long clerkTel, String password,String clerkType,  String status) {
		super();
		this.clerkId = clerkId;
		this.clerkName = clerkName;
		this.clerkType = clerkType;
		this.clerkTel = clerkTel;
		this.password = password;
		this.status = status;
	}

	public int getClerkId() {
		return clerkId;
	}

	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public String getClerkType() {
		return clerkType;
	}

	public void setClerkType(String clerkType) {
		this.clerkType = clerkType;
	}

	public long getClerkTel() {
		return clerkTel;
	}

	public void setClerkTel(long clerkTel) {
		this.clerkTel = clerkTel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return this.getClerkId()+"\t"+this.getClerkName()+"\t"+this.getClerkTel()+"\t"+this.getPassword()+"\t"+this.getClerkType()+"\t"+this.getStatus();
	}

}
