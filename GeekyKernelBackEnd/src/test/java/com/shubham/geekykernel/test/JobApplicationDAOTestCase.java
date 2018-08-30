package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.JobApplicationDAO;
import com.shubham.geekykernel.domain.JobApplication;

public class JobApplicationDAOTestCase 
{
	static JobApplicationDAO jobappDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		jobappDAO = (JobApplicationDAO) context.getBean("jobappDAO");
	}
	
	@Test
	public void applyForJobSuccessTestCase()
	{
		JobApplication jobApplication = new JobApplication();
		jobApplication.setEmailid("scott@gmail.com");
		jobApplication.setJobid(9951);
		jobApplication.setLoginname("Scott");
		jobApplication.setJobtitle("Intern");
		jobApplication.setJobdescription("Intern");
		
		assertTrue("Problem in applying",jobappDAO.saveJobApplication(jobApplication));
	}
}
