package com.happyone.biz.inter;

import java.util.List;

import com.happyone.domain.Activity;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface ActiveBiz {
//展示活动商品
	public List<Activity> showAct(Users u);
//查询活动商品信息的方法
	public Product selectActiveById(int actid, int pronum);

	

}
