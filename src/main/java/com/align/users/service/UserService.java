package com.align.users.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.align.users.Repository.UserRepository;
import com.align.users.model.User;
import com.align.users.util.UserUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired UserUtils userUtils; 
	
	public List<User> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
	public List<User> getUserById(String id) {
		return userRepository.findById(Long.valueOf(id));
	}
	
	public void deleteUserById(String id) {
		userRepository.deleteById(Long.valueOf(id));
	}
	
	public void deleteUserByUserName(String userName) {
		 userRepository.deleteByUserName(userName);
	}
	
	public void deleteUserByEmail(String email) {
		 userRepository.deleteByEmail(email);
	}
	
	public void createUser(User user) {

		//mask password
		user.setPassword(userUtils.encryptPassword(user.getPassword()));

		//Fetch role Id based on roles.
		List<String> roles = Arrays.asList(user.getRoles().split(","));
		if(roles.size() > 1) {
			for(String temp : roles) {
				user.setRole_id(userRepository.getRoleIdByRole(temp));
				userRepository.createUser(user);
			}
		}else {
			user.setRole_id(userRepository.getRoleIdByRole(roles.get(0)));
			userRepository.createUser(user);
		}
	}
	
	public void updateUser(User user) {
		  String password = user.getPassword();
		  if(!userUtils.isEncryptedPassword(password)) {
			  user.setPassword(userUtils.encryptPassword(password));
		  }
		//Fetch role Id based on roles.
			List<String> roles = Arrays.asList(user.getRoles().split(","));
			if(roles.size() > 1) {
				for(String temp : roles) {
					user.setRole_id(userRepository.getRoleIdByRole(temp));
					userRepository.update(user);
				}
			}else {
				user.setRole_id(userRepository.getRoleIdByRole(roles.get(0)));
				userRepository.update(user);
			}
	      userRepository.update(user);
	}
}