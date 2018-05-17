package com.mack.tia.controller.student;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mack.tia.dao.GradeDAO;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.Grade;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/student")
public class ReportController {
	
	@Autowired
	private GradeDAO gradeDAO;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/report")
	public ModelAndView loadReport(String semester) {
		
		if(StringUtils.isBlank(semester) || !StringUtils.isNumeric(semester)) {
			return new ModelAndView("redirect:home");
		}

		Integer numericSemester = Integer.valueOf(semester);
		if(numericSemester < 1 || numericSemester > 8) {
			return new ModelAndView("redirect:home");
		}
		
		ModelAndView view = new ModelAndView();
		view.addObject("semester", semester);
		
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Grade> grades = gradeDAO.getGradesBySemester(userDAO.getUserById(loggedUser.getId()), numericSemester);
		view.addObject("grades", grades);
		
		return view;
	}
}
