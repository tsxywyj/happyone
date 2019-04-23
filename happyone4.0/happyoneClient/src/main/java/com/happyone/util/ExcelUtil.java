package com.happyone.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.happyone.control.ProxyClient;
import com.happyone.domain.Orders;
import com.happyone.service.inter.ServiceBiz;

public class ExcelUtil {
    private ServiceBiz sb;
    private List<Orders> list;
	public ExcelUtil() {
		// TODO Auto-generated constructor stub
		this.sb=ProxyClient.getClient(ServiceBiz.class, "10.10.49.106", 6666);
	}

	public void setExcel() {
		list=this.sb.selectOrdersAll();
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
		cell.setCellValue("订单编号");
		cell.setCellStyle(style);

		cell = row.createCell(1); // 第二个单元格
		cell.setCellValue("订单号");
		cell.setCellStyle(style);
        //return getOrderId()getOrderNo()getUserId()getOrderDate()getSumPrice()getIsSend()getStatus();
		cell = row.createCell(2); 
		cell.setCellValue("用户编号");
		cell.setCellStyle(style);
		cell = row.createCell(3); 
		cell.setCellValue("订单日期");
		cell.setCellStyle(style);
		cell = row.createCell(4); 
		cell.setCellValue("订单总价");
		cell.setCellStyle(style);
		cell = row.createCell(5); 
		cell.setCellValue("是否配送");
		cell.setCellStyle(style);
		cell = row.createCell(6); 
		cell.setCellValue("订单状态");
		cell.setCellStyle(style);
		// 第五步插入数据

		for (int i = 0; i < list.size(); i++) {
			// 创建行
			row = sheet.createRow(i + 1);
			// 创建单元格并且添加数据
			row.createCell(0).setCellValue(list.get(i).getOrderId());
			row.createCell(1).setCellValue(list.get(i).getOrderNo());
			row.createCell(2).setCellValue(list.get(i).getUserId());
			row.createCell(3).setCellValue(list.get(i).getOrderDate());
			row.createCell(4).setCellValue(list.get(i).getSumPrice());
			row.createCell(5).setCellValue(list.get(i).getIsSend());
			row.createCell(6).setCellValue(list.get(i).getStatus());
		}

		// 第六步将生成excel文件保存到指定路径下
		try {
			FileOutputStream fout = new FileOutputStream("data/a.xls");
			wb.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Excel文件生成成功...");
	}

}
