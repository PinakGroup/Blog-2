package com.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Story;
import com.sec.repo.StoryRepository;

@Service
public class StoryServiceImp implements StoryService{

	private StoryRepository storyRepository;
	
	@Autowired
	public StoryServiceImp(StoryRepository storyRepository) {
		this.storyRepository = storyRepository;
	}
	
	@Override
	public String addNewStory(Story story) {
		storyRepository.save(story);
		return "ok";
	}
	
	@Override
	public List<Story> getStories() {
		return storyRepository.findAll();
	}

}
