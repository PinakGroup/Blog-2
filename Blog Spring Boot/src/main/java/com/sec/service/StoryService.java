package com.sec.service;

import java.util.List;

import com.sec.entity.Story;

public interface StoryService {

	public String addNewStory(Story story);
	
	public List<Story> getStories();
	
	
}
