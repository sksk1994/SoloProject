package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Entities.User;

import exceptions.UserAlreadyExists;
import exceptions.UserEmailAlreadyExists;

@Repository
public class UserDAO {

	
	private List<User> listUsers = new ArrayList<User>();

	
	@Autowired
	private EntityManager em;

	public List<User> listUsers() {

		TypedQuery<User> listUsersQuery = em.createQuery("Select u from SCU_USER u", User.class);
		listUsers = listUsersQuery.getResultList();

		return listUsers;
	}

	public void addUser(User user) throws UserAlreadyExists, UserEmailAlreadyExists {

		User usernameInDB = em.find(User.class, user.getUsername());

		if (usernameInDB == null) {

			// named parameter
			TypedQuery<User> listUsersEmailQuery = em
					.createQuery("Select u from SCU_USER u where u.email=:email", User.class);
			listUsersEmailQuery.setParameter("email", user.getEmail());

			List<User> listUserEmails = listUsersEmailQuery.getResultList();
			if (listUserEmails.isEmpty()) {

				EntityTransaction entityTransaction = em.getTransaction();
				entityTransaction.begin();
				em.persist(user);
				entityTransaction.commit();
			} else
				throw new UserEmailAlreadyExists();
		} else {
			throw new UserAlreadyExists();
		}
	}

	public User getUser(String username) {
		User userInDB = em.find(User.class, username);
		return userInDB;
	}

	public void removeUser(String username) {

		User userInDB = em.find(User.class, username);
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(userInDB);

		entityTransaction.commit();
	}

	public void updateUser(User newUser) {
		
		User usernameInDB = em.find(User.class, newUser.getUsername());
		
		if (usernameInDB != null) {

			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.merge(newUser);

			entityTransaction.commit();
		}

	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
