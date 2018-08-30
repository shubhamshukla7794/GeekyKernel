package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.BlogcommentDAO;
import com.shubham.geekykernel.domain.Blogcomment;


@Repository("blogcommentDAO")
@Transactional
public class BlogcommentDAOImpl implements BlogcommentDAO {

	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addComment(Blogcomment blogComment) 
	{
		try
		{ 
			blogComment.setCommentdate(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	
	/*public boolean deleteComment(Blogcomment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}*/

	
	public List<Blogcomment> getAllComments(int blogId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Blogcomment where blogId=:myblogid");
			query.setParameter("myblogid",blogId);
			List<Blogcomment> listBlogComments=query.list();
			return listBlogComments;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return null;
		}
	}
	
}
