package org.pharmacy.pharmeasy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.ApprovalDao;
import org.pharmacy.pharmeasy.model.Approval;

public class ApprovalDaoImpl implements ApprovalDao {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	@Transactional
	public Approval createApproval(Approval approvalObj) {
		try {
			entityManager.getTransaction().begin();
			approvalObj = entityManager.merge(approvalObj);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return approvalObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Approval updateApproval(Approval approvalObj) {
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
		try {
			Query query = entityManager.createQuery("select obj from Approval obj where obj.approvalId=:approvalId");
			query.setParameter("approvalId", approvalId);

			return (Approval) query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Approval> retrieveApprovalForUserByRequester(Integer requesterId, Integer userId) {
		try {
			Query query = entityManager.createQuery(
					"select obj from Approval obj where obj.requesterId=:requesterId and obj.userId=:userId");
			query.setParameter("requesterId", requesterId);
			query.setParameter("userId", userId);

			ArrayList<Approval> approvals = (ArrayList<Approval>) query.getResultList();
			return approvals;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Approval> retrieveApprovalForUser(Integer userId) {
		try {
			Query query = entityManager.createQuery("select obj from Approval obj where obj.userId=:userId");
			query.setParameter("userId", userId);

			ArrayList<Approval> approvals = (ArrayList<Approval>) query.getResultList();
			return approvals;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public boolean rejectApproval(Integer approvalId) {
		try {
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

	@Override
	public List<Approval> retrieveApprovedApprovalForRequester(Integer requesterId) {
		try {
			Query query = entityManager
					.createQuery("select obj from Approval obj where obj.requesterId=:requesterId and approval='true'");
			query.setParameter("requesterId", requesterId);

			ArrayList<Approval> approvals = (ArrayList<Approval>) query.getResultList();
			return approvals;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Approval> retrievePendingApprovalForUser(Integer userId) {
		try {
			Query query = entityManager
					.createQuery("select obj from Approval obj where obj.userId=:userId and approval='false'");
			query.setParameter("userId", userId);

			ArrayList<Approval> approvals = (ArrayList<Approval>) query.getResultList();
			return approvals;
		} catch (Exception e) {
			return null;
		}
	}

}
