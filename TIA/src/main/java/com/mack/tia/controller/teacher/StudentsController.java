package com.mack.tia.controller.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mack.tia.dao.SchoolSubjectDAO;
import com.mack.tia.dao.StudentDAO;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/teacher")
public class StudentsController {
	
	@Autowired
	private SchoolSubjectDAO schoolSubjectDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "/students")
	public ModelAndView loadView(SchoolSubject schoolSubject) {
		
		ModelAndView view = new ModelAndView("teacher/students");

		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		view.addObject("subjects", schoolSubjectDAO.getSubjectsByTeacher(loggedUser));

		if(schoolSubject != null && schoolSubject.getId() != null) {
			
			SchoolSubject managedSchoolSubject = schoolSubjectDAO.getSchoolSubjectByIdWithStudents(schoolSubject.getId());
			List<User> allStudents = studentDAO.getAllStudents();
			List<User> studentsToDisplay = new ArrayList<>();
			
			for(User studend : allStudents) {
				
				if(!managedSchoolSubject.getStudents().contains(studend)) {
					studentsToDisplay.add(studend);
				}
			}
			
			view.addObject("students", studentsToDisplay);
			view.addObject("schoolSubject", schoolSubject);
		}
		
		return view;
	}
}
