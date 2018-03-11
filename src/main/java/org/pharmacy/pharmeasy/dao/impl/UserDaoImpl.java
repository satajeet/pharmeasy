package org.pharmacy.pharmeasy.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.UserDao;
import org.pharmacy.pharmeasy.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	@Transactional
	public User createUser(User userObj) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query maxIdQuery = entityManager.createQuery("select max(obj.userId) from User obj");
		Integer maxId = 0;
		try {
			maxId = (Integer) maxIdQuery.getSingleResult();
		} catch (Exception e) {
			maxId = 0;
		}
		if (maxId == null) {
			maxId = 0;
		}
		Integer userId = maxId + 1;
		userObj.setUserId(userId);
		try {
			entityManager.getTransaction().begin();
			if (!entityManager.contains(userObj)) {
				entityManager.persist(userObj);
				entityManager.flush();
			}
			entityManager.getTransaction().commit();
			return userObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User updateUser(User userObj) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager
				.createQuery("update USER obj set obj.PASSWORD=:password where obj.USERID = :userId");
		query.setParameter("userId", userObj.getUserId());
		query.setParameter("password", userObj.getPassword());
		try {
			int i = query.executeUpdate();
			return userObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User retrieveUser(Integer userId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select obj from User obj where obj.userId=:userId");
		query.setParameter("userId", userId);
		try {
			return (User) query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User retrieveUserByDetails(String userName, String password) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager
				.createQuery("select obj from User obj where obj.userName=:userName and obj.password=:password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		try {
			return (User) query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<User> retrieveUserByType(String userType) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select obj from User obj where obj.userType=:userType");
		query.setParameter("userType", userType);
		try {
			return (ArrayList<User>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
