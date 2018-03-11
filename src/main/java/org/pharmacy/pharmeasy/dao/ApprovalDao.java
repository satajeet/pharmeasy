package org.pharmacy.pharmeasy.dao;

import java.util.ArrayList;

import org.pharmacy.pharmeasy.model.Approval;

public interface ApprovalDao {

	public Approval createApproval(Approval approval);

	public Approval updateApproval(Approval approval);

	public Approval retrieveApproval(Integer approvalId);

	public ArrayList<Approval> retrieveApprovalForDoctorPharma(Integer requesterId, Integer userId);

	public ArrayList<Approval> retrieveApprovalForUser(Integer userId);

	public boolean rejectApproval(Integer approvalId);

}
