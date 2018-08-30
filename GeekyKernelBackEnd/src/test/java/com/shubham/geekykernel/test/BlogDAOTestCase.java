package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.BlogDAO;
import com.shubham.geekykernel.domain.Blog;

public class BlogDAOTestCase 
{
	static BlogDAO blogDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
	}
	
	@Test
	public void addBlogTestCase()
	{
		Blog blog = new Blog();
		
		blog.setBlogname("Check");
		blog.setBlogcontent("This is just for checking");
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setLoginname("Tony");
		blog.setStatus("NA");
		
		assertTrue("Problem adding blog", blogDAO.save(blog));
	}
	
	@Test
	public void deleteBlogTestCase()
	{
		Blog blog = blogDAO.get(952);
		assertTrue("Problem Deleting blog", blogDAO.delete(blog));
	}
	
	@Test
	public void updateBlogTestCase()
	{
		Blog blog = blogDAO.get(953);
		
		blog.setDislikes(1);
		blog.setLikes(2);
		
		assertTrue("Problem in Blog Updation", blogDAO.update(blog));
	}
	
	@Test
	public void listBlogTestCase()
	{
		List<Blog> listBlogs = blogDAO.list();
		assertTrue("Problem in listing blogs", listBlogs.size()>0);

		for(Blog blog:listBlogs)
		{
			System.out.print(blog.getBlogId()+" | ");
			System.out.print(blog.getBlogname()+" | ");
			System.out.println(blog.getBlogcontent());
		}
	}
	
	@Test
	public void listApprovedBlogTestCase()
	{
		List<Blog> listApprovedBlogs = blogDAO.approvedBlogsList();
		assertTrue("Problem in listing approved blogs", listApprovedBlogs.size()>0);

		for(Blog blog:listApprovedBlogs)
		{
			System.out.print(blog.getBlogId()+" | ");
			System.out.print(blog.getBlogname()+" | ");
			System.out.println(blog.getBlogcontent());
		}
	}
	
	@Test
	public void approveBlogTestCase()
	{
		assertTrue("Problem in Approving Blog", blogDAO.approveBlog(951));
	}
	
	@Test
	public void rejectBlogTestCase()
	{
		assertTrue("Problem in Rejecting Blog", blogDAO.rejectBlog(953));
	}
	
	@Test
	public void likesBlogTestCase()
	{
		assertTrue("Problem in incrementing likes in Blog", blogDAO.incLikes(951));
	}
	
	@Test
	public void disLikesBlogTestCase()
	{
		assertTrue("Problem in incrementing dislikes in Blog", blogDAO.incDisLikes(951));
	}
}