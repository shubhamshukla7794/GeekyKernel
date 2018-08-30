package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.Forum;

public interface ForumDAO {

	public boolean saveForum(Forum forum);
	public boolean deleteforum(Forum forum);
	public boolean updateforum(Forum forum);
	public Forum getForum(int forumid);
	public boolean approveForum(int forumid);
	public boolean rejectForum(int forumid);
	public List<Forum> approvedForumsList();
	public List<Forum> forumList();
	
}
