package com.align.users.model.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestDto {
	

	private String userName;
	private String email;
	private String id;
	private String role;
	@NotNull(message="Password should be 6 to 12 characters")
	@Size(min=6, max=12)
	private String password;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
