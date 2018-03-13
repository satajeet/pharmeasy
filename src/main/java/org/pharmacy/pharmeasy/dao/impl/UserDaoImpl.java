package org.pharmacy.pharmeasy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.UserDao;
import org.pharmacy.pharmeasy.model.User;

public class UserDaoImpl implements UserDao {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	@Transactional
	public User createUser(User userObj) {
		try {
			entityManager.getTransaction().begin();
			userObj = entityManager.merge(userObj);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return userObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User updateUser(User userObj) {
		try {
			Query query = entityManager
					.createQuery("update User obj set obj.password=:password where obj.userId = :userId");
			query.setParameter("userId", userObj.getUserId());
			query.setParameter("password", userObj.getPassword());

			int i = query.executeUpdate();
			return userObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User retrieveUser(Integer userId) {
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
	public List<User> retrieveUserByType(String userType) {
		Query query = entityManager.createQuery("select obj from User obj where obj.userType=:userType");
		query.setParameter("userType", userType);
		try {
			List<User> users = query.getResultList();
			return users;
		} catch (Exception e) {
			return null;
		}
	}

}
