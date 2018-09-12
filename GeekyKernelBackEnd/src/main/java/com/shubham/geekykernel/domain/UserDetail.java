package com.shubham.geekykernel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class UserDetail {
	
	@Id
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$")
	private String loginname;
	@NotEmpty(message = "Please enter your password.")
    @Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
	private String password;
	@Pattern(regexp = "^[a-zA-Z ]{3,50}$")
	private String Username;
	@NotEmpty @Email
	private String emailId;
	@Pattern(regexp = "^(\\+\\d{1,2}[- ]?)?\\d{10}$")
	private String mobile;
	private String roles;
	@Pattern(regexp = "^[a-zA-Z0-9-,() ]{3,250}$")
	private String address;
	
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
}
