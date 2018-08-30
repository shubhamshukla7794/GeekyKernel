package com.shubham.geekykernel.dao;

import com.shubham.geekykernel.domain.UserDetail;

public interface UserDAO {
	
	public boolean registerUser(UserDetail userdetail);
	public boolean updateUser(UserDetail userdetail);
	public UserDetail getUser(String loginname);
	public UserDetail checkUser(UserDetail userdetail);//available or not
	
}
