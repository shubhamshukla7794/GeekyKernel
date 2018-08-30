package com.shubham.geekykernel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
@Table
@SequenceGenerator(name="blogidseq",sequenceName="blogidseq")
public class Blog
	{
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="blogidseq")
		private int blogId;
		private String blogname;
		private String blogcontent;
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private Date createdate;
		private String loginname;
		private String status;
		private int  likes;
		private int dislikes;
		
		
		public String getBlogname() {
			return blogname;
		}
		public void setBlogname(String blogname) {
			this.blogname = blogname;
		}
		public String getBlogcontent() {
			return blogcontent;
		}
		public void setBlogcontent(String blogcontent) {
			this.blogcontent = blogcontent;
		}
		public Date getCreatedate() {
			return createdate;
		}
		public void setCreatedate(Date createdate) {
			this.createdate = createdate;
		}
		public String getLoginname() {
			return loginname;
		}
		public void setLoginname(String loginname) {
			this.loginname = loginname;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getLikes() {
			return likes;
		}
		public void setLikes(int likes) {
			this.likes = likes;
		}
		public int getDislikes() {
			return dislikes;
		}
		public void setDislikes(int dislikes) {
			this.dislikes = dislikes;
		}
		public int getBlogId() {
			return blogId;
		}
		public void setBlogId(int blogId) {
			this.blogId = blogId;
		}
	

	
	}
