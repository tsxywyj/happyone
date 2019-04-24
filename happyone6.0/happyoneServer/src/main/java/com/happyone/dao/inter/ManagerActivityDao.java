package com.happyone.dao.inter;

import java.util.List;

import com.happyone.domain.Activity;
import com.happyone.domain.Product;

public interface ManagerActivityDao {
	// 查看所有活动
	public List<Activity> selectActivityAll();
	//
    //根据id查看活动
	public Activity selectActivityByid(int activityId);
	//下架活动
	public boolean downActivityByid(int activityId);
	//上架活动
	public boolean upActivityByid(int activityId);
	//修改活动
	public boolean updateActivityByid(int activityId,String thing,int i);
	//添加活动
	public boolean insertActivity(String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId, String isshelves);
	//按照是否上架查看
	public List<Activity> selectActivityByis(String isShelves);
}
