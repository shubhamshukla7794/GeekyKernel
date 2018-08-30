package com.shubham.geekykernel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shubham.geekykernel.dao.FriendDAO;
import com.shubham.geekykernel.domain.Friend;
import com.shubham.geekykernel.domain.UserDetail;

public class FriendDAOTestCase 
{
	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.shubham");
		context.refresh();
	
		friendDAO = (FriendDAO) context.getBean("friendDAO");
	}
	
	@Test
	public void sendFriendRequestTestCase()
	{
		Friend friend = new Friend();
		friend.setLoginname("Scott");
		friend.setFriendname("Tony");
		
		assertTrue("Problem in sending a friend request", friendDAO.sendFriendRequest(friend));
	}
	
	@Test
	public void acceptFriendRequestTestCase()
	{
		assertTrue("Problem in accepting a friend request", friendDAO.accepctFriendrequest(7954));
	}
	
	@Test
	public void deleteFriendRequestTestCase()
	{
		assertTrue("Problem in deleting a friend request", friendDAO.deleteFriendRequest(7952));
	}
	
	@Test
	public void showFriendListTestCase()
	{
		List<Friend> friendList = friendDAO.showFriendList("Tony");		
		assertTrue("Problem in listing  friend list", friendList.size()>0);
		
		System.out.println("\n---------------- Friend List ----------------");
		for(Friend friend:friendList)
		{
			System.out.print("\t"+friend.getLoginname()+"\t--> ");
			System.out.println("\t"+friend.getFriendname());
		}
	}
	
	@Test
	public void sendPendingFriendRequestTestCase()
	{
		List<Friend> listPendingFriendRequest = friendDAO.showPendingFriendRequest("Scott");		
		assertTrue("Problem in listing pending friend request", listPendingFriendRequest.size()>0);
		
		System.out.println("\n---------------- Pending Friend List ----------------");
		for(Friend friend:listPendingFriendRequest)
		{
			System.out.print("\t"+friend.getLoginname()+"\t--> ");
			System.out.println("\t"+friend.getFriendname());
		}
	}
	
	@Test
	public void showSuggestedFriends()
	{
		List<UserDetail> listUserDetail = (List<UserDetail>) friendDAO.showSuggestedFriend("Shubham");
		assertTrue("Problem in suggesting friends", listUserDetail.size()>0);
		
		System.out.println("\n---------------- Suggested Friend List ----------------");
		for(UserDetail userDetail:listUserDetail)
		{
			System.out.println("\t"+userDetail.getLoginname()+"\t--> "+"\t"+userDetail.getUsername());
		}
	}
}