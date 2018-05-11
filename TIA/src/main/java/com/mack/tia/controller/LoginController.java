package com.mack.tia.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mack.tia.constant.Profile;
import com.mack.tia.model.User;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/nonlogged/login", method = RequestMethod.GET)
	public ModelAndView accessLoginView() {
		
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/nonlogged/redirectProfile")
	public ModelAndView logIn() {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(Profile.ROLE_ALUNO.toString().equals(user.getRole().getAuthority())) {
			return new ModelAndView("redirect:/student/home");
		}
		else if(Profile.ROLE_PROFESSOR.toString().equals(user.getRole().getAuthority())) {
			return new ModelAndView("redirect:/teacher/home");
		}
		else {
			return new ModelAndView("redrect:/nonlogged/logout"); 
		}
	}
}
