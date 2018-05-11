package com.mack.tia.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mack.tia.model.User;

@Component
public class PasswordResetValidator implements Validator {

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
	}
}
