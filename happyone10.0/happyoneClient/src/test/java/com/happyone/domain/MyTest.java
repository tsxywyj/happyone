package com.happyone.domain;

import org.junit.Test;

import com.happyone.util.UserInput;

public class MyTest {

	public MyTest() {
	
		// TODO Auto-generated constructor stub
	}
	@Test
    public void testone(){
		UserInput ui=new UserInput();
		System.out.println(ui.getLong("q"));
    	
    }
}
