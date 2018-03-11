package org.pharmacy.pharmeasy.manager;

import java.util.ArrayList;

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

	public ArrayList<Approval> retrieveApprovalForDoctorPharma(Integer requesterId, Integer userId);

	public ArrayList<Prescription> retrievePrescriptionByUserId(Integer userId);

	public ArrayList<Approval> retrieveApprovalForUser(Integer userId);

	public ArrayList<User> retrieveUserByType(String userType);

	public ArrayList<Prescription> retrievePrescriptionByRequesterForUser(Integer userId, Integer requester);

	public boolean rejectApproval(Integer approvalId);

}
