package com.shubham.geekykernel.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.JobApplicationDAO;
import com.shubham.geekykernel.domain.JobApplication;

@Repository("jobappDAO")
@Transactional
public class JobApplicationDAOImpl implements JobApplicationDAO 
{
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private JobApplicationDAO jobappDAO;

	public boolean saveJobApplication(JobApplication jobApplication) 
	{
		try 
		{
			jobApplication.setJobappstatus('N');
			jobApplication.setApplied_date(new Date());
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;

		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplication> jobApplications() 
	{
		return sessionFactory.getCurrentSession().createQuery("from JobApplication").list();
	}

	public List<JobApplication> jobApplicationlist(int jobid) 
	{
		return sessionFactory.getCurrentSession().createCriteria(JobApplication.class)
				.add(Restrictions.eq("jobid", jobid)).list();
	}

	public boolean isJobAlreadyApplied(String loginname, int jobid) 
	{
		/*JobApplication jobApplication = (JobApplication) sessionFactory.getCurrentSession()
				.createCriteria(JobApplication.class).add(Restrictions.eq("loginname", loginname))
				.add(Restrictions.eq("jobappid", jobid)).uniqueResult();*/
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from JobApplication where loginname =:myloginname and jobid =:jobid");
		query.setParameter("myloginname", loginname);
		query.setParameter("jobid", jobid);
		List<JobApplication> jobAppsList = query.list();
		//JobApplication jobApplication = jobAppsList.get(0);
		session.close();
		
		if (jobAppsList.isEmpty())//jobApplication == null) 
		{
			return false;
		}
		return true;
	}

	public List<JobApplication> jobApplicationList(String loginname) 
	{
		return sessionFactory.getCurrentSession().createCriteria(JobApplication.class)
				.add(Restrictions.eq("loginname", loginname)).list();
	}

	public boolean approveApplication(int jobappid) 
	{
		JobApplication jobApplication = jobappDAO.getApplication(jobappid);
		try 
		{
			jobApplication.setJobappstatus('A');
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean rejectApplication(int jobappid) 
	{
		JobApplication jobApplication = jobappDAO.getApplication(jobappid);
		try 
		{
			jobApplication.setJobappstatus('R');
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public JobApplication getApplication(int jobappid) 
	{
		return sessionFactory.getCurrentSession().get(JobApplication.class, jobappid);
	}

	public boolean deletejobapp(int jobappid) 
	{
		try 
		{
			sessionFactory.getCurrentSession().delete(getApplication(jobappid));
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}