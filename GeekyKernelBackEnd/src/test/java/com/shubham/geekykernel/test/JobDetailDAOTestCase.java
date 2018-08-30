package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.JobDetailDAO;
import com.shubham.geekykernel.domain.JobDetail;

public class JobDetailDAOTestCase 
{
	static JobDetailDAO jobdetailDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		jobdetailDAO = (JobDetailDAO) context.getBean("jobdetailDAO");
	}
	
	@Test
	public void addJobTestCase()
	{
		JobDetail jobDetail = new JobDetail();
		
		jobDetail.setCompany("Stark Industries");
		jobDetail.setDesignation("Soldier");
		jobDetail.setLocation("Leipzig.Halle Airport");
		jobDetail.setRoleandResp("Left Forward");
		jobDetail.setSkills("Engage Captain America in the battle");
		jobDetail.setCtc(3500000);
		
		assertTrue("Problem in adding job", jobdetailDAO.saveJob(jobDetail));
	}
	
	@Test
	public void updateJobTestCase()
	{
		JobDetail jobDetail = jobdetailDAO.get(9951);
		jobDetail.setCompany("The Avengers");
		assertTrue("Problem in updating job", jobdetailDAO.updateJob(jobDetail));
	}
}
