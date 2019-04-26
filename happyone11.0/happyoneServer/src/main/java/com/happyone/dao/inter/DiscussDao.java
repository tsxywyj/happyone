package com.happyone.dao.inter;

import com.happyone.domain.Discuss;

public interface DiscussDao {
	public Discuss selectDiscussByno(String orderNo);
	public boolean insertDiscuss(Discuss dis);

}
