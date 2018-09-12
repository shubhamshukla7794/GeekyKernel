package com.shubham.geekykernel.restrcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.geekykernel.dao.UserDAO;
import com.shubham.geekykernel.domain.UserDetail;

@RestController
public class UserRestController
{
	@Autowired
	UserDAO userDAO;

	@PostMapping("/registerUser")
	public ResponseEntity<UserDetail> registerUser(@RequestBody UserDetail userDetail) 
	{
		userDetail.setRoles("ROLE_USER");
		if (userDAO.registerUser(userDetail)) 
		{
			return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
		} 
		else 
		{
			return new ResponseEntity<UserDetail>(userDetail, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/checkLogin")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail, HttpSession session)
	{
		UserDetail tempUserDetail = userDAO.checkUser(userDetail);
		/*if (tempUserDetail != null)
		{
			session.setAttribute("userDetail", tempUserDetail);
			session.setAttribute("loginname", tempUserDetail.getLoginname());
			return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.OK);

		} 
		else 
		{
			return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.NOT_FOUND);
		}*/
		
		try 
		{
			if (tempUserDetail != null)
			{
				session.setAttribute("userDetail", tempUserDetail);
				session.setAttribute("loginname", tempUserDetail.getLoginname());
				return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.OK);

			} 
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.NOT_FOUND);				
		}
	    return new ResponseEntity<UserDetail>(tempUserDetail, HttpStatus.OK);
	}
}
