package com.happyone.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.happyone.biz.impl.ActiveBizImpl;
import com.happyone.biz.impl.OrderBizImpl;
import com.happyone.biz.impl.UserBizImpl;
import com.happyone.biz.inter.ActiveBiz;
import com.happyone.biz.inter.OrdersBiz;
import com.happyone.biz.inter.UserBiz;
import com.happyone.dao.impl.ActiveDaoImpl;
import com.happyone.dao.inter.ActiveDao;
import com.happyone.util.UserInput;



public class mytest {
	
	private UserBiz ubiz;
	private OrdersBiz obiz;
	private ActiveBiz abiz;
	private ActiveDao adao;
	
	public mytest() {
		super();
		this.ubiz=new UserBizImpl();
		this.obiz=new OrderBizImpl();
		this.abiz=new ActiveBizImpl();
		this.adao=new ActiveDaoImpl();
	}
	@Test
	public void mytest1(){
		long i=12345678912l;
		
			System.out.println(i/10000000000l);
		
	}
	//更新会员积分
	@Test
	public void updateUserIntegral(){
		Users user=new Users();
		user.setUserid(2);
		List<Product> list=new ArrayList<Product>();
		Product pro=new Product(0, null, null, 3, 150, null);
		list.add(pro);
		ubiz.updateUser(user, list);
	}
	//添加订单
	@Test
	public void insertOrder(){
		Users user=new Users();
		user.setUserid(2);
		List<Product> list=new ArrayList<Product>();
		Product pro=new Product(0, null, null, 3, 150, null);
		list.add(pro);
		obiz.insertOrders(user, list, 2);
	}
	//添加子订单
	@Test
	public void insertSonOrder(){
		Users user=new Users();
		user.setUserid(2);
		List<Product> list=new ArrayList<Product>();
		Product pro=new Product(0, null, null, 3, 150, null);
		list.add(pro);
		obiz.insertSonOrder(list,"abb5a069b6c44ca0");
	}
	//查看活动
	@Test
	public void selectAct(){
		Users user=new Users();
		user.setMembertypeid(2);
		System.out.println(user);
		List<Activity> selectAllAct = this.adao.selectAllAct(user);
		/*for (Activity activity : selectAllAct) {
			System.out.println(activity.getActivityId()+activity.toString());
		}*/
		
	}

}
