package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.happyone.dao.inter.DiscussDao;
import com.happyone.domain.Discuss;
import com.happyone.util.DBUtil;

public class DiscussDaoImpl implements DiscussDao {
	private DBUtil db;

	public DiscussDaoImpl() {
		// TODO Auto-generated constructor stub

	}

	public Discuss selectDiscussByno(String orderNo) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		Discuss discuss = new Discuss();
		String sql = "select * from discuss where orderNo='" + orderNo + "'";
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				// DISCUSSID ORDERNO DISCUSSTYPE DISCUSSCONTENT
				discuss.setDiscussId(rs.getInt("DISCUSSID"));
				discuss.setOrderNo(rs.getString("ORDERNO"));
				discuss.setDiscussType(rs.getString("DISCUSSTYPE"));
				discuss.setDiscussContent(rs.getString("DISCUSSCONTENT"));
				return discuss;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}

	// 插入评价
	public boolean insertDiscuss(Discuss dis) {
		this.db = new DBUtil();
		String sql = "insert into discuss values (seq_discuss.nextval,?,?,?)";
		try {
			int i = db.update(sql, dis.getOrderNo(), dis.getDiscussType(), dis.getDiscussContent());
			return i > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			this.db.closed();
		}
	}
}
