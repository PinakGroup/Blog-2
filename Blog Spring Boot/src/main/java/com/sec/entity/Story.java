package com.sec.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name="stories" )
public class Story {

	@Id @GeneratedValue
	private Long id;
	
	private String title;
	
	private String content;
	
	private Date posted;
	
	@ManyToOne
	private User user;
	
	

	public Story() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		return "Story [id=" + id + ", title=" + title + ", content=" + content + ", posted=" + posted + ", user=" + user
//				+ "]";
//	}
	
	
	
}
