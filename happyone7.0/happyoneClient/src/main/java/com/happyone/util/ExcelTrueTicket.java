package com.happyone.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.happyone.domain.Activity;
import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;

public class ExcelTrueTicket {
    private Orders order;
    private List<Product> list;
    private Clerk clerk;
	public ExcelTrueTicket(Orders order,List<Product> list,Clerk clerk) {
		// TODO Auto-generated constructor stub
		this.order=order;
		this.list=list;
		this.clerk=clerk;
	}
	public void setExcel() {
		// 第一步创建workbook
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步创建sheet
		HSSFSheet sheet = wb.createSheet("测试");

		// 第三步创建行row:添加表头0行
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中

		// 第四步创建单元格
		HSSFCell cell = row.createCell(0); // 第一个单元格
		cell.setCellValue("麦当鸡etc欢迎您");
		cell.setCellStyle(style);

		row = sheet.createRow(1);//创建新行
		cell = row.createCell(0);//创建第一个单元格
		cell.setCellValue("订单号:");
		cell.setCellStyle(style);
        
		cell = row.createCell(1); 
		cell.setCellValue(order.getOrderNo());
		cell.setCellStyle(style);
		row = sheet.createRow(2);//创建新行
		cell = row.createCell(0); 
		cell.setCellValue("收银员工:");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue(clerk.getClerkName());
		cell.setCellStyle(style);
		
		row = sheet.createRow(3);//创建新行
		cell = row.createCell(0); 
		cell.setCellValue("订单时间");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue(order.getOrderDate());
		cell.setCellStyle(style);
		// 第五步插入数据
		//"商品编号\t商品名称\t商品数量\t商品总价");
		row = sheet.createRow(4);//创建新行
		cell = row.createCell(0); 
		cell.setCellValue("商品编号");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);
		cell = row.createCell(2); 
		cell.setCellValue("商品数量");
		cell.setCellStyle(style);
		cell = row.createCell(3); 
		cell.setCellValue("商品总价");
		cell.setCellStyle(style);
		int i=0;
		for ( i =0; i < list.size(); i++) {
			// 创建行
			row = sheet.createRow(i + 5);
			// 创建单元格并且添加数据
			row.createCell(0).setCellValue(list.get(i).getProductId());
			row.createCell(1).setCellValue(list.get(i).getProductName());
			row.createCell(2).setCellValue(list.get(i).getProductNum());
			row.createCell(3).setCellValue(list.get(i).getProductPrice());
		}
		row = sheet.createRow(i + 6);
		cell = row.createCell(0); 
		cell.setCellValue("是否配送：");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue(order.getIsSend());
		cell.setCellStyle(style);
		
		
		row = sheet.createRow(i + 7);
		cell = row.createCell(0); 
		cell.setCellValue("订单总价：");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue(order.getSumPrice());
		cell.setCellStyle(style);
		  
		
		row = sheet.createRow(i + 8);
		cell = row.createCell(0); 
		cell.setCellValue("会员积分增长：");
		cell.setCellStyle(style);
		cell = row.createCell(1); 
		cell.setCellValue((int)order.getSumPrice());
		cell.setCellStyle(style);
		// 第六步将生成excel文件保存到指定路径下
		try {
			FileOutputStream fout = new FileOutputStream("orders/"+order.getOrderNo()+".xls");
			wb.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("实体小票文件生成成功...");
	}

}
