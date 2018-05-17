package com.mack.tia.controller.teacher;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mack.tia.dao.SchoolSubjectDAO;
import com.mack.tia.dao.UserDAO;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherHomeController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SchoolSubjectDAO schoolSubjectDAO;

	@RequestMapping(value = "/home")
	public String accessHome() {
		return "teacher/teacherHome";
	}
	
	@Transactional
	@RequestMapping(value = "/resetSemester")
	public ModelAndView resetSemester(RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:home");
		
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User teacher = userDAO.getUserById(loggedUser.getId());
		
		List<SchoolSubject> schoolSubjects = schoolSubjectDAO.getSubjectsByTeacher(teacher);
		
		for(SchoolSubject schoolSubject : schoolSubjects) {
			schoolSubjectDAO.removeSubject(schoolSubject);
		}
		
		redirectAttributes.addFlashAttribute("alertMessage", "Semestre reiniciado com sucesso!");
		
		return view;
	}
}
