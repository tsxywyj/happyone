package com.happyone.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.happyone.biz.inter.UserBiz;
import com.happyone.dao.impl.UserDaoImpl;
import com.happyone.dao.inter.UserDao;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public class UserBizImpl implements UserBiz {
	private UserDao udao;
	

	public UserBizImpl() {
		super();
		this.udao=new UserDaoImpl();
	}

//用户登录
	public Users login(long tel, String password) {
		Users user=udao.selectUserByTel(tel);
		if(user!=null&&user.getPassword().equals(password)){
			return user;
		}else {
			return null;
		}
	}
//用户的注册
	public String addUsers(Users u) {
		
		return this.udao.insertUser(u)?"注册成功":"注册失败";
	}
//更新会员积分和会员类型编号
	public void updateUser(Users user,List<Product> list) {
		this.udao.updateUser(user,list);
	}

	public String updateUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
//通过tel查找用户
	public Users selectUserByTel(long tel) {
		// TODO Auto-generated method stub
		return this.udao.selectUserByTel(tel);
	}
//修改用户信息
	public String updateUserInfo(Users u) {
	
		return this.udao.updateUserInfo(u)?"修改成功":"修改失败";
	}




}
