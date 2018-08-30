package com.shubham.geekykernel.restrcontroller;

import java.net.URI;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.shubham.geekykernel.dao.ProfilePictureDAO;
import com.shubham.geekykernel.domain.ProfileImage;
import com.shubham.geekykernel.domain.UserDetail;


@RestController
public class ProfileRestCotroller 
{	
	@Autowired
	private ProfilePictureDAO profilepictureDAO;
	
	@Autowired
	HttpSession session;
	
	
	@PostMapping("profile/ImageUpload")
	public ResponseEntity<?> upload(@RequestParam("file") CommonsMultipartFile file)
	{
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		if(userDetail==null)
		{
			return new ResponseEntity<String>("Unauthorised User",HttpStatus.NOT_FOUND);
		}
		else
		{
			ProfileImage profile=new ProfileImage();
			profile.setLoginname(userDetail.getLoginname());
			profile.setpicture(file.getBytes());
			profilepictureDAO.uploadProfile(profile);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("http://localhost:8087/GeekyKernelMainFrontEnd/#/"));
			return new ResponseEntity<Void>(headers,HttpStatus.MOVED_PERMANENTLY);
		}
	}
	@RequestMapping("profile/getProfilePicture/{loginname}")
	public @ResponseBody byte[] getProfilePicture(@PathVariable String loginname)
	{
		//UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		
		ProfileImage profile = profilepictureDAO.getProfile(loginname);
		
		if(profile == null)
		{
			return null;
		}
		else
		{
			byte[] image = profile.getpicture();
			return image;
		}
}
}
	
