package org.pharmacy.pharmeasy.manager;

import java.util.List;

import org.pharmacy.pharmeasy.model.Approval;
import org.pharmacy.pharmeasy.model.Prescription;
import org.pharmacy.pharmeasy.model.User;

public interface PharmacyManager {

	public User createUser(User user);

	public Prescription retrievePrescription(Integer prescriptionId);

	public Prescription createPrescription(Prescription prescription);

	public Approval createApproval(Approval approval);

	public Approval updateApproval(Approval approval);

	public Approval retrieveApproval(Integer approvalId);

	public User retrieveUserByDetails(String userName, String password);

	public List<Prescription> retrievePrescriptionByUserId(Integer userId);

	public List<Approval> retrieveApprovalForUser(Integer userId);

	public List<User> retrieveUserByType(String userType);

	public boolean rejectApproval(Integer approvalId);

	public User updateUser(User user);

	public List<Approval> retrieveApprovedApprovalForRequester(Integer requesterId);

	public List<Approval> retrievePendingApprovalForUser(Integer userId);

	List<Approval> retrieveApprovalForUserByRequester(Integer requesterId, Integer userId);

}
