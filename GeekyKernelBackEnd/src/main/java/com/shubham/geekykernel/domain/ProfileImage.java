package com.shubham.geekykernel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class ProfileImage 
{
	@Id
	private String loginname;
	private byte[] picture;
	
	
	public byte[] getpicture() {
		return picture;
	}
	public void setpicture(byte[] picture) {
		this.picture = picture;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	
}