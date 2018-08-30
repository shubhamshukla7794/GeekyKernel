package com.shubham.geekykernel.restrcontroller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.dao.JobApplicationDAO;
import com.shubham.geekykernel.dao.JobDetailDAO;
import com.shubham.geekykernel.dao.UserDAO;
import com.shubham.geekykernel.domain.JobApplication;
import com.shubham.geekykernel.domain.JobDetail;
import com.shubham.geekykernel.domain.UserDetail;


@RestController()
public class JobRestController 
{
	@Autowired
	private JobDetail jobdetail;
	
	@Autowired
	private JobDetailDAO jobdetailDAO;
	
	@Autowired
	private JobApplicationDAO jobappDAO;
	
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	HttpSession session;
	
	
	
	@RequestMapping("/jobdetail/list")
	public ResponseEntity<List<JobDetail>> jobList()
	{		
		List<JobDetail> jobList = jobdetailDAO.jobList(); 
		if(jobList.isEmpty())		
		{
			return new ResponseEntity<List<JobDetail>>(jobList, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<JobDetail>>(jobList, HttpStatus.OK);
	}
	
	@RequestMapping("/jobdetail/get/{jobid}")
	public ResponseEntity<JobDetail> getJob(@PathVariable int jobid)
	{
		JobDetail jobdetail = jobdetailDAO.get(jobid);
		if(jobdetail == null)				
		{
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.NOT_FOUND);
		}
		else
		{
			session.setAttribute("jobidforapply", jobid);
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.OK);
		}
	}
	
	@PostMapping("jobdetail/post")
	public ResponseEntity<JobDetail> saveJob(@RequestBody JobDetail jobdetail)
	{		
		if(jobdetailDAO.saveJob(jobdetail))
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/jobdetail/delete/{jobid}")
	public ResponseEntity<JobDetail> deleteJob(@PathVariable int jobid)
	{
		JobDetail j = jobdetailDAO.get(jobid);
		if(j == null)				
		{
			jobdetail = new JobDetail();
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.CONFLICT);
		}
		
		List<JobApplication> appliedJobs = jobappDAO.jobApplicationlist(jobid);
		if(!appliedJobs.isEmpty())		
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.CONFLICT);
		}
		
		if(jobdetailDAO.deleteJob(jobid))
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//@PutMapping("/jobdetail/update/{jobid}")
	//public ResponseEntity<JobDetail> updateJob(@PathVariable int jobid)
	{
		/*jobdetail = jobdetailDAO.getJob(jobid);
			
		
		if(jobdetailDAO.updateJob(jobdetail))
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<JobDetail>(jobdetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}		*/		
	}
	
	

	
	@PostMapping("/jobdetail/registration")
	public ResponseEntity<JobApplication> jobRegistration(@RequestBody JobApplication jobApplication)
	{
		String loginname = (String) session.getAttribute("loginname");
		UserDetail user = userDAO.getUser(loginname);
		int jobid = (Integer)session.getAttribute("jobidforapply");
		JobDetail jobdetail = jobdetailDAO.get(jobid);
		
		jobApplication.setLoginname(loginname);
		jobApplication.setEmailid(user.getEmailId());
		jobApplication.setJobdescription(jobdetail.getDesignation());
		jobApplication.setJobid(jobid);
		jobApplication.setJobtitle(jobdetail.getDesignation());
		
		if(!jobappDAO.isJobAlreadyApplied(loginname, jobid))
		{
			if(jobappDAO.saveJobApplication(jobApplication))
			{
				
				return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
			}
			else
			{
				
				return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		else
		{
			JobApplication j = new JobApplication();
			
			return new ResponseEntity<JobApplication>(j, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/jobdetail/approveApplication/{jobappid}")
	public ResponseEntity<JobApplication> approveApplication(@PathVariable int jobappid)
	{
		if(jobappDAO.approveApplication(jobappid))
		{
			JobApplication jobApplication = new JobApplication();
			
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.ACCEPTED);
		}
		else
		{
			JobApplication jobApplication = new JobApplication();
			
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/jobdetail/rejectApplication/{jobappid}")
	public ResponseEntity<JobApplication> rejectApplication(@PathVariable int jobappid)
	{
		JobApplication jobApplication = new JobApplication();
		if(jobappDAO.rejectApplication(jobappid))
		{
			jobApplication = jobappDAO.getApplication(jobappid);
			
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.ACCEPTED);
		}
		else
		{
			jobApplication = jobappDAO.getApplication(jobappid);
			
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("jobdetail/appliedJobs")
	public ResponseEntity<List<JobApplication>> appliedJobList()
	{
		List<JobApplication> jobapplist = jobappDAO.jobApplications();
		
		if(!jobapplist.isEmpty())
		{
			return new ResponseEntity<List<JobApplication>>(jobapplist, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<JobApplication>>(jobapplist, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/application/delete/{jobappid}")
	public ResponseEntity<?> deleteapp(@PathVariable int jobappid)
	{
		if(jobappDAO.deletejobapp(jobappid))
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}