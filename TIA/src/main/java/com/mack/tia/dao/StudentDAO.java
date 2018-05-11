package com.mack.tia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mack.tia.model.User;

@Repository
public class StudentDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> getAllStudents() {
		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.role.name = 'ROLE_ALUNO' ORDER BY name", User.class);
		return query.getResultList();
	}
}
