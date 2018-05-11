package com.mack.tia.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.User;

@Controller
public class UserActivationController {
	
	@Autowired
	private UserDAO userDAO;

	@Transactional
	@RequestMapping(value = "/nonlogged/activate")
	public ModelAndView activateUser(String activationKey, RedirectAttributes redirectAttributes) {
	
		User user = userDAO.activationKeyExists(activationKey);
		
		if(user != null) {

			userDAO.activateUser(user);
			redirectAttributes.addFlashAttribute("alertMessage", "Usuário ativado com sucesso!");
		}
		else {
			redirectAttributes.addFlashAttribute("alertMessage", "Chave de ativação não encontrada.");
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:login");
		return modelAndView;
	}
}
