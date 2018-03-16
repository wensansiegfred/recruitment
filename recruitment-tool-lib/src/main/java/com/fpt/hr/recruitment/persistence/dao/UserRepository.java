package com.fpt.hr.recruitment.persistence.dao;

import java.util.List;

import com.fpt.hr.recruitment.persistence.model.User;

public interface UserRepository {
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public void addUser(User user);
}
