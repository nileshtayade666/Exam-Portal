package com.exam.services;

import java.util.List;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	public User createUser(User user ,Set<UserRole> userroles) throws Exception;
	
	
	public List<User> findAll();

	public User findByID(long id);
	
	public User findByUsername(String username);
	
	public void delete(long id );

	
}
