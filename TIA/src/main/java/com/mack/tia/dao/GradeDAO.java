package com.mack.tia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mack.tia.model.Grade;
import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Repository
public class GradeDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Grade> loadGradesBySchoolSubject(SchoolSubject schoolSubject) {
		
		TypedQuery<Grade> query = entityManager.createQuery("SELECT g FROM Grade g WHERE g.schoolSubject = :schoolSubject", Grade.class);
		query.setParameter("schoolSubject", schoolSubject);
		
		return query.getResultList();
	}
	
	public void addGrade(SchoolSubject schoolSubject, User student) {
		
		Grade grade = new Grade();
		
		grade.setStudent(student);
		grade.setSchoolSubject(schoolSubject);
		
		entityManager.persist(grade);
	}
	
	public void removeGrade(SchoolSubject schoolSubject, User student) {
		
		Query query = entityManager.createQuery("DELETE FROM Grade g WHERE g.schoolSubject = :schoolSubject AND g.student = :student");
		query.setParameter("schoolSubject", schoolSubject);
		query.setParameter("student", student);
		
		query.executeUpdate();
	}
	
	public void updateGrade(Grade grade) {
		entityManager.merge(grade);
	}
}
