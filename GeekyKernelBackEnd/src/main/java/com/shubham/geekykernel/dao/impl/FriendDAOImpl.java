package com.shubham.geekykernel.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.geekykernel.dao.FriendDAO;
import com.shubham.geekykernel.domain.Friend;
import com.shubham.geekykernel.domain.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO 
{

	@Autowired
	private SessionFactory sessionFactory;

	public List<Friend> showFriendList(String loginname) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Friend where (loginname='" + loginname
				+ "' AND status='Accepted')OR (friendname='" + loginname + "' AND status='Accepted')").list();
	}

	public List<Friend> showPendingFriendRequest(String loginname) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Friend where (loginname='" + loginname
				+ "' AND status='Pending') OR (friendname='" + loginname + "' AND status='Pending')").list();
	}

	public boolean sendFriendRequest(Friend friend) 
	{
		try 
		{
			friend.setStatus("Pending");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean accepctFriendrequest(int friendId) 
	{
		try 
		{
			Friend friend = sessionFactory.getCurrentSession().get(Friend.class, friendId);
			friend.setStatus("Accepted");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteFriendRequest(int friendId) 
	{
		try 
		{
			Friend friend = sessionFactory.getCurrentSession().get(Friend.class, friendId);
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> showSuggestedFriend(String loginname) 
	{
		List<String> users = sessionFactory.getCurrentSession()
				.createSQLQuery("select loginname from userdetail where loginname not in(select "
						+ "friendname from friend where loginname = '" + loginname
						+ "' and status = 'Accepted' UNION ALL select loginname from friend " + "where friendname = '"
						+ loginname
						+ "' and status = 'Accepted') AND loginname not in(select friendname from friend where "
						+ "loginname = '" + loginname
						+ "' and status = 'Pending' UNION ALL select loginname from friend where friendname = '"
						+ loginname + "'" + " and status = 'Pending') and loginname!='" + loginname + "'")
				.list();
		List<UserDetail> suggestedPeople = new ArrayList<UserDetail>();
		int i = 0;
		while (i < users.size()) 
		{
			UserDetail user = sessionFactory.getCurrentSession().get(UserDetail.class, users.get(i));
			suggestedPeople.add(user);
			i++;
		}
		return suggestedPeople;
	}

	public Friend getFriend(int friendid) 
	{
		return sessionFactory.getCurrentSession().get(Friend.class, friendid);
	}

}
