package com.happyone.control;

import java.util.ArrayList;
import java.util.List;


import com.happyone.domain.Activity;
import com.happyone.domain.Clerk;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.SendOrders;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.ExcelUtil;
import com.happyone.util.UserInput;
import com.happyone.view.ClerkView;
import com.happyone.view.ManagerView;

public class ManagerControl {
	private UserInput ui;
	private ServiceBiz sb;
	private Clerk clerk;
	private ManagerView mv;
	

	public ManagerControl(Clerk clerk,ServiceBiz sb) {
		// TODO Auto-generated constructor stub
		this.ui = new UserInput();
		
		this.clerk = clerk;
		this.mv = new ManagerView();
		this.sb=sb;

	}

	public void managerStart() {
		while (true) {
			this.mv.managerViewAll();
			int chooseAll = this.ui.getInt("请选择");
			switch (chooseAll) {
			case 1:
				this.managerClerk();
				break;
			case 2:
				this.managerProduct();
				break;
			case 3:
				this.managerActivity();
				break;
			case 4:
				this.managerOrders();
				break;
			case 5:
				// 统计
				break;

			}
			if (chooseAll == 0) {
				this.sb.updateClerkStatus(clerk.getClerkId(), "下班");
				return;
			}
		}
	}

	public void managerClerk() {
		while (true) {
			this.mv.managerViewClerk();
			int chooseClerk = this.ui.getInt("请选择");
			switch (chooseClerk) {
			case 1:
				List<Clerk> listAll = this.sb.selectClerkAll();
				this.mv.println("职工编号\t职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
				for (int i = 0; i < listAll.size(); i++) {
					this.mv.println((i + 1) + "\t" + listAll.get(i).toString());
				}
				break;
			case 2:
				long clerkTel = this.ui.getLong("请输入手机号");
				Clerk clerkBytel = this.sb.selectClerkBytel(clerkTel);
				if (clerkBytel != null) {
					this.mv.println("职工编号\t职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
					this.mv.println(clerk.getClerkId() + "\t" + clerkBytel.toString());
				} else {
					this.mv.println("职工不存在");
				}
				break;
			case 3:
				this.mv.println("请选择职工类型");
				this.mv.println("1.普通店员");
				this.mv.println("2.配送员");
				int seltype = this.ui.getInt("请选择");
				String selecttype = "";
				switch (seltype) {
				case 1:
					selecttype = "普通员工";
					break;
				case 2:
					selecttype = "配送员";
					break;
				default:
					break;
				}
				List<Clerk> listtype = this.sb.selectClerkBytype(selecttype);
				if (listtype != null) {
					this.mv.println("职工编号\t职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
					for (int i = 0; i < listtype.size(); i++) {
						this.mv.println((i + 1) + "\t" + listtype.get(i).toString());
					}
				}
				break;
			case 4:

				this.mv.println("请选择职工类型");
				this.mv.println("1.普通店员");
				this.mv.println("2.配送员");
				int ctype = this.ui.getInt("请选择");
				String type = "";
				switch (ctype) {
				case 1:
					type = "普通员工";
					break;
				case 2:
					type = "配送员";
					break;
				default:
					break;
				}
				String cname = this.ui.getString("请输入新员工名字");
				long ctel = this.ui.getLong("请输入新员工手机号");
				String cpasswork = this.ui.getString("请输入新员工的密码");
				if (this.sb.insertClerk(1, cname, type, ctel, cpasswork, "下班")) {
					this.mv.println("添加成功");
				} else {

					this.mv.println("添加成功");
				}

				break;
			case 5:
				System.out.println("您的信息如下");
				this.mv.println("职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
				this.mv.println(clerk.toString());
				this.mv.println("请选择您要修改的信息字段");
				this.mv.println("1.职工姓名");
				this.mv.println("2.职工手机号");
				this.mv.println("3.职工密码");
				int updatei = this.ui.getInt("请选择");
				String updates = this.ui.getString("请输入新的信息");
				if (this.sb.updateClerkByid(clerk.getClerkId(), updates, updatei)) {
					System.out.println("修改成功,重新登陆后生效");

				} else {
					System.out.println("修改失败");
				}
				break;
			case 6:
				List<Clerk> listAll2 = this.sb.selectClerkAll();
				this.mv.println("职工编号\t职工姓名\t职工手机号\t\t职工密码\t职工类型\t职工状态");
				for (int i = 0; i < listAll2.size(); i++) {
					this.mv.println((i + 1) + listAll2.get(i).toString());
				}
				int choosetype = this.ui.getInt("请输入员工编号");
				String type2 = "";
				this.mv.println("1.普通店员");
				this.mv.println("2.配送员");
				int ctype2 = this.ui.getInt("请输入工作类型");
				switch (ctype2) {
				case 1:
					type2 = "普通员工";
					break;
				case 2:
					type2 = "配送员";
					break;
				default:
					break;
				}
				if (this.sb.updateClerkByid(listAll2.get(choosetype - 1).getClerkId(), type2, 4)) {
					System.out.println("修改成功");
				} else {
					System.out.println("修改失败");
				}
				break;
			}
			if (chooseClerk == 0) {

				return;
			}
		}
	}

	public void managerProduct() {
		List<Product> list;
		while (true) {
			this.mv.managerViewProduct();
			int chooseProduct = this.ui.getInt("请选择");
			switch (chooseProduct) {
			case 1:
				list = this.sb.selectProductAll();
				if (list.size() > 0) {
					this.mv.println("商品编号\t商品名称\t\t商品类型\t商品价格\t是否上架");
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsShelves());
					}
				}
				break;
			case 2:
				this.mv.println("1.惊喜套餐");
				this.mv.println("2.主食");
				this.mv.println("3.饮品");
				this.mv.println("4.小食");
				int chooseType = this.ui.getInt("请选择");
				String productType = "";
				switch (chooseType) {
				case 1:
					productType = "套餐";
					break;
				case 2:
					productType = "主食";
					break;
				case 3:
					productType = "饮品";
					break;
				case 4:
					productType = "小食";
					break;
				default:
					break;
				}

				list = this.sb.selectProductBytype(productType);
				if (list.size() > 0) {
					this.mv.println("商品编号\t商品名称\t\t商品类型\t商品价格\t是否上架");
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsShelves());
					}
				}
				break;
			case 3:
				this.mv.println("请输入新商品的类型");
				this.mv.println("1.惊喜套餐");
				this.mv.println("2.主食");
				this.mv.println("3.饮品");
				this.mv.println("4.小食");
				int chooseNewType = this.ui.getInt("请选择");
				String productNewType = "";
				switch (chooseNewType) {
				case 1:
					productNewType = "套餐";
					break;
				case 2:
					productNewType = "主食";
					break;
				case 3:
					productNewType = "饮品";
					break;
				case 4:
					productNewType = "小食";
					break;
				default:
					break;
				}
				String productNewName = this.ui.getString("请输入新商品的名称");
				double productNewPrice = this.ui.getDouble("请输入新商品的价格");
				if (this.sb.insertProduct(0, productNewName, productNewType, productNewPrice, "是")) {
					this.mv.println("添加成功");

				} else {
					this.mv.println("添加失败");
				}
				break;
			case 4:
				list = this.sb.selectProductByis("否");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsShelves());
					}
					int updateId = this.ui.getInt("请选择商品编号");
					String updateWhat = "";
					this.mv.println("1.修改商品名称");
					this.mv.println("2.修改商品类型");
					this.mv.println("3.修改商品价格");
					int updateWha = this.ui.getInt("请选择您要修改的商品信息");
					if (updateWha == 2) {
						this.mv.println("1.惊喜套餐");
						this.mv.println("2.主食");
						this.mv.println("3.饮品");
						this.mv.println("4.小食");
						int updateType = this.ui.getInt("请选择");
						String updateThing = "";
						switch (updateType) {
						case 1:
							updateThing = "套餐";
							break;
						case 2:
							updateThing = "主食";
							break;
						case 3:
							updateThing = "饮品";
							break;

						case 4:
							updateThing = "小食";
							break;
						default:
							continue;
						}
						if (this.sb.updateProductByid(list.get((updateId - 1)).getProductId(), updateThing,
								updateWha)) {

							this.mv.println("修改成功");
						} else {

							this.mv.println("修改失败");
						}
						continue;
					}
					String updateThing = this.ui.getString("请输入新的信息");
					if (this.sb.updateProductByid(list.get((updateId - 1)).getProductId(), updateThing, updateWha)) {

						this.mv.println("修改成功");
					} else {

						this.mv.println("修改失败");
					}

				} else {
					System.out.println("上架中的商品不能修改");

				}

