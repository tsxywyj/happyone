package com.happyone.control;

import com.happyone.biz.impl.ManagerClerkBizImpl;
import com.happyone.biz.inter.ManagerClerkBiz;
import com.happyone.domain.Clerk;
import com.happyone.util.UserInput;
import com.happyone.view.ClerkView;

public class ClerkControl {
	private ClerkView cv;
    private UserInput ui;
    private ManagerClerkBiz mcb;
    private Clerk clerk;
    private ManagerControl mc;
    private PutongClerkControl pcc;
    private SendClerkControl scc;
	public ClerkControl() {
		// TODO Auto-generated constructor stub
		this.cv=new ClerkView();
		this.ui=new UserInput();
		this.mcb=new ManagerClerkBizImpl();
		this.clerk=new Clerk();
		
	}
    public void startClerk(){
    	
    	this.cv.clerkDengluView();
    	long tel=this.ui.getLong("请输入职工手机号");
    	if(this.mcb.selectClerkBytel(tel)!=null){
    		String password=this.ui.getString("请输入密码");
    		if((clerk=this.mcb.denglu(tel, password))!=null){
    			this.cv.println("欢迎:"+clerk.getClerkName()+",进入"+clerk.getClerkType()+"系统");
    		    
    			if(clerk.getClerkType().equals("普通员工"))
    			{
    				this.mcb.updateClerkStatus(clerk.getClerkId(), "工作中");
    				this.pcc=new PutongClerkControl(clerk);
    				this.pcc.putongClerkStart();
    			}
    			if(clerk.getClerkType().equals("配送员"))
    			{
    				this.mcb.updateClerkStatus(clerk.getClerkId(), "工作中空闲");
    				this.scc=new SendClerkControl(clerk);
    				this.scc.sendClerkStart();
    			}
    			if(clerk.getClerkType().equals("经理"))
    			{
    				this.mcb.updateClerkStatus(clerk.getClerkId(), "工作中");
    				this.mc=new ManagerControl(clerk);
    				this.mc.managerStart();
    			}
       		}else{
    			
    		this.cv.println("密码不正确");
    		}
    		
    	}else{
    		this.cv.println("该职工不存在");
    		
    	}
    }
}
