package com.shubham.geekykernel.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.ProfilePictureDAO;
import com.shubham.geekykernel.domain.ProfileImage;


@Repository("profilepictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO
{
	@Autowired
	SessionFactory sessionFactory;

	public boolean uploadProfile(ProfileImage Profile) 
	{
		try 
		{
			sessionFactory.getCurrentSession().saveOrUpdate(Profile);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public ProfileImage getProfile(String loginname) 
	{
		return (ProfileImage) sessionFactory.getCurrentSession().get(ProfileImage.class, loginname);
	}
	
	
}