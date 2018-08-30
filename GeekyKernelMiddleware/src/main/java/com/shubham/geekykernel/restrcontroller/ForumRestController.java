package com.shubham.geekykernel.restrcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.dao.ForumDAO;
import com.shubham.geekykernel.domain.Forum;


@RestController
public class ForumRestController 
{
	@Autowired
	private ForumDAO forumDAO;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/forum/list")
	public ResponseEntity<List<Forum>> getForumList()
	{
		List<Forum> forumList = forumDAO.forumList();
		if(!forumList.isEmpty())
		{
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/forum/approvedList")
	public ResponseEntity<List<Forum>> getApprovedForumList()
	{
		List<Forum> forumList = forumDAO.approvedForumsList();
		if(!forumList.isEmpty())
		{
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/forum/add")
	public ResponseEntity<Forum> saveForum(@RequestBody Forum forum)
	{
		String loginname = (String) session.getAttribute("loginname");
		forum.setLoginname(loginname);
		if(forumDAO.saveForum(forum))
		{
			
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/forum/delete/{forumid}")
	public ResponseEntity<Forum> deleteJob(@PathVariable int forumid)
	{
		Forum forum = forumDAO.getForum(forumid);
		if(forum == null)
		{
			forum = new Forum();
			
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		
		if(forumDAO.deleteforum(forum))
		{
			
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/forum/get/{forumid}")
	public ResponseEntity<Forum> getForum(@PathVariable int forumid)
	{
		Forum forum = forumDAO.getForum(forumid);
		if(forum == null)
		{
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	}
	
	@PutMapping("/forum/approve/{forumid}")
	public ResponseEntity<Forum> approveForum(@PathVariable int forumid)
	{
		if(forumDAO.approveForum(forumid))
		{
			Forum forum = forumDAO.getForum(forumid);
			
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			Forum forum = forumDAO.getForum(forumid);
			
			return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/forum/reject/{forumid}")
	public ResponseEntity<Forum> rejectForum(@PathVariable int forumid)
	{
		if(forumDAO.rejectForum(forumid))
		{
			Forum forum = forumDAO.getForum(forumid);
			
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else
		{
			Forum forum = forumDAO.getForum(forumid);
			
			return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}