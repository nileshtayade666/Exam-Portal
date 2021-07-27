package com.exam.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.services.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService ;


	
	@GetMapping("id/{id}")
	public User findById(@PathVariable("id") long id )
	{
		return userService.findByID(id);
	}
	
	@GetMapping("username/{username}")
	public User findById(@PathVariable("username") String  username )
	{
		return userService.findByUsername(username);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id )
	{
		 userService.delete(id);
		 
		 return ResponseEntity.ok("Deleted Successfully");
	}
	
	
	@GetMapping("/")
	public List<User> findAll()
	{
		return userService.findAll();
	}
	
	
	
	
}
