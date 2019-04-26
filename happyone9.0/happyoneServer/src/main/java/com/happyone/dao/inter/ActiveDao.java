package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Activity;
import com.happyone.domain.Product;
import com.happyone.domain.Users;

public interface ActiveDao {
//查找指定用户可以查看的活动
	public List<Activity> selectAllAct(Users u);
//查询活动商品信息的方法
	public Product selectActiveById(int actid, int pronum);
	//通过活动id查询商品id
		public int selectProIdByActid(int actid);
}
