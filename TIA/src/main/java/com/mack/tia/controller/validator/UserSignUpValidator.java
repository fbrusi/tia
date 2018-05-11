package com.mack.tia.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.User;

@Component
public class UserSignUpValidator implements Validator {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User)target;
		
		if(user.getPasswordConfirm() == null || !user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "password.not.equals");
		}
		
		if(userDAO.userExists(user.getUsername()) != null) {
			errors.rejectValue("username", "duplicated.username");
		}
	}
}
