package com.shubham.geekykernel.dao;

import org.springframework.stereotype.Component;

import com.shubham.geekykernel.domain.ProfileImage;

@Component
public interface ProfilePictureDAO 
{
	public boolean uploadProfile(ProfileImage Profile);
	public ProfileImage getProfile(String loginname);
	
}