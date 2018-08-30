package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.BlogDAO;
import com.shubham.geekykernel.domain.Blog;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Blog blog) {
		 
			
			try {
				 
				blog.setCreatedate(new Date(System.currentTimeMillis()));
				sessionFactory.getCurrentSession().save(blog);
			} catch (Exception e) {
				// print the complete exception stack trace
				e.printStackTrace();
				return false;
			}
			
			return true;
		
	}

	public boolean update(Blog blog) {
		try {
			 
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		 
	}

	public Blog get(int id) {
		Session session=sessionFactory.openSession();
		Blog blog=(Blog)session.get(Blog.class,id);
		session.close();
		return blog;
	}

	public List<Blog> list() {
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}

	public boolean delete(Blog blog) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
		
	}
	public List<Blog> approvedBlogsList() {
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='A'").list();
	}

	public boolean approveBlog(int blogid) {
		try {
			Blog blog = this.get(blogid);
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean rejectBlog(int blogid) {
		try {
			Blog blog = get(blogid);
			blog.setStatus("R");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean incLikes(int blogid) {
		try {
			Blog blog = get(blogid);
			blog.setLikes(blog.getLikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean incDisLikes(int blogid) {
		try {
			Blog blog = get(blogid);
			blog.setDislikes(blog.getDislikes() + 1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


}
