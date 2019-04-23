package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ManagerActivityDao;
import com.happyone.domain.Activity;
import com.happyone.util.DBUtil;

public class ManagerActivityDaoImpl implements ManagerActivityDao {
	DBUtil db;

	public ManagerActivityDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Activity> selectActivityAll() {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		List<Activity> list = new ArrayList<Activity>();
		String sql = "select a.*,d.productname from activity a,product d  where d.productid=a.productid order by a.ISSHELVES desc";
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Activity a = new Activity();
				// ACTIVITYNAME ACTIVITYCONTENT PRODUCTID ACTIVITYPRICE
				// MEMBERTYPEID
				a.setActivityId(rs.getInt("ACTIVITYID"));
				a.setActivityName(rs.getString("ACTIVITYNAME"));
				a.setActivityContent(rs.getString("ACTIVITYCONTENT"));
				a.setProductName(rs.getString("productname"));
				a.setActivityPrice(rs.getDouble("ACTIVITYPRICE"));
				a.setUserTypeId(rs.getInt("MEMBERTYPEID"));
				a.setIsshelves(rs.getString("isshelves"));
				list.add(a);
			}
			if (list != null) {
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}

		return null;
	}

	public Activity selectActivityByid(int activityId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		Activity a;
		String sql = "select a.*,d.productname from activity a,product d where d.productid=a.productid";
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				a = new Activity();
				// ACTIVITYNAME ACTIVITYCONTENT PRODUCTID ACTIVITYPRICE
				// MEMBERTYPEID
				a.setActivityId(rs.getInt("ACTIVITYID"));
				a.setActivityName(rs.getString("ACTIVITYNAME"));
				a.setActivityContent(rs.getString("ACTIVITYCONTENT"));
				a.setProductName(rs.getString("productname"));
				a.setActivityPrice(rs.getDouble("ACTIVITYPRICE"));
				a.setUserTypeId(rs.getInt("MEMBERTYPEID"));
				a.setIsshelves(rs.getString("isshelves"));
				return a;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return null;
	}

	public boolean downActivityByid(int activityId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "update activity set isshelves='否' where activityId=" + activityId;
		try {
			int q = db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public boolean upActivityByid(int activityId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "update activity set isshelves='是' where activityId=" + activityId;
		try {
			int q = db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public boolean updateActivityByid(int activityId, String thing, int i) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "";
		switch (i) {
		// ACTIVITYNAME ACTIVITYCONTENT PRODUCTID ACTIVITYPRICE MEMBERTYPEID
		// ISSHELVES

		case 1:
			// 修改活动姓名
			sql = "update activity set ACTIVITYNAME='" + thing + "' where activityId=" + activityId;
			break;
		case 2:
			// 修改活动描述
			sql = "update activity set ACTIVITYCONTENT='" + thing + "' where activityId=" + activityId;
			break;
		case 3:
			// 修改活动价格
			double price = Double.parseDouble(thing);
			sql = "update activity set ACTIVITYPRICE=" + price + " where activityId=" + activityId;
			break;
		case 4:
			// 修改会员限制
			int xz = Integer.parseInt(thing);
			sql = "update activity set MEMBERTYPEID=" + xz + " where activityId=" + activityId;
			break;
		default:
			return false;
		}
		try {
			int q = this.db.update(sql);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public boolean insertActivity(String activityName, String activityContent, int productId, double activityPrice,
			int userTypeId, String isshelves) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		// ACTIVITYNAME ACTIVITYCONTENT PRODUCTID ACTIVITYPRICE MEMBERTYPEID
		// ISSHELVES PRODUCTNAME

		String sql = "insert into activity values (seq_activity.nextval,?,?,?,?,?,?)";
		try {
			int q = db.update(sql, activityName, activityContent, productId, activityPrice, userTypeId, isshelves);
			if (q == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}
		return false;
	}

	public List<Activity> selectActivityByis(String isShelves) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		List<Activity> list = new ArrayList<Activity>();
		String sql = "select a.*,d.productname from activity a,product d where d.productid=a.productid and a.ISSHELVES='"+isShelves+"'";
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Activity a = new Activity();
				// ACTIVITYNAME ACTIVITYCONTENT PRODUCTID ACTIVITYPRICE
				// MEMBERTYPEID
				a.setActivityId(rs.getInt("ACTIVITYID"));
				a.setActivityName(rs.getString("ACTIVITYNAME"));
				a.setActivityContent(rs.getString("ACTIVITYCONTENT"));
				a.setProductName(rs.getString("productname"));
				a.setActivityPrice(rs.getDouble("ACTIVITYPRICE"));
				a.setUserTypeId(rs.getInt("MEMBERTYPEID"));
				a.setIsshelves(rs.getString("isshelves"));
				list.add(a);
			}
			if (list != null) {
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();

		}

		return null;
	}

}
