package com.sec.service;

import java.util.List;

import com.sec.entity.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByEmail(String email);

	public String userActivation(String code);
	
	public User findByActivation(String code);
	
	public List<User> getUsers();
	
	
}
