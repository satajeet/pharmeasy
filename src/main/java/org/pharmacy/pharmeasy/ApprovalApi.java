package org.pharmacy.pharmeasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pharmacy.pharmeasy.manager.PharmacyManager;
import org.pharmacy.pharmeasy.manager.impl.PharmacyManagerImpl;
import org.pharmacy.pharmeasy.model.Approval;

@Path("approval")
public class ApprovalApi {

	private PharmacyManager pharmacyManager;

	private void injectDependencies() {

		pharmacyManager = new PharmacyManagerImpl();
	}

	/**
	 * Create a request by a doctor or pharmacist to view presciption. While
	 * creating set "approval" parameter to "false"
	 * 
	 * @param approval
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createApproval(Approval approval) {
		injectDependencies();
		Approval approvalObj = pharmacyManager.createApproval(approval);
		if (approvalObj != null) {
			return Response.status(Status.CREATED).entity(approvalObj).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(approvalObj).build();
		}
	}

	/**
	 * Approve a approval request from a doctor/pharmacist - update "approval"
	 * parameter to "true"
	 * 
	 * @param approval
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateApproval(Approval approval) {
		injectDependencies();
		Approval approvalObj = pharmacyManager.updateApproval(approval);
		if (approvalObj != null) {
			return Response.ok().entity(approvalObj).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approvalObj).build();
		}
	}

	/**
	 * Get approval for an approval id
	 * 
	 * @param approvalId
	 * @return
	 */
	@GET
	@Path("approvalId")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveApproval(@QueryParam("approvalId") Integer approvalId) {
		injectDependencies();
		Approval approval = pharmacyManager.retrieveApproval(approvalId);
		if (approval != null) {
			return Response.ok().entity(approval).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approval).build();
		}
	}

	/**
	 * View list of presciptions approved by customer
	 * 
	 * @param requesterId
	 * @param userId
	 * @return
	 */
	@GET
	@Path("approvals")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveApprovalForUserByRequester(@QueryParam("requesterId") Integer requesterId,
			@QueryParam("userId") Integer userId) {
		injectDependencies();
		List<Approval> approvals = pharmacyManager.retrieveApprovalForUserByRequester(requesterId, userId);
		if (approvals != null) {
			return Response.ok().entity(approvals).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approvals).build();
		}
	}

	/**
	 * View approvals by patient/user
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("pendingApproval")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrievePendingApprovalForUser(@QueryParam("userId") Integer userId) {
		injectDependencies();
		List<Approval> approvals = pharmacyManager.retrievePendingApprovalForUser(userId);
		if (approvals != null) {
			return Response.ok().entity(approvals).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approvals).build();
		}
	}

	/**
	 * View all approvals by patient/user
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("approvalsForUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveApprovalForUser(@QueryParam("userId") Integer userId) {
		injectDependencies();
		List<Approval> approvals = pharmacyManager.retrieveApprovalForUser(userId);
		if (approvals != null) {
			return Response.ok().entity(approvals).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approvals).build();
		}
	}

	/**
	 * View approvals by patient/user
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("requester")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveApprovalForRequester(@QueryParam("requester") Integer requesterId) {
		injectDependencies();
		List<Approval> approvals = pharmacyManager.retrieveApprovedApprovalForRequester(requesterId);
		if (approvals != null) {
			return Response.ok().entity(approvals).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(approvals).build();
		}
	}

	/**
	 * Reject approval request of a doctor or a pharmacist
	 * 
	 * @param approvalId
	 * @return
	 */

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response rejectApproval(@QueryParam("approvalId") Integer approvalId) {
		injectDependencies();
		boolean status = pharmacyManager.rejectApproval(approvalId);
		if (status) {
			return Response.ok().build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
	}
}
