package com.sec.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Story;

public interface StoryRepository extends CrudRepository<Story, Long> {

	List<Story> findAll();
}
