package com.happyone.control;

import java.util.ArrayList;
import java.util.List;

import com.happyone.domain.Activity;
import com.happyone.domain.Discuss;
import com.happyone.domain.Orders;
import com.happyone.domain.Product;
import com.happyone.domain.Users;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.UserInput;
import com.happyone.view.View;

public class Control {
	private View v;
	private UserInput ui;
	private ServiceBiz sb = ProxyClient.getClient(ServiceBiz.class, "10.10.49.106", 6666);
	// 创建一个list集合用来存储用户所选择商品的信息
	private List<Product> list;

	private ClerkControl cc;

	public Control() {
		super();
		this.v = new View();
		this.ui = new UserInput();

		this.list = new ArrayList<Product>();

		this.cc = new ClerkControl(sb);
	}

	// 初始页面
	public void start() {
		while (true) {
			this.v.welcome();
			int select1 = this.ui.getInt("请选择：");
			if (select1 == 1) {
				// 用户登录
				this.userLogin();
				this.usershelf();
			} else if (select1 == 2) {
				// 注册方法
				this.userRegiter();
			} else if (select1 == 3) {
				// 职工登录方法
				this.cc.startClerk();
			} else if (select1 == 4) {
				System.exit(0);
			} else {
				continue;

			}
		}

	}

	// 用户页面
	public void usershelf() {
		while (true) {
			this.v.userLogin();
			int select2 = this.ui.getInt("请选择：");
			if (select2 == 1) {
				// 查看菜单
				this.showProduct();
			} else if (select2 == 2) {
				// 修改个人信息
				this.updateUserInfo(user);
			} else if (select2 == 3) {
				// 查看活动
				this.showActivity();
			} else if (select2 == 4) {
				// 查看我的购物车
				if (this.list.size() == 0 || this.list == null) {
					this.v.println("您的购物车空空如也，快去选购吧！");
				} else {
					this.showCart(list);
					this.upOrder();
				}

			} else if (select2 == 5) {
				// 查看我的订单
				this.selectAllOrder(user);
			} else if (select2 == 6) {
				// 查看个人信息
				this.selectUser(user);
			}  else if (select2 == 7) {
				// 查看我的评价
				this.selectAllDiscuss(user);
			} else if (select2 == 8) {
				// 返回上一层
				break;
			} else if (select2 == 0) {
				// 直接退出系统
				System.exit(0);
			} else {
				continue;

			}
		}

	}

	// 用户登录
	Users user = null;

	public void userLogin() {
		while (true) {
			long tel = this.ui.getLong("请输入您的手机号：");
			String password = this.ui.getString("请输入您的密码：");
			user = this.sb.login(tel, password);
			if (user != null) {
				break;
			}
			this.v.println("输入有误！");
		}
		this.v.println("欢迎用户" + user.getUsername() + "访问,您的用户等级为" + user.getMembertypeid());
	}

	// 用户注册
	public void userRegiter() {
		Users u = null;
		String username = this.ui.getString("请输入用户名");
		long tel = this.ui.getLong("请输入手机号");
		Users users = this.sb.selectUserByTel(tel);
		if (users != null) {
			this.v.println("该手机号已注册！");
			return;
		}
		String password = this.ui.getString("请输入密码");
		String address = this.ui.getString("请输入您的地址");
		u = new Users(tel, password, username, 1, 0, address);
		String s = this.sb.addUsers(u);
		this.v.println(s);
	}

