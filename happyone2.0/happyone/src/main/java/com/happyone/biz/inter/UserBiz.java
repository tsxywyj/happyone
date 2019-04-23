package com.happyone.biz.inter;

import java.util.List;

import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface UserBiz {
	

	//用户登录
	public Users login(long tel, String password);
	//用户注册
	public String addUsers(Users u);
	//更新用户积分和会员类型编号
	public void updateUser(Users user,List<Product> list);
	//通过手机号查询用户
	public Users selectUserByTel(long tel);
	//修改用户信息
	public String updateUserInfo(Users u);
	
	

	

}
