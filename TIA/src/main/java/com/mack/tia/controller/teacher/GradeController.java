package com.mack.tia.controller.teacher;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mack.tia.controller.teacher.form.GradeForm;
import com.mack.tia.dao.GradeDAO;
import com.mack.tia.dao.SchoolSubjectDAO;
import com.mack.tia.model.Grade;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Controller
@RequestMapping(value = "/teacher")
public class GradeController {
	
	@Autowired
	private SchoolSubjectDAO schoolSubjectDAO;
	
	@Autowired
	private GradeDAO gradeDAO;

	@RequestMapping(value = "/grades")
	public ModelAndView loadView(SchoolSubject schoolSubject, ModelAndView view) {
		
		view.setViewName("teacher/grades");

		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		view.addObject("subjects", schoolSubjectDAO.getSubjectsByTeacher(loggedUser));
		
		return view;
	}
	
	@Transactional
	@RequestMapping(value = "/loadGrades", method = RequestMethod.POST)
	public ModelAndView loadGrades(SchoolSubject schoolSubject, ModelAndView view) {
		
		if(schoolSubject.getId().intValue() != 0) {
			
			SchoolSubject managedSchoolSubject = schoolSubjectDAO.getSchoolSubjectById(schoolSubject.getId());
			List<Grade> grades = gradeDAO.loadGradesBySchoolSubject(managedSchoolSubject);
			Collections.sort(grades);

			GradeForm gradeForm = new GradeForm();
			gradeForm.setSchoolSubject(managedSchoolSubject);
			gradeForm.setGrades(grades);
			
			view.addObject("gradeForm", gradeForm);
			view.addObject("schoolSubject", managedSchoolSubject);

			return loadView(managedSchoolSubject, view);
		}
		else {
			return loadView(schoolSubject, view);
		}
	}
	
	@Transactional
	@RequestMapping(value = "/updateGrades", method = RequestMethod.POST)
	public ModelAndView updateGrades(GradeForm gradeForm, ModelAndView view) {
		
		boolean hasErrors = false;
		
		for(Grade grade : gradeForm.getGrades()) {
			
			if(!isValidGrade(grade.getGradeA())) {
				grade.setHasErrorGradeA(true);
				hasErrors = true;
			}
			else {
				grade.setHasErrorGradeA(false);
			}
			if(!isValidGrade(grade.getGradeB())) {
				grade.setHasErrorGradeB(true);
				hasErrors = true;
			}
			else {
				grade.setHasErrorGradeB(false);
			}
			if(!isValidGrade(grade.getGradeC())) {
				grade.setHasErrorGradeC(true);
				hasErrors = true;
			}
			else {
				grade.setHasErrorGradeC(false);
			}
			if(!isValidGrade(grade.getGradeD())) {
				grade.setHasErrorGradeD(true);
				hasErrors = true;
			}
			else {
				grade.setHasErrorGradeD(false);
			}
			if(!isValidGrade(grade.getGradePF())) {
				grade.setHasErrorGradePF(true);
				hasErrors = true;
			}
			else {
				grade.setHasErrorGradePF(false);
			}
		}
		
		if(!hasErrors) {
		
			SchoolSubject managedSchoolSubject = schoolSubjectDAO.getSchoolSubjectById(gradeForm.getSchoolSubject().getId());
			List<Grade> grades = gradeDAO.loadGradesBySchoolSubject(managedSchoolSubject);
			
			for(Grade grade : grades) {
				
				for(Grade inputGrade : gradeForm.getGrades()) {
					
					if(grade.getStudent().getId().equals(inputGrade.getStudent().getId())) {
						
						grade.setGradeA(inputGrade.getGradeA());
						grade.setGradeB(inputGrade.getGradeB());
						grade.setGradeC(inputGrade.getGradeC());
						grade.setGradeD(inputGrade.getGradeD());
						grade.setGradePF(inputGrade.getGradePF());
					}
				}
				
				gradeDAO.updateGrade(grade);
			}
			return loadGrades(gradeForm.getSchoolSubject(), view);
		}
		else {
			
			view.setViewName("teacher/grades");

			User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			view.addObject("subjects", schoolSubjectDAO.getSubjectsByTeacher(loggedUser));
			
			SchoolSubject managedSchoolSubject = schoolSubjectDAO.getSchoolSubjectById(gradeForm.getSchoolSubject().getId());
			
			view.addObject("gradeForm", gradeForm);
			view.addObject("schoolSubject", managedSchoolSubject);
			view.addObject("errorMessage", "Uma ou mais notas possuíam valores inválidos e foram desconsideradas. Por favor verifique, boletim não salvo.");
			
			return view;
		}
		
	}
	
	private boolean isValidGrade(String grade) {
		
		if(!StringUtils.isBlank(grade) && !BigDecimalValidator.getInstance().isValid(grade)) {
			return false;
		}
		else if(!StringUtils.isBlank(grade) && 
				BigDecimalValidator.getInstance().isValid(grade) && 
				!BigDecimalValidator.getInstance().isInRange(new BigDecimal(grade), 0, 10)) {
			return false;
		}
		
		return true;
	}
}