	// 查看菜单
	public void showProduct() {
		while (true) {
			Users updateUser = null;
			this.v.println("------------------------------------------");
			this.showMenu();
			this.v.buy();
			int select3 = this.ui.getInt("请选择：");
			if (select3 == 1) {

				loop: while (true) {
					int proid = this.ui.getInt("请选择商品编号(按0结束)：");
					Product prod = this.sb.selectProById(proid, 1);
					if (prod == null && proid != 0) {
						this.v.println("该商品不存在");
						continue;
					}
					if (proid == 0) {
						if (list != null) {
							this.v.println("您的商品信息如下：");
							// 显示购物车
							this.showCart(list);
							String s = this.ui.getString("是否提交订单?(y/n)");
							if (s.equals("n")) {
								break;
							}
							int i = this.ui.getInt("1.到店自取/2.需要配送");
							if (s.equals("y") && i == 1) {
								// 更新会员积分
								updateUser = this.sb.updateUser(user, list);

								// 存入订单表
								String orderno = this.sb.insertOrders(user, list, i);
								// 存入子订单表
								this.sb.insertSonOrder(list, orderno);
								// for (int j = 0; j < list.size(); j++) {
								// list.remove(j);
								// }
								list.removeAll(list);
								user = updateUser;
							} else if (s.equals("y") && i == 2) {
								// 更新会员积分
								updateUser = this.sb.updateUser(user, list);

								// 存入订单表
								String orderno = this.sb.insertOrders(user, list, i);
								// 存入子订单表
								this.sb.insertSonOrder(list, orderno);
								// 存入配送表
								// this.obiz.insertSendOrder(user,orderno);
								// for (int j = 0; j < list.size(); j++) {
								// list.remove(j);
								// }
								list.removeAll(list);
								user = updateUser;
							} else {
								continue;

							}
						}
						break;
					}
					int pronum = this.ui.getInt("请选择" + proid + "商品数量：");
					// 通过商品id查出商品信息，存入list集合（购物车）中
					Product pro = this.sb.selectProById(proid, pronum);
					// 遍历集合，如果加入过该商品，则只增加数量

					if (list.size() != 0) {
						for (Product product : list) {
							if (proid == product.getProductId()) {
								product.setProductNum(product.getProductNum() + pronum);
								continue loop;
							}
						}
					}

					list.add(pro);

				}
			} else if (select3 == 2) {
				break;
			}

		}

	}

	// 查看购物车后提交订单
	public void upOrder() {
		Users updateUser = null;
		String s = this.ui.getString("是否提交订单?(y/n)");
		if (s.equals("n")) {
			return;
		}
		int i = this.ui.getInt("1.到店自取/2.需要配送");
		if (s.equals("y") && i == 1) {
			// 更新会员积分
			updateUser = this.sb.updateUser(user, list);
			// 存入订单表
			String orderno = this.sb.insertOrders(user, list, i);
			// 存入子订单表
			this.sb.insertSonOrder(list, orderno);
			// for (int j = 0; j < list.size(); j++) {
			// list.remove(j);
			// }
			list.removeAll(list);
		} else if (s.equals("y") && i == 2) {
			// 更新会员积分
			updateUser = this.sb.updateUser(user, list);
			// 存入订单表
			String orderno = this.sb.insertOrders(user, list, i);
			// 存入子订单表
			this.sb.insertSonOrder(list, orderno);
			// 存入配送表

			// this.obiz.insertSendOrder(user,orderno);
			// for (int j = 0; j < list.size(); j++) {
			// list.remove(j);
			// }
			list.removeAll(list);
		} else {
			return;

		}
		user = updateUser;
	}

	// 展示菜单列表的方法
	public void showMenu() {
		System.out.println("商品id" + "\t" + "商品名称" + "\t\t" + "商品描述" + "\t\t" + "商品价格");
		List<Product> showPro = this.sb.showPro();
		for (Product product : showPro) {
			System.out.println(product.toString2());
		}
	}

	// 显示购物车列表的方法
	public void showCart(List<Product> list) {
		System.out.println("商品id" + "\t\t" + "商品名称" + "\t\t" + "商品单价" + "\t\t" + "商品数量" + "\t\t" + "小计");
		double sum = 0;
		for (Product pro : list) {
			double sumPrice = pro.getProductNum() * pro.getProductPrice();
			System.out.println(pro.getProductId() + "\t\t" + pro.getProductName() + "\t\t" + pro.getProductPrice()
					+ "\t\t" + pro.getProductNum() + "\t\t" + sumPrice);
			sum = sum + sumPrice;

		}
		this.v.println("总计：" + sum);

	}

