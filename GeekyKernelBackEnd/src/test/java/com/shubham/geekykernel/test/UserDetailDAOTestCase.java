package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.UserDAO;
import com.shubham.geekykernel.domain.UserDetail;

public class UserDetailDAOTestCase 
{
	static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}	
	
	@Test
	public void registerUserTestCase()
	{
		UserDetail user = new UserDetail();
		
		user.setLoginname("Scott");
		user.setPassword("scott@123");
		user.setUsername("Scott Lang");
		user.setEmailId("scott@gmail.com");
		user.setMobile("9087654321");
		user.setRoles("ROLE_USER");
		user.setAddress("San Francisco");
		
		assertTrue("Problem Registering User", userDAO.registerUser(user));
	}
	
	@Test
	public void updateUserTestCase()
	{
		UserDetail user = userDAO.getUser("Peter");
		user.setMobile("9988776655");
		
		assertTrue("Problem Updating User", userDAO.updateUser(user));
	}
	
	@Test
	public void validateTestCase()
	{
		UserDetail user = new UserDetail();
		user.setLoginname("Tony");
		user.setPassword("tony@123");
		
		UserDetail userDetail = userDAO.checkUser(user);
		
		assertNotNull("Problem Validating User", userDetail);
		
		System.out.println(" Username :  "+userDetail.getUsername());
	}
}
