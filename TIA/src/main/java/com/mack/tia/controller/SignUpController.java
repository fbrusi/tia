package com.mack.tia.controller;

import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mack.tia.constant.Profile;
import com.mack.tia.controller.validator.UserSignUpValidator;
import com.mack.tia.dao.RoleDAO;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.User;

@Controller
public class SignUpController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private UserSignUpValidator userSignUpValidator;
	
	@Autowired
	private MailSender mailer;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userSignUpValidator);
	}

	@RequestMapping(value = "/nonlogged/firstAccess", method = RequestMethod.GET)
	public ModelAndView accessFirstAccessForm(User user) {
		
		ModelAndView modelAndView = new ModelAndView("signUpForm");
		modelAndView.addObject("profiles", Profile.values());
		
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value = "/nonlogged/firstAccess", method = RequestMethod.POST)
	public ModelAndView requestFirstAccess(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			user.setPassword("");
			user.setPasswordConfirm("");
			
			return accessFirstAccessForm(user);
		}
		
		user.setActivationKey(String.valueOf(new Random().nextLong()).replaceAll("-", ""));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		user.setRole(roleDAO.getRoleById(user.getRole().getAuthority()));
		userDAO.save(user);
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("TIA");
		email.setTo(user.getUsername());
		email.setSubject("TIA - Confirmação de cadastro");
		email.setText("Acesse http://localhost:8080/tia/nonlogged/activate?activationKey=" + user.getActivationKey() + " para ativar a sua conta.");
		//mailer.send(email);
		
		redirectAttributes.addFlashAttribute("alertMessage", "Usuário cadastrado com sucesso! Favor ativar a conta através do link enviado por e-mail.");
		
		ModelAndView modelAndView = new ModelAndView("redirect:login");
		return modelAndView;
	}
}
