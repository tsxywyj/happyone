package com.happyone.biz.impl;

import java.util.List;

import com.happyone.biz.inter.ManagerActivityBiz;
import com.happyone.dao.impl.ManagerActivityDaoImpl;
import com.happyone.dao.inter.ManagerActivityDao;
import com.happyone.domain.Activity;

public class ManagerActivityBizImpl implements ManagerActivityBiz {
	ManagerActivityDao mao;

	public ManagerActivityBizImpl() {
		// TODO Auto-generated constructor stub
		mao = new ManagerActivityDaoImpl();
	}

	public List<Activity> selectActivityAll() {
		// TODO Auto-generated method stub
		return this.mao.selectActivityAll();
	}

	public Activity selectActivityByid(int activityId) {
		// TODO Auto-generated method stub
		return this.mao.selectActivityByid(activityId);
	}

	public boolean downActivityByid(int activityId) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.downActivityByid(activityId);
		} else {
			return false;

		}
	}

	public boolean upActivityByid(int activityId) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.upActivityByid(activityId);
			} else {
			return false;

		}
	}

	public boolean updateActivityByid(int activityId,String thing,int i) {
		// TODO Auto-generated method stub
		if (this.mao.selectActivityByid(activityId) != null) {
			return this.mao.updateActivityByid(activityId, thing, i);
		} else {
			return false;

		}
	}

	public boolean insertActivity(String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId, String isshelves) {
		// TODO Auto-generated method stub
	return this.mao.insertActivity(activityName,activityContent,productId, activityPrice,
			userTypeId, isshelves);
	}

	public List<Activity> selectActivityByis(String isShelves) {
		// TODO Auto-generated method stub
		return this.mao.selectActivityByis(isShelves);
	}

}
