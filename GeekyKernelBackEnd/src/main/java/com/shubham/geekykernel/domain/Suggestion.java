package com.shubham.geekykernel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
@Table

public class Suggestion 
{
	@Id
	private String emailid;
	private String name;
	private String message;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date added_date;
	
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmailid() 
	{
		return emailid;
	}
	public void setEmailid(String emailid) 
	{
		this.emailid = emailid;
	}
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}
	public Date getAdded_date() 
	{
		return added_date;
	}
	public void setAdded_date(Date added_date) 
	{
		this.added_date = added_date;
	}
}