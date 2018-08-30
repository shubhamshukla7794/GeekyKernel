package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.BlogcommentDAO;
import com.shubham.geekykernel.domain.Blogcomment;

public class BlogcommentDAOTestCase 
{
	static BlogcommentDAO blogcommentDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		blogcommentDAO = (BlogcommentDAO) context.getBean("blogcommentDAO");
	}
	
	@Test
	public void addBlogCommentTestCase()
	{
		Blogcomment blogcomment = new Blogcomment();
		
		blogcomment.setBlogid(951);
		blogcomment.setLoginname("Peter");
		blogcomment.setCommenttext("Comment check.");
		
		assertTrue("Problem in Adding a Blog Comment", blogcommentDAO.addComment(blogcomment));
	}
	
	@Test
	public void listBlogCommentTestCase()
	{
		List<Blogcomment> listBlogComments = blogcommentDAO.getAllComments(951);
		
		assertTrue("Problem in Listing BlogComments", listBlogComments.size()>0);
		
		for(Blogcomment blogcomment:listBlogComments)
		{
			System.out.print(blogcomment.getBlogid()+" --> ");
			System.out.print(blogcomment.getLoginname()+" --> ");
			System.out.println(blogcomment.getCommenttext());
		}
	}
}