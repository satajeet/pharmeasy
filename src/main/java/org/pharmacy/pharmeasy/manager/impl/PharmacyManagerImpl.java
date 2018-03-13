package org.pharmacy.pharmeasy.manager.impl;

import java.util.List;

import org.pharmacy.pharmeasy.dao.ApprovalDao;
import org.pharmacy.pharmeasy.dao.PrescriptionDao;
import org.pharmacy.pharmeasy.dao.UserDao;
import org.pharmacy.pharmeasy.dao.impl.ApprovalDaoImpl;
import org.pharmacy.pharmeasy.dao.impl.PrescriptionDaoImpl;
import org.pharmacy.pharmeasy.dao.impl.UserDaoImpl;
import org.pharmacy.pharmeasy.manager.PharmacyManager;
import org.pharmacy.pharmeasy.model.Approval;
import org.pharmacy.pharmeasy.model.Prescription;
import org.pharmacy.pharmeasy.model.User;

public class PharmacyManagerImpl implements PharmacyManager {

	private UserDao userDao;
	private PrescriptionDao prescriptionDao;
	private ApprovalDao approvalDao;

	private void injectDependencies() {
		userDao = new UserDaoImpl();
		prescriptionDao = new PrescriptionDaoImpl();
		approvalDao = new ApprovalDaoImpl();
	}

	@Override
	public User createUser(User user) {
		injectDependencies();
		return userDao.createUser(user);
	}

	@Override
	public Prescription retrievePrescription(Integer prescriptionId) {
		injectDependencies();
		return prescriptionDao.retrievePrescription(prescriptionId);
	}

	@Override
	public Prescription createPrescription(Prescription prescription) {
		injectDependencies();
		return prescriptionDao.createPrescription(prescription);
	}

	@Override
	public Approval createApproval(Approval approval) {
		injectDependencies();
		return approvalDao.createApproval(approval);
	}

	@Override
	public Approval updateApproval(Approval approval) {
		injectDependencies();
		return approvalDao.updateApproval(approval);
	}

	@Override
	public Approval retrieveApproval(Integer approvalId) {
		injectDependencies();
		return approvalDao.retrieveApproval(approvalId);
	}

	@Override
	public User retrieveUserByDetails(String userName, String password) {
		injectDependencies();
		return userDao.retrieveUserByDetails(userName, password);
	}

	@Override
	public List<Prescription> retrievePrescriptionByUserId(Integer userId) {
		injectDependencies();
		return prescriptionDao.retrievePrescriptionByUserId(userId);
	}

	@Override
	public List<Approval> retrieveApprovalForUserByRequester(Integer requesterId, Integer userId) {
		injectDependencies();
		return approvalDao.retrieveApprovalForUserByRequester(requesterId, userId);
	}

	@Override
	public List<Approval> retrieveApprovalForUser(Integer userId) {
		injectDependencies();
		return approvalDao.retrieveApprovalForUser(userId);
	}

	@Override
	public List<User> retrieveUserByType(String userType) {
		injectDependencies();
		return userDao.retrieveUserByType(userType);
	}

	@Override
	public boolean rejectApproval(Integer approvalId) {
		injectDependencies();
		return approvalDao.rejectApproval(approvalId);
	}

	@Override
	public User updateUser(User user) {
		injectDependencies();
		return userDao.updateUser(user);
	}

	@Override
	public List<Approval> retrieveApprovedApprovalForRequester(Integer requesterId) {
		injectDependencies();
		return approvalDao.retrieveApprovedApprovalForRequester(requesterId);
	}

	@Override
	public List<Approval> retrievePendingApprovalForUser(Integer userId) {
		injectDependencies();
		return approvalDao.retrievePendingApprovalForUser(userId);
	}

}
