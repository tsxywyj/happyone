package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Clerk;

public interface ManagerClerkDao {
	
	// 职工登录
	public Clerk denglu(long tel,String password);
	// 按照员工手机号查找
	public Clerk selectClerkBytel(long tel);
	// 按照员工编号查找
	public Clerk selectClerkByid(int clerkId);
	// 查看所有员工
	public List<Clerk> selectClerkAll();
    //修改员工信息
	public boolean updateClerkByid(int clerkId,String thing,int i);
	//修改员工工作状态
	public boolean updateClerkStatus(int clerkId,String Status);
	//添加员工
	public boolean insertClerk(Clerk clerk);
    //删除员工
    public boolean deleteClerk(int clerkId);
    //按照工作查找
    public List<Clerk> selectClerkBytype(String type);
  //查找配送员空闲
    public Clerk selectSendClerkBytype(String type);
}
