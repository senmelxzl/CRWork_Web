package com.crwork.web.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crwork.web.model.UserModel;

public class CRWork_spring_test {
	public static void main(String[] args) {
		ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserModel mUserModel = (UserModel) ct.getBean("UserModel");
		System.out.println("name:" + mUserModel.getUserName());
	}
}
