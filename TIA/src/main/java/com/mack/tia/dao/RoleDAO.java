package com.mack.tia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mack.tia.constant.Profile;
import com.mack.tia.model.Role;

@Repository
public class RoleDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Role getRoleById(String role) {
		return entityManager.find(Role.class, role);
	}
	
	public void initializeRoles() {
		
		String jpqlCountKey = "SELECT COUNT(r) FROM Role r";
		TypedQuery<Long> queryCountKey = entityManager.createQuery(jpqlCountKey, Long.class);
		
		Long quantity = queryCountKey.getSingleResult();
		
		if(quantity == 0) {
			
			Role student = new Role();
			student.setName(Profile.ROLE_ALUNO.toString());
			
			Role teacher = new Role();
			teacher.setName(Profile.ROLE_PROFESSOR.toString());
			
			entityManager.persist(student);
			entityManager.persist(teacher);
		}
	}
}
