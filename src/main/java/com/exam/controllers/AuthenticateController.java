package com.exam.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.security.JwtUtil;
import com.exam.services.UserService;
import com.exam.services.servicesImpl.UserDetailsServiceImpl;


@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private UserService userService ;

	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) throws Exception
	{
		
		Set<UserRole> userroles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userrole = new UserRole();
		userrole.setRole(role);
		userrole.setUser(user);
		
		userroles.add(userrole);
		return this.userService.createUser(user, userroles);
	}
	
	
	@PostMapping("/token")
	public ResponseEntity<?>  generateToken(@RequestBody JwtRequest jwtRequest)
	{
		
		System.out.println(jwtRequest);
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (Exception e) {
		}
		
	UserDetails userDetails=	this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		
	String token=	this.jwtUtil.generateToken(userDetails);
		
	System.out.println("Token : "+token);
	
	
		return ResponseEntity.ok(new JwtResponse(token)); 
	}
	

}
