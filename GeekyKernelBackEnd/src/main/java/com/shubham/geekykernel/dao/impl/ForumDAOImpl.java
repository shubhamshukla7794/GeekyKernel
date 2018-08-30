package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.ForumDAO;
import com.shubham.geekykernel.domain.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	
	
	public boolean saveForum(Forum forum) {
		try {
			
			forum.setStatus("NA");
			forum.setCreatedDate(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteforum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateforum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public Forum getForum(int forumid) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumid);
	}

	public boolean approveForum(int forumid) {
		try {
			Forum forum = getForum(forumid);
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public boolean rejectForum(int forumid) {
		try {
			Forum forum = getForum(forumid);
			forum.setStatus("R");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public List<Forum> approvedForumsList() {
		return sessionFactory.getCurrentSession().createQuery("from Forum where status = 'A'").list();
		
	}

	public List<Forum> forumList() {
		return sessionFactory.getCurrentSession().createQuery("from Forum").list();
	}

	
	
}
