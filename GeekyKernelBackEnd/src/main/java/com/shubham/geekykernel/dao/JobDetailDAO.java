package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.JobDetail;

public interface JobDetailDAO 
{
	public boolean saveJob(JobDetail job);			
	public boolean updateJob(JobDetail job);			
	public boolean deleteJob(int jobid);		
	public List<JobDetail> jobList(); 	
	public JobDetail get(int id); 
}
