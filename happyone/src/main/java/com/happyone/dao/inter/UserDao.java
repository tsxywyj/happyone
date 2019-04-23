package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface UserDao {

	//通过手机号tel查询用户
	public Users selectUserByTel(long tel);

	//添加用户
	public boolean insertUser(Users u);
//更新会员积分和会员类型编号
	public Users updateUser(Users user,List<Product> list);

//修改用户信息
	public boolean updateUserInfo(Users u);





}
