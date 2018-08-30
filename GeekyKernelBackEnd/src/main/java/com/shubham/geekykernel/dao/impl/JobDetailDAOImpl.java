package com.shubham.geekykernel.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.JobDetailDAO;
import com.shubham.geekykernel.domain.JobDetail;



@Repository("jobdetailDAO")
@Transactional
public class JobDetailDAOImpl implements JobDetailDAO
{
	@Autowired
	SessionFactory sessionFactory;	
	
	public boolean saveJob(JobDetail job) 
	{
		try 
		{
			job.setLastDate(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().save(job);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(JobDetail job) 
	{
		try 
		{
			sessionFactory.getCurrentSession().update(job);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public JobDetail get(int id) 
	{
		Session session=sessionFactory.openSession();
		JobDetail job=(JobDetail)session.get(JobDetail.class,id);
		session.close();
		return job;
	}
	
	public List<JobDetail> jobList() 
	{
		return sessionFactory.getCurrentSession().createQuery("from JobDetail").list();
	}
	
	public boolean isJobOpened(int jobid) 
	{
		JobDetail job = (JobDetail) sessionFactory.getCurrentSession().createCriteria(JobDetail.class).add(Restrictions.eq("jobid", jobid)).uniqueResult();
		
		if (job != null) 
		{
			return true;
		}
		return false;
	}

	public boolean deleteJob(int jobid) 
	{
		try 
		{
			sessionFactory.getCurrentSession().delete(get(jobid));
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}