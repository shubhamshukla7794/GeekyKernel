package com.shubham.geekykernel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
@Table
@SequenceGenerator(name="jppsidseq",sequenceName="jppsidseq")
public class JobApplication 
{	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jppsidseq")
	private int jobappid;
	private String emailid;
	private String loginname;
	private int jobid;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date applied_date;
	private String jobtitle;
	private String jobdescription;
	private char jobappstatus;
	
	public int getJobappid() {
		return jobappid;
	}
	public void setJobappid(int jobappid) {
		this.jobappid = jobappid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public Date getApplied_date() {
		return applied_date;
	}
	public void setApplied_date(Date applied_date) {
		this.applied_date = applied_date;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	public char getJobappstatus() {
		return jobappstatus;
	}
	public void setJobappstatus(char jobappstatus) {
		this.jobappstatus = jobappstatus;
	}	
}