package com.happyone.biz.impl;

import java.util.List;

import com.happyone.biz.inter.ManagerClerkBiz;
import com.happyone.dao.impl.ManagerClerkDaoImpl;
import com.happyone.dao.inter.ManagerClerkDao;
import com.happyone.domain.Clerk;

public class ManagerClerkBizImpl implements ManagerClerkBiz {
	ManagerClerkDao mcd;

	public ManagerClerkBizImpl() {
		// TODO Auto-generated constructor stub
		mcd = new ManagerClerkDaoImpl();
	}

	public Clerk denglu(long tel, String password) {
		// TODO Auto-generated method stub
		if (this.selectClerkBytel(tel) != null) {
			return this.mcd.denglu(tel, password);
		} else {
			return null;
		}
	}

	public Clerk selectClerkBytel(long tel) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkBytel(tel);
	}

	public Clerk selectClerkByid(int clerkId) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkByid(clerkId);
	}

	public List<Clerk> selectClerkAll() {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkAll();
	}

	public boolean updateClerkByid(int clerkId, String thing,int i) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkByid(clerkId) != null) {
			return this.mcd.updateClerkByid(clerkId, thing,i);
		} else {
			return false;
		}
	}

	public boolean updateClerkStatus(int clerkId, String Status) {
		// TODO Auto-generated method stub
		if (this.mcd.selectClerkByid(clerkId) != null) {
                return this.mcd.updateClerkStatus(clerkId, Status);
		} else {
			return false;
		}
	}

	public boolean insertClerk(int clerkId, String clerkName, String clerkType, long clerkTel, String password,
			String status) {
		// TODO Auto-generated method stub
		if(this.mcd.selectClerkBytel(clerkTel)==null){
			return this.mcd.insertClerk(new Clerk(clerkId, clerkName, clerkTel, password, clerkType, status));
		}else{
			return false;
			
		}
		
	}

	public boolean deleteClerk(int clerkId) {
		// TODO Auto-generated method stub
		if(this.mcd.selectClerkByid(clerkId)!=null){
			return this.mcd.deleteClerk(clerkId);
		}else{
			return false;
		}
	}

	public List<Clerk> selectClerkBytype(String type) {
		// TODO Auto-generated method stub
		return this.mcd.selectClerkBytype(type);
	}

	public Clerk selectSendClerkBytype(String type) {
		// TODO Auto-generated method stub
		return this.mcd.selectSendClerkBytype(type);
	}

}
