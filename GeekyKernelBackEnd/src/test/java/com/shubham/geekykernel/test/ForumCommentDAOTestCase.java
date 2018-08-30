package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.ForumCommentDAO;
import com.shubham.geekykernel.domain.ForumComment;

public class ForumCommentDAOTestCase 
{
	static ForumCommentDAO forumcommentDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		forumcommentDAO = (ForumCommentDAO) context.getBean("forumcommentDAO");
	}
	
	@Test
	public void addForumCommentTestCase()
	{
		ForumComment forumComment = new ForumComment();
		
		forumComment.setForumid(3951);
		forumComment.setLoginname("Scott");
		forumComment.setDiscussiontxt("I am the first person to come back from Quantam Realm.");
		
		assertTrue("Problem in Adding a Forum Comment",forumcommentDAO.save(forumComment));
	}
	
	@Test
	public void listForumCommentTestCase()
	{
		List<ForumComment> listComments = forumcommentDAO.list();
		
		assertTrue("Problem in Listing ForumComments:",listComments.size()>0);
		
		for(ForumComment forumComment:listComments)
		{
			System.out.print(forumComment.getForumid()+" --> ");
			System.out.print(forumComment.getLoginname()+" --> ");
			System.out.println(forumComment.getDiscussiontxt());
		}
	}
}
