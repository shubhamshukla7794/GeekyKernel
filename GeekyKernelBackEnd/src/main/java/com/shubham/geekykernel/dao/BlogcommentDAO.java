package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.Blogcomment;



public interface BlogcommentDAO {
	
	public boolean addComment(Blogcomment blogcomment);
	//public boolean deleteComment(Blogcomment blogcomment);
	public List<Blogcomment> getAllComments(int blogId);
	
}