				break;
			case 5:
				list = this.sb.selectProductByis("否");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsShelves());
					}
					int upChoose = this.ui.getInt("输入上架的商品");
					if (this.sb.upProductByid(list.get((upChoose - 1)).getProductId())) {
						System.out.println("上架成功");
					} else {
						System.out.println("上架失败");
					}
				} else {

					System.out.println("暂无需要上架的商品");
					continue;
				}
				break;
			case 6:
				list = this.sb.selectProductByis("是");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsShelves());
					}
					int upChoose = this.ui.getInt("输入下架的商品");
					if (this.sb.downProductByid(list.get((upChoose - 1)).getProductId())) {
						System.out.println("下架成功");
					} else {
						System.out.println("下架失败");
					}
				} else {
					System.out.println("暂无需要下架的商品");
					continue;
				}
				break;
			}
			if (chooseProduct == 0) {

				return;
			}
		}

	}

	public void managerActivity() {
		List<Activity> list;
		while (true) {
			this.mv.managerViewActivity();
			int chooseProduct = this.ui.getInt("请选择");
			switch (chooseProduct) {
			case 1:
				list = this.sb.selectActivityAll();
				if (list.size() > 0) {
					this.mv.println("活动编号\t活动名称\t活动描述\t活动商品名称\t活动价格\t会员等级限制\t是否上架");
					for (int i = 0; i < list.size(); i++) {

						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getUserTypeId()
								+ "\t" + list.get(i).getIsshelves());
					}
				}
				break;
			case 2:
				this.mv.println("请输入新商品的类型");
				this.mv.println("1.惊喜套餐");
				this.mv.println("2.主食");
				this.mv.println("3.饮品");
				this.mv.println("4.小食");
				int chooseNewType = this.ui.getInt("请选择");
				String productNewType = "";
				switch (chooseNewType) {
				case 1:
					productNewType = "套餐";
					break;
				case 2:
					productNewType = "主食";
					break;
				case 3:
					productNewType = "饮品";
					break;
				case 4:
					productNewType = "小食";
					break;
				default:
					break;
				}
				List<Product> plist = this.sb.selectProductBytype(productNewType);
				boolean b = false;
				for (int i = 0; i < plist.size(); i++) {
					if (plist.get(i).getIsShelves().equals("是")) {
						b = true;
						break;
					}

				}

				if (plist.size() > 0 && b == true) {
					this.mv.println("商品编号\t商品名称\t\t商品类型\t商品价格\t是否上架");
					int j = 1;
					for (int i = 0; i < plist.size(); i++) {
						if (plist.get(i).getIsShelves().equals("是")) {
							this.mv.println((j) + "\t" + plist.get(i).toString() + "\t" + plist.get(i).getIsShelves());
							j++;
						}
					}
				} else {
					System.out.println("没有当前类型商品");
					continue;
				}
				int chooseType = this.ui.getInt("请输出新活动的商品编号");
				int newProductID = plist.get(chooseType - 1).getProductId();
				String activityNewName = this.ui.getString("请输入新活动的名称");
				String activityContent = this.ui.getString("请输入新的活动描述");
				int userTypeId = this.ui.getInt("请输入新的活动的会员要求（1~5）");
				if (userTypeId > 5 || userTypeId < 1) {
					System.out.println("会员要求不合法");
					continue;
				}
				double productNewPrice = this.ui.getDouble("请输入新活动的价格");
				if (this.sb.insertActivity(activityNewName, activityContent, newProductID, productNewPrice, userTypeId,
						"是")) {
					this.mv.println("添加成功");

				} else {
					this.mv.println("添加失败");
				}
				break;
			case 3:
				list = this.sb.selectActivityByis("否");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getUserTypeId()
								+ "\t" + list.get(i).getIsshelves());
					}
					int updateId = this.ui.getInt("请选择活动编号");
					String updateWhat = "";
					this.mv.println("1.修改活动名称");
					this.mv.println("2.修改活动描述");

					this.mv.println("3.修改活动价格");
					this.mv.println("4.修改会员等级限制");
					int updateWha = this.ui.getInt("请选择您要修改的活动信息");
					String updateThing = this.ui.getString("请输入新的信息");
					if (this.sb.updateActivityByid(list.get((updateId - 1)).getActivityId(), updateThing, updateWha)) {

						this.mv.println("修改成功");
					} else {

						this.mv.println("修改失败");
					}

				} else {
					System.out.println("上架中的商品不能修改");

				}

				break;
			case 4:
				list = this.sb.selectActivityByis("否");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsshelves());
					}
					int upChoose = this.ui.getInt("输入上架的活动");
					if (this.sb.upActivityByid(list.get((upChoose - 1)).getActivityId())) {
						System.out.println("上架成功");
					} else {
						System.out.println("上架失败");
					}
				} else {
					System.out.println("暂无需要上架的活动");
					continue;
				}
				break;
			case 5:
				list = this.sb.selectActivityByis("是");
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						this.mv.println((i + 1) + "\t" + list.get(i).toString() + "\t" + list.get(i).getIsshelves());
					}
					int upChoose = this.ui.getInt("输入下架的活动");
					if (this.sb.downActivityByid(list.get((upChoose - 1)).getActivityId())) {
						System.out.println("下架成功");
					} else {
						System.out.println("下架失败");
					}
				} else {
					System.out.println("暂无需要下架的活动");
					continue;
				}
				break;
			}
			if (chooseProduct == 0) {

				return;
			}
		}
	}

	public void managerOrders() {
		List<Orders> list = new ArrayList<Orders>();
		List<SendOrders> slist = new ArrayList<SendOrders>();
        List<Product> plist=new ArrayList<Product>();
		while (true) {
			this.mv.managerViewOrder();
			int chooseOrder = this.ui.getInt("请选择");
			switch (chooseOrder) {

			case 1:
				list = this.sb.selectOrdersAll();
				if (list.size() != 0) {
					this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
					for (int i = 0; i < list.size(); i++) {
						this.mv.println(list.get(i).toString());
					}

				} else {
					this.mv.println("当前没有订单");

				}
				break;
			case 2:
				slist = this.sb.selectSendOrderAll();
				if (slist.size() != 0) {
					this.ui.println("订单编号\t订单号\t订单电话\t\t订单地址\t配送员姓名\t订单状态");
					for (int i = 0; i < slist.size(); i++) {
						System.out.println((i + 1) + "\t" + slist.get(i).toString());
					}

				} else {
					System.out.println("暂无配送订单");
					return;
				}
				break;
			case 3:
				ExcelUtil ex = new ExcelUtil();
				ex.setExcel();
				break;
			case 4:
				int month=this.ui.getInt("输入查询的月份");
				list=this.sb.selectOrdersAll();
				List<Orders> newlist=new ArrayList<Orders>();
				for(int i=0;i<list.size();i++){
					String time=list.get(i).getOrderDate().toString();
				    String[] arr=time.split("-");
				    int m=Integer.parseInt(arr[1]);
				    if(m==month){
				    	newlist.add(list.get(i));
				    }
				}
				if(newlist.size()==0){
					this.ui.println("该月没有订单");
					continue;
				}
				double sum=0;
				this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
				for(int i=0;i<newlist.size();i++){
					sum=newlist.get(i).getSumPrice()+sum;
					this.ui.println(newlist.get(i).toString());
				}
				this.ui.println("该月的销售总额是:"+sum);
				break;
			case 5:
				String day=this.ui.getString("输入查询的日期");
				list=this.sb.selectOrdersAll();
				List<Orders> newlist2=new ArrayList<Orders>();
				for(int i=0;i<list.size();i++){
					String time=list.get(i).getOrderDate().toString();
				
				    if(time.equals(day)){
				    	newlist2.add(list.get(i));
				    }
				}
				if(newlist2.size()==0){
					this.ui.println("该日没有订单");
					continue;
				}
				double sum2=0;
				this.ui.println("订单编号\t订单号\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态");
				for(int i=0;i<newlist2.size();i++){
					sum2=newlist2.get(i).getSumPrice()+sum2;
					this.ui.println(newlist2.get(i).toString());
				}
				this.ui.println("该日的销售总额是:"+sum2);
				break;
			case 6:
				List<Product> prolist=this.sb.selectProductAll();
				if (prolist.size() > 0) {
					this.mv.println("商品编号\t商品名称\t\t商品类型\t商品价格\t是否上架");
					for (int i = 0; i < prolist.size(); i++) {
						this.mv.println((i + 1) + "\t" + prolist.get(i).toString() + "\t" + prolist.get(i).getIsShelves());
					}
				}
				int proid=this.ui.getInt("请输入查询的商品编号");
				int productid=prolist.get(proid-1).getProductId();
				
				plist=this.sb.selectSonOrderAll();
				int count=0;
				double psum=0;
				for(int i=0;i<plist.size();i++){
					if(plist.get(i).getProductId()==productid)
					{
						count++;
					}
				}
				psum=count*prolist.get(proid-1).getProductPrice();
				this.ui.println(prolist.get(proid-1).getProductName()+"共销售了"+count+"次，销售额是:"+psum);
				break;
			}

			if (chooseOrder == 0) {

				return;
			}
		}
	}
}
