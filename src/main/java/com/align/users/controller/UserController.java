package com.align.users.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.align.users.model.User;
import com.align.users.model.rest.RequestDto;
import com.align.users.model.rest.ResponseDto;
import com.align.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * This is to get all the users data from database
	 * @return
	 */
	@GetMapping("/getUsers")
	public ResponseEntity<ResponseDto> getAllUsers() {
		ResponseDto response = new ResponseDto();
		try {
		List<User> result =  userService.getAllUsers();
		response.setUsers(result);
		response.setResponseCode(HttpStatus.OK);
		response.setResponseMessage("Success");
		}catch(Exception e) {
			response.setResponseCode(HttpStatus.BAD_REQUEST);
			response.setResponseMessage("Error while getting data");
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
		
	}
	/**
	 * This is to get the user details based on userId
	 * @param id
	 * @return
	 */
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<ResponseDto> getUserById(@Validated @PathVariable String  id) {
		
		ResponseDto response = new ResponseDto();
		HttpStatus httpStatus;
		try {
		List<User> result =  userService.getUserById(id);
		if(result.size() == 0) {
			httpStatus= HttpStatus.NOT_FOUND;
			response.setResponseMessage("Success");
		}else {
			httpStatus = HttpStatus.OK;
			response.setUsers(result);
		}
		response.setResponseMessage("Success");
		}catch(NumberFormatException ne) {
			httpStatus = HttpStatus.BAD_REQUEST;
			response.setResponseMessage("Id is not valid");
		}catch(Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setResponseMessage("Error while getting the details");
		}
		response.setResponseCode(httpStatus);
		return new ResponseEntity<ResponseDto>(response,httpStatus);
	}
	
	@PutMapping(path="/cuuser",consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> createUser(@Validated @RequestBody RequestDto requestBody) {
		ResponseDto response = new ResponseDto();
		
		try {
		User user = new User();
		user.setEmail(requestBody.getEmail());
		user.setPassword(requestBody.getPassword());
		user.setEmail(requestBody.getEmail());
		user.setRoles(requestBody.getRole());
		
		List<User> usersFromDb = new ArrayList<User>();
		if(user.getId() != null) {		
			usersFromDb = 	userService.getUserById(user.getId());
		}
		if(usersFromDb != null && usersFromDb.size()>0) {
		    userService.updateUser(user);	
		}else {
			userService.createUser(user);
		}
		
		response.setResponseMessage("Success");
		response.setResponseCode(HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			response.setResponseMessage("Error");
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
		return new ResponseEntity<ResponseDto>(response,response.getResponseCode());
	}

	/**
	 * This to delete the user either by Id, userName , email.
	 * @param requestDto
	 * @return
	 */
	@PostMapping("/deleteUser")
	public ResponseEntity<ResponseDto> deleteUser(@Validated @RequestBody RequestDto requestDto) {
		ResponseDto response = new ResponseDto();		
		try {
			if(StringUtils.hasText(requestDto.getId())) {
				userService.deleteUserById(requestDto.getId());
			}else if(StringUtils.hasText(requestDto.getUserName())) {
				userService.deleteUserByUserName(requestDto.getUserName());
			}else if(StringUtils.hasText(requestDto.getEmail())) {
				userService.deleteUserByEmail(requestDto.getEmail());
			}
			response.setResponseCode(HttpStatus.OK);
			response.setResponseMessage("Success");
			
		}catch(Exception e) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage("Success");
		}
		return new ResponseEntity<ResponseDto>(response,response.getResponseCode());
	}
	
}
