package com.mack.tia.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mack.tia.model.SchoolSubject;
import com.mack.tia.model.User;

@Repository
public class SchoolSubjectDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(SchoolSubject schoolSubject) {
		entityManager.persist(schoolSubject);
	}
	
	public List<SchoolSubject> getSubjectsByTeacher(User teacher) {
		
		TypedQuery<SchoolSubject> query = entityManager.createQuery("SELECT s FROM SchoolSubject s WHERE s.teacher = :teacher ORDER BY semester", SchoolSubject.class);
		query.setParameter("teacher", teacher);
		
		return query.getResultList();
	}
	
	public void removeSubject(SchoolSubject schoolSubject) {
		entityManager.remove(schoolSubject);
	}
	
	public SchoolSubject getSchoolSubjectById(BigInteger id) {
		return entityManager.find(SchoolSubject.class, id);
	}
	
	public SchoolSubject getSchoolSubjectByIdWithStudents(BigInteger id) {
		
		TypedQuery<SchoolSubject> query = entityManager.createQuery("SELECT DISTINCT s FROM SchoolSubject s LEFT JOIN FETCH s.students WHERE s.id = :id", SchoolSubject.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}
	
	public void update(SchoolSubject schoolSubject) {
		entityManager.merge(schoolSubject);
	}
}
