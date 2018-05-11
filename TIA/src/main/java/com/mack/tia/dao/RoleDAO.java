package com.mack.tia.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mack.tia.model.Role;

@Repository
public class RoleDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Role getRoleById(String role) {
		return entityManager.find(Role.class, role);
	}
}
