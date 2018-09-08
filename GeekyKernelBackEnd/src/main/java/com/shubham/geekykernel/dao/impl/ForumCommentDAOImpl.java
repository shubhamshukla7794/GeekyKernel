package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.ForumCommentDAO;
import com.shubham.geekykernel.domain.ForumComment;


@Repository("forumcommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO{




		@Autowired
		private SessionFactory sessionFactory;
		public boolean save(ForumComment forumcomment) {
			try {
				 
				forumcomment.setDiscussionDate(new Date(System.currentTimeMillis()));
				sessionFactory.getCurrentSession().saveOrUpdate(forumcomment);
			} catch (Exception e) {
				// print the complete exception stack trace
				e.printStackTrace();
				return false;
			}
			
			return true;
		
	}
		

		
		public boolean update(ForumComment forumcomment) {
			try {
				 
				sessionFactory.getCurrentSession().update(forumcomment);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
		}

		
		public boolean delete(ForumComment forumcomment) {
			try
			{
				sessionFactory.getCurrentSession().delete(forumcomment);
				return true;
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised:"+e);
				return false;
			}
			
		}

		
		public ForumComment get(int id) {
			Session session=sessionFactory.openSession();
			ForumComment forumcomment=(ForumComment)session.get(ForumComment.class,id);
			session.close();
			return forumcomment;
			
		}

		
		public List<ForumComment> list() {
			return sessionFactory.getCurrentSession().createQuery("from ForumComment").list();
		}
		
		public List<ForumComment> getAllForumComments(int forumid) 
		{
			try
			{
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from ForumComment where forumid=:myforumid");
				query.setParameter("myforumid",forumid);
				List<ForumComment> listForumComments=query.list();
				return listForumComments;
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised:"+e);
				return null;
			}
		}

	}

