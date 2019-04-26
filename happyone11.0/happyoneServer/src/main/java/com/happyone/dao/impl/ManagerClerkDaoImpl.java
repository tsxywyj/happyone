package com.happyone.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.happyone.dao.inter.ManagerClerkDao;
import com.happyone.domain.Clerk;
import com.happyone.util.DBUtil;

public class ManagerClerkDaoImpl implements ManagerClerkDao {
	private DBUtil db;

	public ManagerClerkDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public Clerk denglu(long tel, String password) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "select * from clerk where clerktel=" + tel + " and password='" + password + "'";
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}

		return null;
	}

	public Clerk selectClerkBytel(long tel) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "select * from clerk where clerktel=" + tel;

		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				// CLERKID CLERKNAME CLERKTEL PASSWORD CLERKTYPE STATUS
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
				return c;
			}
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}

	public Clerk selectClerkByid(int clerkId) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "select * from clerk where clerkId=" + clerkId;
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				// CLERKID CLERKNAME CLERKTEL PASSWORD CLERKTYPE STATUS
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
				return c;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}

		return null;

	}

	public List<Clerk> selectClerkAll() {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "select * from clerk";
		List<Clerk> list = new ArrayList<Clerk>();
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
				list.add(c);

			}
			if (list == null) {
				return null;

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}
		return null;
	}

	public boolean updateClerkByid(int clerkId, String thing, int i) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		// CLERKID CLERKNAME CLERKTEL PASSWORD CLERKTYPE STATUS
		if(i==2){
			
			
		}
		String sql = "";
		switch (i) {
		case 1:
			// 修改用户名
			sql = "update clerk set CLERKNAME='" + thing + "' where  CLERKID=" + clerkId;
			break;
		case 2:
			int num=0;
			for(int q=0;q<thing.length();q++){
			char x=thing.charAt(q);
			if((x>'a' &&x<'z' )||(x>'A'&&x<'Z') ){
			num++;
			}
			}
			if(num!=0){
				
				return false;
			}
			boolean result=thing.matches("[0-9]+");
			if (result == false) { 
				return false;
			}
			long tel=0;
			tel = Long.parseLong(thing);
		
			// 修改电话
			if(tel/10000000000l!=1){
				return false;
			}
			sql = "update clerk set CLERKTEL=" + tel + " where  CLERKID=" + clerkId;

			break;
		case 3:
			// 修改密码
			sql = "update clerk set password='" + thing + "' where  CLERKID=" + clerkId;
			break;
		case 4:
			// 修改工作
			sql = "update clerk set CLERKTYPE='" + thing + "' where  CLERKID=" + clerkId;

			break;
		default:
			return false;
		}
		try {
			int q = db.update(sql);
			if (q != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			db.closed();
		}
		
	}

	public boolean updateClerkStatus(int clerkId, String Status) {
		// TODO Auto-generated method stub
		this.db = new DBUtil();
		String sql = "update clerk set status='" + Status + "' where clerkid=" + clerkId;
		try {
			int q = this.db.update(sql);
			if (q != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closed();
		}

		return false;
	}

	public boolean insertClerk(Clerk clerk) {
		// TODO Auto-generated method stub
		// CLERKID CLERKNAME CLERKTEL PASSWORD CLERKTYPE STATUS
		this.db = new DBUtil();
		String sql = "insert into clerk values (seq_clerk.nextval,?,?,?,?,?)";
        try {
			int q = this.db.update(sql,clerk.getClerkName(),clerk.getClerkTel(),clerk.getPassword(),clerk.getClerkType(),clerk.getStatus());
			if (q != 0) {
				return true;
			} else {
				return false;
			}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.closed();
		}
		return false;
	}

	public boolean deleteClerk(int clerkId) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql ="delete from clerk where clerkId="+clerkId;
		try {
			int q = db.update(sql);
			if (q != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.closed();
		}
		
		return false;
	}

	public List<Clerk> selectClerkBytype(String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="select * from clerk where CLERKTYPE='"+type+"'";
		List<Clerk> list=new ArrayList<Clerk>();
		try {
			ResultSet rs = this.db.query(sql);
			while (rs.next()) {
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
				list.add(c);

			}
			if (list != null) {
				return list;

			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Clerk selectSendClerkBytype(String type) {
		// TODO Auto-generated method stub
		this.db=new DBUtil();
		String sql="select * from clerk where STATUS='"+type+"' and CLERKTYPE='配送员'";
		try {
			ResultSet rs = this.db.query(sql);
			if (rs.next()) {
				Clerk c = new Clerk();
				c.setClerkId(rs.getInt("CLERKID"));
				c.setClerkName(rs.getString("CLERKNAME"));
				c.setClerkTel(rs.getLong("CLERKTEL"));
				c.setPassword(rs.getString("PASSWORD"));
				c.setClerkType(rs.getString("CLERKTYPE"));
				c.setStatus(rs.getString("STATUS"));
			    return c;

			}
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return null;
	}

}
