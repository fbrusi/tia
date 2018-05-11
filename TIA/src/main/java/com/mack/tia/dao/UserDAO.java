package com.mack.tia.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.mack.tia.model.User;

@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String jpql = "select u from User u where u.username = :username";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("username", username);
		
		List<User> users = query.getResultList();
		
		if(users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("O usuário " + username + " não existe.");
		}
		
		User user = users.get(0);
		if(!user.isEnabled()) {
			throw new UsernameNotFoundException("O usuário " + username + " não está ativado.");
		}
		
		return users.get(0);
	}

	public void save(User user) {
		entityManager.persist(user);
	}
	
	public void update(User user) {
		entityManager.merge(user);
	}
	
	public User getUserById(BigInteger id) {
		return entityManager.find(User.class, id);
	}

	public User userExists(String username) {
		
		String jpqlCountKey = "select count(u) from User u where u.username = :username";
		TypedQuery<Long> queryCountKey = entityManager.createQuery(jpqlCountKey, Long.class);
		queryCountKey.setParameter("username", username);
		
		Long quantity = queryCountKey.getSingleResult();
		
		if(quantity == 0) {
			return null;
		}
		
		String jpqlGetUser = "select u from User u where u.username = :username";

		TypedQuery<User> queryGetUser = entityManager.createQuery(jpqlGetUser, User.class);
		queryGetUser.setParameter("username", username);

		return queryGetUser.getSingleResult();
	}
	
	public User passwordResetKeyExists(String passwordResetKey) {

		String jpqlCountKey = "select count(u) from User u where u.passwordResetKey = :passwordResetKey";
		TypedQuery<Long> queryCountKey = entityManager.createQuery(jpqlCountKey, Long.class);
		queryCountKey.setParameter("passwordResetKey", passwordResetKey);
		
		Long quantity = queryCountKey.getSingleResult();
		
		if(quantity == 0) {
			return null;
		}
		
		String jpqlGetUser = "select u from User u where u.passwordResetKey = :passwordResetKey";

		TypedQuery<User> queryGetUser = entityManager.createQuery(jpqlGetUser, User.class);
		queryGetUser.setParameter("passwordResetKey", passwordResetKey);

		return queryGetUser.getSingleResult();
	}
	
	public User activationKeyExists(String activationKey) {

		String jpqlCountKey = "select count(u) from User u where u.activationKey = :activationKey";
		TypedQuery<Long> queryCountKey = entityManager.createQuery(jpqlCountKey, Long.class);
		queryCountKey.setParameter("activationKey", activationKey);
		
		Long quantity = queryCountKey.getSingleResult();
		
		if(quantity == 0) {
			return null;
		}
		
		String jpqlGetUser = "select u from User u where u.activationKey = :activationKey";

		TypedQuery<User> queryGetUser = entityManager.createQuery(jpqlGetUser, User.class);
		queryGetUser.setParameter("activationKey", activationKey);

		return queryGetUser.getSingleResult();
	}
	
	public void activateUser(User user) {
		
		user.setEnabled(true);
		user.setActivationKey(null);
		
		update(user);
	}
}
