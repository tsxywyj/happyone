package com.happyone.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.happyone.biz.impl.ManagerOrdersBizImpl;
import com.happyone.util.DBUtil;

public class MyTest {

	@Test
	public void testOne() {
		ManagerOrdersBizImpl mb=new ManagerOrdersBizImpl();
        System.out.println(mb.updateSendOrder(23, "配送中"));
		
	}
}
