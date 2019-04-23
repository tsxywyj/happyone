package com.happyone.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.happyone.biz.inter.ActiveBiz;
import com.happyone.dao.impl.ActiveDaoImpl;
import com.happyone.dao.inter.ActiveDao;
import com.happyone.domain.Activity;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public class ActiveBizImpl implements ActiveBiz {
	private ActiveDao adao;
	

	public ActiveBizImpl() {
		super();
		this.adao = new ActiveDaoImpl();
	}
	//展示活动商品
	public List<Activity> showAct(Users u) {

		 return this.adao.selectAllAct(u);
		
	}
	
	public Product selectActiveById(int actid, int pronum) {
		return this.adao.selectActiveById(actid,pronum);
	}


}
