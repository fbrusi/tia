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

import com.mack.tia.controller.validator.PasswordResetValidator;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.User;

@Controller
public class PasswordResetController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordResetValidator passwordResetValidator;
	
	@Autowired
	private MailSender mailer;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(passwordResetValidator);
	}

	@RequestMapping(value = "/nonlogged/passwordReset", method = RequestMethod.GET)
	public ModelAndView accessPasswordResetForm(User user) {
		
		ModelAndView modelAndView = new ModelAndView("passwordReset");
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value = "/nonlogged/passwordReset", method = RequestMethod.POST)
	public ModelAndView requestResetKey(User userVO, RedirectAttributes redirectAttributes) {
		
		User user = userDAO.userExists(userVO.getUsername());
		if(user != null) {
			
			user.setPasswordResetKey(String.valueOf(new Random().nextLong()).replaceAll("-", ""));
			userDAO.update(user);
			
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("TIA");
			email.setTo(user.getUsername());
			email.setSubject("TIA - Confirmação de cadastro");
			email.setText("Acesse http://localhost:8080/tia/nonlogged/resetPassword?passwordResetKey=" + user.getPasswordResetKey() + " para reiniciar sua senha.");
			//mailer.send(email);
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:login");
		redirectAttributes.addFlashAttribute("alertMessage", "Link de reset de senha enviado para o e-mail.");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/nonlogged/resetPassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(User user, RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView();

		if(userDAO.passwordResetKeyExists(user.getPasswordResetKey()) != null) {
			
			modelAndView.setViewName("passwordResetForm");
			modelAndView.addObject("passwordResetKey", user.getPasswordResetKey());
		}
		else {
			modelAndView.setViewName("redirect:login");
		}
		
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value = "/nonlogged/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPasswordForm(@Valid User userToUpdate, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasFieldErrors("password") || bindingResult.hasFieldErrors("passwordConfirm")) {
			
			userToUpdate.setPassword("");
			userToUpdate.setPasswordConfirm("");
			
			return resetPassword(userToUpdate, redirectAttributes);
		}

		User user = userDAO.passwordResetKeyExists(userToUpdate.getPasswordResetKey());
		
		user.setPasswordResetKey(null);
		user.setPassword(new BCryptPasswordEncoder().encode(userToUpdate.getPassword()));
		
		userDAO.update(user);
		
		redirectAttributes.addFlashAttribute("alertMessage", "Senha resetada com sucesso.");
		
		ModelAndView modelAndView = new ModelAndView("redirect:login");
		return modelAndView;
	}
}
