package com.happyone.biz.impl;

import com.happyone.biz.inter.DiscussBiz;
import com.happyone.dao.impl.DiscussDaoImpl;
import com.happyone.dao.inter.DiscussDao;
import com.happyone.domain.Discuss;

public class DiscussBizImpl implements DiscussBiz {
	private DiscussDao ddao;
	public DiscussBizImpl() {
		// TODO Auto-generated constructor stub
		this.ddao=new DiscussDaoImpl();
	}

	public Discuss selectDiscussByno(String orderNo) {
		// TODO Auto-generated method stub
		return this.ddao.selectDiscussByno(orderNo);
	}

	public String insertDiscuss(Discuss dis) {
		// TODO Auto-generated method stub
		return this.ddao.insertDiscuss(dis)?"评价成功！":"评价失败！";

	}

}
