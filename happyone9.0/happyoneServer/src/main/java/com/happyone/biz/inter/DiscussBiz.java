package com.happyone.biz.inter;

import com.happyone.domain.Discuss;

public interface DiscussBiz {
public Discuss selectDiscussByno(String orderNo);
//插入评价
	public String insertDiscuss(Discuss dis);
 }
