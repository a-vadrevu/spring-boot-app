package com.align.users.model.rest;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.align.users.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto implements Serializable {
      /**
	 * 
	 */
	private static final long serialVersionUID = -8748704283144138725L;
	
	private String responseMessage;
      private HttpStatus  responseCode;
      private List<User> users;
      
      
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
      
}