	// 修改个人信息的方法
	private void updateUserInfo(Users u) {

		// 通过用户手机号查找到该用户

		while (true) {
			String pwd1 = this.ui.getString("请输入要修改的密码：");
			String pwd2 = this.ui.getString("请再次输入密码");
			if (!pwd1.equals(pwd2)) {
				this.v.println("两次输入不一致");
				continue;
			}
			String name = this.ui.getString("请输入要修改的名字：");
			String address = this.ui.getString("请输入要修改的地址:");
			Users users = new Users(u.getTel(), pwd1, name, u.getMembertypeid(), u.getIntegral(), address);
			String s = this.sb.updateUserInfo(users);
			this.v.println(s);
			user = users;
			break;
		}

	}

	// 查看订单
	public void selectAllOrder(Users u) {
		List<Orders> listorder = new ArrayList<Orders>();
		listorder = this.sb.selectAllOrder(u);
		System.out.println("订单号\t\t\t订单总额\t\t订单日期\t\t是否配送\t\t订单状态");
		for (Orders orders : listorder) {

			System.out.println(orders.getOrderNo() + "\t" + orders.getSumPrice() + "\t\t" + orders.getOrderDate() + "\t"
					+ orders.getIsSend() + "\t\t" + orders.getStatus());
		}
		List<Orders> Unreceived = this.sb.selectOrderUnreceived(u);
		if (Unreceived.size() == 0) {
			System.out.println("暂时没有需要收货的订单");
			return;
		}
		this.v.println("以下订单还未确认收货，是否确认收货?(y/n)");

		System.out.println("订单id\t\t订单号\t\t\t订单总额\t\t订单日期\t\t是否配送\t\t订单状态");
		for (Orders orders : Unreceived) {

			System.out.println(orders.getOrderId() + "\t\t" + orders.getOrderNo() + "\t" + orders.getSumPrice() + "\t\t"
					+ orders.getOrderDate() + "\t" + orders.getIsSend() + "\t\t" + orders.getStatus());
		}
		String s = this.ui.getString("请选择：");
		if (s.equals("y")) {
			while (true) {
				int int1 = this.ui.getInt("请输入要确认收货的订单id(按0退出)");
				if (int1 == 0) {
					break;
				}
				// 判断订单状态是否已收货
				Orders od = this.sb.selectOrderById(int1);
				if (od == null) {
					this.v.println("不存在当前订单");
					continue;
				}
				if (od.getStatus().equals("交易完成")) {
					this.v.println("该订单已交易完成，请重新选择！");
				} else {
					// 改变订单的收货状态
					String alterOrderStatus = this.sb.alterOrderStatus(int1);
					this.v.println(alterOrderStatus);
					// 是否评价
					String orderNo = od.getOrderNo();
					String isdis = this.ui.getString("是否评价？(y/n)");
					if (isdis.equals("y")) {
						int distype = this.ui.getInt("请输入评价类型(1.好评2.中评3.差评)");
						String dtype = null;
						if (distype == 1) {
							dtype = "好评";
						} else if (distype == 1) {
							dtype = "中评";
						} else if (distype == 1) {
							dtype = "差评";
						}
						String discontent = this.ui.getString("请输入评价内容");
						Discuss d = new Discuss(1, orderNo, dtype, discontent);
						String insertDiscuss = this.sb.insertDiscuss(d);
						this.v.println(insertDiscuss);
					}
					else{
						Discuss d=new Discuss(1, orderNo, "好评", "未评价");
						String insertDiscuss = this.sb.insertDiscuss(d);
					}

				}
			}
		}
	}
	//查看用户评价
		public void selectAllDiscuss(Users user) {
			List<Orders> selectAllOrder = this.sb.selectAllOrder(user);
			List<Orders> newlist=new ArrayList<Orders>();
			for (int i = 0; i < selectAllOrder.size(); i++) {
				if(selectAllOrder.get(i).getStatus().equals("交易完成")){
					newlist.add(selectAllOrder.get(i));
					
				}
			}
			if(newlist.size()==0){
				System.out.println("您暂时没有评价呢");
				return;
			}
			this.ui.println("订单编号\t订单号\t\t\t订单用户id\t订单时间\t订单总价\t是否配送\t订单状态\t评价类型\t评价内容");
			for (int i = 0; i < newlist.size(); i++) {
				Discuss selectDiscussByno = this.sb.selectDiscussByno(newlist.get(i).getOrderNo());
				
				System.out.println(newlist.get(i).toString()+"\t"+selectDiscussByno.toString());
			}
			
		}

