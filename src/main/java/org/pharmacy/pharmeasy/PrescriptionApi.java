package org.pharmacy.pharmeasy;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pharmacy.pharmeasy.manager.PharmacyManager;
import org.pharmacy.pharmeasy.manager.impl.PharmacyManagerImpl;
import org.pharmacy.pharmeasy.model.Prescription;

@Path("prescription")
public class PrescriptionApi {

	private PharmacyManager pharmacyManager;

	private void injectDependencies() {

		pharmacyManager = new PharmacyManagerImpl();
	}

	/**
	 * Add a new prescription by User/Patient
	 * 
	 * @param prescription
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createPrescription(Prescription prescription) {
		injectDependencies();
		Prescription prescriptionObj = pharmacyManager.createPrescription(prescription);
		if (prescriptionObj != null) {
			return Response.status(Status.CREATED).entity(prescriptionObj).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(prescriptionObj).build();
		}
	}

	/**
	 * get prescription for a id
	 * 
	 * @param prescriptionId
	 * @return
	 */
	@GET
	@Path("prescription")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrievePrescription(@QueryParam("prescriptionId") Integer prescriptionId) {
		injectDependencies();
		Prescription prescription = pharmacyManager.retrievePrescription(prescriptionId);
		if (prescription != null) {
			return Response.ok().entity(prescription).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(prescription).build();
		}
	}

	/**
	 * View list of prescriptions of a patient by a doctor or pharmacist
	 * 
	 * @param userId
	 * @param requester
	 * @return
	 */
	@GET
	@Path("requester")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrievePrescriptionByRequesterForUser(@QueryParam("userId") Integer userId,
			@QueryParam("requesterId") Integer requester) {
		injectDependencies();
		ArrayList<Prescription> prescriptions = pharmacyManager.retrievePrescriptionByRequesterForUser(userId,
				requester);
		if (prescriptions != null) {
			return Response.ok().entity(prescriptions).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(prescriptions).build();
		}
	}

	/**
	 * View prescription by patient
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrievePrescriptionByUserId(@QueryParam("userId") Integer userId) {
		injectDependencies();
		ArrayList<Prescription> prescriptions = pharmacyManager.retrievePrescriptionByUserId(userId);
		if (prescriptions != null) {
			return Response.ok().entity(prescriptions).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(prescriptions).build();
		}
	}
}
