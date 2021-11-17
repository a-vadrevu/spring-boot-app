package com.align.users.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class UserUtils {

	private static final Pattern encodedPasswordPattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
	public String encryptPassword(String password) {
		return Base64Utils.encode(password.getBytes()).toString();
	}
	
	public String decryptPassword(String encryptedPassword) {
		return Base64Utils.decode(encryptedPassword.getBytes()).toString();
	}
	
	public boolean isEncryptedPassword(String password) {
		return encodedPasswordPattern.matcher(password).matches();
	}
	
	
}