	// 查看活动
	public void showActivity() {
		Users updateUser = null;
		// System.out.println(user.getAddress());
		while (true) {
			this.v.println("------------------------------------------");
			this.showActiveMenu(user);
			this.v.buy();
			int select = this.ui.getInt("请选择：");
			if (select == 1) {

				loop: while (true) {
					int actid = this.ui.getInt("请选择活动商品编号(按0结束)：");
					int productid = this.sb.selectProId(actid);
					Product prod = this.sb.selectProById(productid, 1);
					if (prod == null && actid != 0) {
						this.v.println("该商品不存在");
						continue;
					}
					if (actid == 0) {
						if (list != null) {
							this.v.println("您的商品信息如下：");
							// 显示购物车
							this.showCart(list);
							String s = this.ui.getString("是否提交订单?(y/n)");
							if (s.equals("n")) {
								break;
							}
							int i = this.ui.getInt("1.到店自取/2.需要配送");
							if (s.equals("y") && i == 1) {
								// 更新会员积分
								updateUser = this.sb.updateUser(user, list);

								// 存入订单表
								String orderno = this.sb.insertOrders(user, list, i);
								// 存入子订单表
								this.sb.insertSonOrder(list, orderno);
								// for (int j = 0; j < list.size(); j++) {
								// list.remove(j);
								// }
								list.removeAll(list);
								user = updateUser;
							} else if (s.equals("y") && i == 2) {
								// 更新会员积分
								updateUser = this.sb.updateUser(user, list);
								// 存入订单表
								String orderno = this.sb.insertOrders(user, list, i);
								// 存入子订单表
								this.sb.insertSonOrder(list, orderno);
								// 存入配送表
								// this.obiz.insertSendOrder(user,orderno);
								// for (int j = 0; j < list.size(); j++) {
								// list.remove(j);
								// }
								list.removeAll(list);
								user = updateUser;
							} else {
								return;

							}
						}
						break;
					}
					int pronum = this.ui.getInt("请选择" + actid + "商品数量：");
					// 通过商品id查出商品信息，存入list集合（购物车）中
					Product pro = this.sb.selectActiveById(actid, pronum);
					// 遍历集合，如果加入过该商品，则只增加数量
					int proid = pro.getProductId();
					for (Product product : list) {
						if (proid == product.getProductId()) {
							product.setProductNum(product.getProductNum() + pronum);
							continue loop;
						}
					}
					list.add(pro);

				}
			} else if (select == 2) {
				break;
			}

		}
	}

	// 显示活动列表的方法
	public void showActiveMenu(Users u) {
		System.out.println("活动id" + "\t\t" + "活动名称" + "\t\t" + "活动描述" + "\t\t" + "商品名称" + "\t\t" + "商品价格");
		List<Activity> showAct = this.sb.showAct(u);
		for (Activity act : showAct) {
			System.out.println(act.getActivityId() + "\t\t" + act.toString());
		}

	}

	// 查看个人信息
	private void selectUser(Users user) {
		this.v.println("姓名\t手机号\t\t密码\t会员等级\t积分\t地址");
		System.out.println(user);
	}
	
	
}
