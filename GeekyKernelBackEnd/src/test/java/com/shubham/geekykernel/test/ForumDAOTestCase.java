package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.ForumDAO;
import com.shubham.geekykernel.domain.Forum;

public class ForumDAOTestCase 
{
	static ForumDAO forumDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
		
		forumDAO = (ForumDAO) context.getBean("forumDAO");
	}
	
	@Test
	public void addForumTestCase()
	{
		Forum forum = new Forum();
		forum.setForumName("Web-Shooters");
		forum.setForumContent("The Web-Shooters are a pair of wrist-mounted mechanical devices "
				+ "developed and used by Spider-Man to project Synthetic Webbing.\r\n" 
				+ "The Web-Shooters and Web Formula were designed and built by Peter Parker himself "
				+ "using homemade materials and chemicals found in typical high school level chemistry "
				+ "classes. Shortly after he gained his powers. Parker then started using them to fight "
				+ "crime in New York City.\r\n" 
				+ "The web-shooters are capable of shooting thin strands of extremely durable and "
				+ "elastic web-like substance. The high tensile strength of the synthetic webbing is "
				+ "sufficient to support the weight of large vehicles such as buses and trucks. "
				+ "Spider-Man is able to use the web-shooters offensively by \"webbing\" his opponents, "
				+ "and can also use them for transport by swinging on web-lines.");
		forum.setLoginname("Peter");
		forum.setStatus("NA");
		
		assertTrue("Problem in Adding Forum", forumDAO.saveForum(forum));
	}
	
	@Test
	public void deleteForumTestCase()
	{
		Forum forum = forumDAO.getForum(3952);
		assertTrue("Problem in Forum Deletion", forumDAO.deleteforum(forum));
	}
	
	@Test
	public void updateForumTestCase()
	{
		Forum forum = forumDAO.getForum(3951);
		forum.setForumName("What we know about Quantum Realm?");
		assertTrue("Problem in Forum Updation", forumDAO.updateforum(forum));
	}
	
	@Test
	public void listForumTestCase()
	{
		List<Forum> listForums = forumDAO.forumList();
		assertTrue("Problem in Listing Forums", listForums.size()>0);
		
		for(Forum forum:listForums)
		{
			System.out.print(forum.getForumId()+" | ");
			System.out.print(forum.getForumName()+" | ");
			System.out.println(forum.getForumContent());
		}
	}
	
	@Test
	public void listApprovedForumTestCase()
	{
		List<Forum> listApprovedForums = forumDAO.approvedForumsList();
		assertTrue("Problem in Listing Approved Forums", listApprovedForums.size()>0);
		
		for(Forum forum:listApprovedForums)
		{
			System.out.print(forum.getForumId()+" | ");
			System.out.print(forum.getForumName()+" | ");
			System.out.println(forum.getForumContent());
		}
	}
	
	@Test
	public void approveForumTestCase()
	{
		assertTrue("Problem in approving Forum", forumDAO.approveForum(3953));
	}
	
	@Test
	public void rejectForumTestCase()
	{
		assertTrue("Problem in approving Forum", forumDAO.rejectForum(3953));
	}
}



















