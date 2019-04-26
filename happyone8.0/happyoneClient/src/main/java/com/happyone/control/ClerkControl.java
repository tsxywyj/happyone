package com.happyone.control;


import com.happyone.domain.Clerk;
import com.happyone.service.inter.ServiceBiz;
import com.happyone.util.UserInput;
import com.happyone.view.ClerkView;

public class ClerkControl {
	private ClerkView cv;
    private UserInput ui;
	private ServiceBiz sb;
    private Clerk clerk;
    private ManagerControl mc;
    private PutongClerkControl pcc;
    private SendClerkControl scc;
	public ClerkControl(ServiceBiz sb) {
		// TODO Auto-generated constructor stub
		this.cv=new ClerkView();
		this.ui=new UserInput();
		this.sb=sb;
		this.clerk=new Clerk();
		
	}
    public void startClerk(){
    	
    	this.cv.clerkDengluView();
    	long tel=this.ui.getLong("请输入职工手机号");
    	if(this.sb.selectClerkBytel(tel)!=null){
    		String password=this.ui.getString("请输入密码");
    		if((clerk=this.sb.denglu(tel, password))!=null){
    			this.cv.println("欢迎:"+clerk.getClerkName()+",进入"+clerk.getClerkType()+"系统");
    		    
    			if(clerk.getClerkType().equals("普通员工"))
    			{
    				this.sb.updateClerkStatus(clerk.getClerkId(), "工作中");
    				this.pcc=new PutongClerkControl(clerk,sb);
    				this.pcc.putongClerkStart();
    			}
    			if(clerk.getClerkType().equals("配送员"))
    			{
    				this.sb.updateClerkStatus(clerk.getClerkId(), "工作中空闲");
    				this.scc=new SendClerkControl(clerk,sb);
    				this.scc.sendClerkStart();
    			}
    			if(clerk.getClerkType().equals("经理"))
    			{
    				this.sb.updateClerkStatus(clerk.getClerkId(), "工作中");
    				this.mc=new ManagerControl(clerk,sb);
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
