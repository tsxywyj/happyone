package com.happyone.domain;

import java.io.Serializable;

public class Users implements Serializable{
	private int userid;
	private long tel;
    private String password;
    private String username;
    private  int membertypeid;
    private int integral;
    private String address;
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int userid, long tel, String password, String username, int membertypeid, int integral,String address) {
		super();
		this.userid=userid;
		this.tel = tel;
		this.password = password;
		this.username = username;
		this.membertypeid = membertypeid;
		this.integral = integral;
		this.address=address;
	}
	public Users( long tel, String password, String username, int membertypeid, int integral,String address) {
		super();
		this.tel = tel;
		this.password = password;
		this.username = username;
		this.membertypeid = membertypeid;
		this.integral = integral;
		this.address=address;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMembertypeid() {
		return membertypeid;
	}

	public void setMembertypeid(int membertypeid) {
		this.membertypeid = membertypeid;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.getUsername()+"\t\t"+this.getTel()+"\t\t"+this.getPassword()+"\t"+this.getMembertypeid()+"\t\t"+this.getIntegral()+"\t\t"+this.getAddress();
    }
}
