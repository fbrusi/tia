package com.mack.tia.controller.validator;

import org.apache.commons.validator.routines.IntegerValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mack.tia.model.SchoolSubject;

@Component
public class SchoolSubjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SchoolSubject.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		SchoolSubject schoolSubject = (SchoolSubject)target;
		
		Integer gradeA = IntegerValidator.getInstance().isValid(schoolSubject.getGradeA()) ? Integer.valueOf(schoolSubject.getGradeA()) : -1;
		Integer gradeB = IntegerValidator.getInstance().isValid(schoolSubject.getGradeB()) ? Integer.valueOf(schoolSubject.getGradeB()) : -1;
		Integer gradeC = IntegerValidator.getInstance().isValid(schoolSubject.getGradeC()) ? Integer.valueOf(schoolSubject.getGradeC()) : -1;
		Integer gradeD = IntegerValidator.getInstance().isValid(schoolSubject.getGradeD()) ? Integer.valueOf(schoolSubject.getGradeD()) : -1;
		Integer gradePF = IntegerValidator.getInstance().isValid(schoolSubject.getGradePF()) ? Integer.valueOf(schoolSubject.getGradePF()) : -1;
		
		if(gradeA < 0 || gradeA > 100) { errors.rejectValue("gradeA", "invalid.grade"); }
		if(gradeB < 0 || gradeB > 100) { errors.rejectValue("gradeB", "invalid.grade"); }
		if(gradeC < 0 || gradeC > 100) { errors.rejectValue("gradeC", "invalid.grade"); }
		if(gradeD < 0 || gradeD > 100) { errors.rejectValue("gradeD", "invalid.grade"); }
		if(gradePF < 0 || gradePF > 100) { errors.rejectValue("gradePF", "invalid.grade"); }
		
		if(gradeA + gradeB + gradeC + gradeD + gradePF != 100) {
			errors.rejectValue("gradePF", "invalid.grade_sum");
		}
		
		schoolSubject.setGradeA(gradeA.toString());
		schoolSubject.setGradeB(gradeB.toString());
		schoolSubject.setGradeC(gradeC.toString());
		schoolSubject.setGradeD(gradeD.toString());
		schoolSubject.setGradePF(gradePF.toString());
	}
}
