package com.exam.services.servicesImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repositories.RoleRepository;
import com.exam.repositories.UserRepository;
import com.exam.services.UserService;


@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userroles) throws Exception {
		
		User local = userRepository.findByUsername(user.getUsername());
		
		if(local !=null)
		{
			System.out.println("User with this Username already Present");
			throw new Exception("User already Exist Exception");
		}
		else
		{
			for(UserRole ur :userroles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userroles);
		local=	userRepository.save(user);
		}
		
		return local;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByID(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
		
	}

}
