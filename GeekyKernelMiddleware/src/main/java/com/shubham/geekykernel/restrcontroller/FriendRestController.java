package com.shubham.geekykernel.restrcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.dao.FriendDAO;
import com.shubham.geekykernel.dao.UserDAO;
import com.shubham.geekykernel.domain.Friend;
import com.shubham.geekykernel.domain.UserDetail;



@RestController
public class FriendRestController 
{
	@Autowired
	HttpSession session;
	
	@Autowired
	private FriendDAO friendDAO;
	
	@Autowired
	private Friend friend;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@RequestMapping("friend/list")
	public ResponseEntity<List<Friend>> showFriendList()
	{
		String loginname = (String) session.getAttribute("loginname");
		List<Friend> friends = friendDAO.showFriendList(loginname);
		
		if(friends.isEmpty())
		{
			Friend friend = new Friend();
			friend.setStatus("No Friends Yet !!!");
			friends.add(friend);
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
		}
	}
	
	@RequestMapping("friend/pendingRequest")
	public ResponseEntity<List<Friend>> PendingFriendRequest()
	{
		String loginname = (String) session.getAttribute("loginname");
		List<Friend> friends = friendDAO.showPendingFriendRequest(loginname);
		
		if(friends.isEmpty())
		{
			Friend friend = new Friend();
			friend.setStatus("No Pending Friend Requests Yet !!!");
			friends.add(friend);
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
		}
	}
	
	@RequestMapping("friend/suggested")
	public ResponseEntity<List<UserDetail>> suggestedPeople()
	{		
		String loginname = (String) session.getAttribute("loginname");
		List<UserDetail> suggestedPeople = friendDAO.showSuggestedFriend(loginname);
	
		if(suggestedPeople.isEmpty())
		{
			return new ResponseEntity<List<UserDetail>>(suggestedPeople, HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(suggestedPeople, HttpStatus.OK);
		}
	}
	
	@RequestMapping("friend/sendRequest/{username}")
	public ResponseEntity<Friend> sendRequest(@PathVariable String username)
	{
		String loginname = (String) session.getAttribute("loginname");
		UserDetail u = userDAO.getUser(username);
				
		friend.setLoginname(loginname);
		friend.setFriendname(u.getLoginname());
		
		if(friendDAO.sendFriendRequest(friend))
		{
			friend.setStatus("Request Sent");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			friend.setStatus("Request not Sent");
			return new ResponseEntity<Friend>(friend, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("friend/acceptRequest/{friendid}")
	public ResponseEntity<Friend> acceptRequest(@PathVariable int friendid)
	{
		Friend friend = new Friend();
		if(friendDAO.accepctFriendrequest(friendid))
		{
			friend = friendDAO.getFriend(friendid);
			friend.setStatus("Request Accepted");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			friend = friendDAO.getFriend(friendid);
			friend.setStatus("internal server error");
			return new ResponseEntity<Friend>(friend, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("friend/deleteRequest/{friendid}")
	public ResponseEntity<Friend> rejectRequest(@PathVariable int friendid)
	{
		Friend friend = new Friend();
		if(friendDAO.deleteFriendRequest(friendid))
		{
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Friend>(friend, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("friend/delete/{friendid}")
	public ResponseEntity<Friend> DeleteFriend(@PathVariable int friendid)
	{
		Friend friend = new Friend();
		if(friendDAO.deleteFriendRequest(friendid))
		{
			friend = friendDAO.getFriend(friendid);
			friend.setStatus("Friend Deleted");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			friend = friendDAO.getFriend(friendid);
			friend.setStatus("internal server error");
			return new ResponseEntity<Friend>(friend, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
	
}