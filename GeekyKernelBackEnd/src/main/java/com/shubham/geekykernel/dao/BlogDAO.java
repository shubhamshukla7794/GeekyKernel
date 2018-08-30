package com.shubham.geekykernel.dao;

import java.util.List;

import com.shubham.geekykernel.domain.Blog;

public interface BlogDAO {
	
	
	public boolean save(Blog blog);
	public boolean update(Blog blog);
	public boolean delete(Blog blog);
	public Blog get(int id);
	public List<Blog> list();
	public List<Blog> approvedBlogsList(); 		
	public boolean approveBlog(int blogid);		
	public boolean rejectBlog(int blogid);		
	public boolean incLikes(int blogid);
	public boolean incDisLikes(int blogid);

	
}