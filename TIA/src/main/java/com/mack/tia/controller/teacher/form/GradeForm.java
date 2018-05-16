package com.mack.tia.controller.teacher.form;

import java.util.List;

import com.mack.tia.model.Grade;
import com.mack.tia.model.SchoolSubject;

public class GradeForm {
	
	private SchoolSubject schoolSubject;
	
	private List<Grade> grades;

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public SchoolSubject getSchoolSubject() {
		return schoolSubject;
	}

	public void setSchoolSubject(SchoolSubject schoolSubject) {
		this.schoolSubject = schoolSubject;
	}
}
