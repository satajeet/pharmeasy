package org.pharmacy.pharmeasy.dao;

import java.util.List;

import org.pharmacy.pharmeasy.model.Approval;

public interface ApprovalDao {

	public Approval createApproval(Approval approval);

	public Approval updateApproval(Approval approval);

	public Approval retrieveApproval(Integer approvalId);

	public List<Approval> retrieveApprovalForUserByRequester(Integer requesterId, Integer userId);

	public List<Approval> retrieveApprovalForUser(Integer userId);

	public boolean rejectApproval(Integer approvalId);

	public List<Approval> retrieveApprovedApprovalForRequester(Integer requesterId);

	public List<Approval> retrievePendingApprovalForUser(Integer userId);

}
