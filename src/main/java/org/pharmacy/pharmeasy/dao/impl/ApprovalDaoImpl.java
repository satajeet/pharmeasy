package org.pharmacy.pharmeasy.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.ApprovalDao;
import org.pharmacy.pharmeasy.model.Approval;

public class ApprovalDaoImpl implements ApprovalDao {

	@Override
	@Transactional
	public Approval createApproval(Approval approvalObj) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query maxIdQuery = entityManager.createQuery("select max(obj.approvalId) from Approval obj");
		Integer maxId = 0;
		try {
			maxId = (Integer) maxIdQuery.getSingleResult();
		} catch (Exception e) {
			maxId = 0;
		}
		if (maxId == null) {
			maxId = 0;
		}
		Integer approvalId = maxId + 1;
		approvalObj.setApprovalId(approvalId);
		try {
			entityManager.getTransaction().begin();
			if (!entityManager.contains(approvalObj)) {
				entityManager.persist(approvalObj);
				entityManager.flush();
			}
			entityManager.getTransaction().commit();
			return approvalObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Approval updateApproval(Approval approvalObj) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(approvalObj);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return approvalObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Approval retrieveApproval(Integer approvalId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select obj from Approval obj where obj.approvalId=:approvalId");
		query.setParameter("approvalId", approvalId);
		try {
			return (Approval) query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Approval> retrieveApprovalForDoctorPharma(Integer requesterId, Integer userId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager
				.createQuery("select obj from Approval obj where obj.requesterId=:requesterId and obj.userId=:userId");
		query.setParameter("requesterId", requesterId);
		query.setParameter("userId", userId);
		try {
			return (ArrayList<Approval>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Approval> retrieveApprovalForUser(Integer userId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select obj from approval obj where obj.userId=:userId");
		query.setParameter("userId", userId);
		try {
			return (ArrayList<Approval>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public boolean rejectApproval(Integer approvalId) {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Approval approval = entityManager.find(Approval.class, approvalId);
			entityManager.getTransaction().begin();
			entityManager.remove(approval);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
