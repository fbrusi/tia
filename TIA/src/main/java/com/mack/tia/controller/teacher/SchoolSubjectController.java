package com.mack.tia.controller.teacher;

import java.math.BigInteger;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mack.tia.dao.SchoolSubjectDAO;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/teacher")
public class SchoolSubjectController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SchoolSubjectDAO schoolSubjectDAO;
	
	@RequestMapping(value = "/schoolSubject")
	public ModelAndView accessSchoolSubjectView(SchoolSubject schoolSubject) {
		
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User teacher = userDAO.userExists(loggedUser.getUsername());
		
		ModelAndView view = new ModelAndView("teacher/schoolSubject");
		view.addObject("subjects", schoolSubjectDAO.getSubjectsByTeacher(teacher));
		
		return view;
	}
	
	@Transactional
	@RequestMapping(value = "/insertSubject", method = RequestMethod.POST)
	public ModelAndView registerNewSubject(@Valid SchoolSubject schoolSubject, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			schoolSubject.setSubject("");
			schoolSubject.setSemester(1);
			
			return accessSchoolSubjectView(schoolSubject);
		}
		
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User teacher = userDAO.userExists(loggedUser.getUsername());
		schoolSubject.setTeacher(teacher);
		
		schoolSubjectDAO.save(schoolSubject);
		redirectAttributes.addFlashAttribute("alertMessage", "Matéria cadastrada com sucesso!");

		ModelAndView view = new ModelAndView("redirect:schoolSubject");
		return view;
	}
	
	@Transactional
	@RequestMapping(value = "/removeSubject", method = RequestMethod.GET)
	public ModelAndView removeSubject(String id, RedirectAttributes redirectAttributes) {
		
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		SchoolSubject schoolSubject = schoolSubjectDAO.getSchoolSubjectById(new BigInteger(id));
		if(schoolSubject.getTeacher().getId().equals(loggedUser.getId())) {
			
			schoolSubjectDAO.removeSubject(schoolSubject);
			redirectAttributes.addFlashAttribute("alertMessage", "Matéria removida com sucesso!");
		}
		
		ModelAndView view = new ModelAndView("redirect:schoolSubject");
		return view;
	}
}
