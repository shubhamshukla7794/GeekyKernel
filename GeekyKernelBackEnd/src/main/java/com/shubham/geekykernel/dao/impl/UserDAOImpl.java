package com.shubham.geekykernel.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.UserDAO;
import com.shubham.geekykernel.domain.UserDetail;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean registerUser(UserDetail userdetail) {
		
		
		try {
			 
			
			sessionFactory.getCurrentSession().saveOrUpdate(userdetail);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean updateUser(UserDetail userdetail) {
		
		try {
			 	sessionFactory.getCurrentSession().update(userdetail);
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public UserDetail getUser(String loginname) {
		
		Session session=sessionFactory.openSession();
		UserDetail userdetail=(UserDetail)session.get(UserDetail.class,loginname);
		session.close();
		return userdetail;
	}

	@SuppressWarnings("deprecation")
	public UserDetail checkUser(UserDetail userdetail) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserDetail where loginname=:myloginname and password=:password");
		
		
	
		query.setParameter("myloginname",userdetail.getLoginname() );
		query.setParameter("password",userdetail.getPassword());
		List<UserDetail> listuser=query.list();
		UserDetail userdetail1=listuser.get(0);
		session.close();
		return userdetail1;
		
	}

}
