package com.mack.tia.controller.teacher;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mack.tia.dao.SchoolSubjectDAO;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/teacher")
public class GradeController {
	
	@Autowired
	private SchoolSubjectDAO schoolSubjectDAO;

	@RequestMapping(value = "/grades")
	public ModelAndView loadView(SchoolSubject schoolSubject) {
		
		ModelAndView view = new ModelAndView("teacher/grades");

		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		view.addObject("subjects", schoolSubjectDAO.getSubjectsByTeacher(loggedUser));
		
		if(schoolSubject != null && schoolSubject.getId() != null && schoolSubject.getId().intValue() != 0) {

			SchoolSubject managedSchoolSubject = schoolSubjectDAO.getSchoolSubjectByIdWithStudents(schoolSubject.getId());
			
			Collections.sort(managedSchoolSubject.getStudents());
			view.addObject("schoolSubject", managedSchoolSubject);
		}
		
		return view;
	}
}
