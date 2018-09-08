package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.ForumComment;

public interface ForumCommentDAO {

	
	public boolean save(ForumComment forumcomment);
	public boolean update(ForumComment forumcomment);
	public boolean delete(ForumComment forumcomment);
	public ForumComment get(int id);
	public List<ForumComment> list();
	public List<ForumComment> getAllForumComments(int forumid);

}
