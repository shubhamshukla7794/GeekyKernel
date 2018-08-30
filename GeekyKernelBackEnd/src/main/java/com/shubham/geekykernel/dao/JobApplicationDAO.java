package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.JobApplication;

public interface JobApplicationDAO 
{
	public boolean saveJobApplication(JobApplication jobApplication);
	public List<JobApplication> jobApplicationlist(int jobid);
	public List<JobApplication> jobApplications();
	public  boolean isJobAlreadyApplied(String loginname, int jobid);
	public List<JobApplication> jobApplicationList(String loginname);
	public boolean approveApplication(int jobappid);
	public boolean rejectApplication(int jobappid);
	public JobApplication getApplication(int jobappid);
	public boolean deletejobapp(int jobappid);
}
