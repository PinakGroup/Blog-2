package com.sec.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	User findByActivation(String code);
	
	
	List<User> findAll();
	
	
}