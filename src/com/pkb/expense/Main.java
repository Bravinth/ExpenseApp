package com.pkb.expense;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pkb.expense.facade.UserFacade;
import com.pkb.expense.vo.UserVO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractApplicationContext bf= new ClassPathXmlApplicationContext("spring.xml");
		bf.registerShutdownHook();
		UserFacade facade = (UserFacade)bf.getBean("userFacade");
		UserVO userVO = facade.getUserInfo("bravinth@gmail.com");
		System.out.println(userVO.getFirstName());
		bf.close();
	}

	public void method(){

	}
}
