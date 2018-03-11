package org.pharmacy.pharmeasy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.pharmacy.pharmeasy.model.Approval;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApprovalApiTest {

	private static String basePath = "http://localhost:8080/pharmeasy/approval";

	// @Test
	public void testCreateApproval() {
		Approval approval = new Approval();
		approval.setApproval("false");
		approval.setUserId(1);
		approval.setPrescriptionId(1);
		approval.setRequesterId(1);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Approval> entity = Entity.entity(approval, MediaType.APPLICATION_JSON);
		response = builder.post(entity, Response.class);
		Assert.assertEquals(response.getStatus() == 201, true);
	}

	// @Test
	public void testUpdateApproval() {
		Approval approval = new Approval();
		approval.setApproval("false");
		approval.setUserId(1);
		approval.setPrescriptionId(1);
		approval.setRequesterId(1);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Approval> entity = Entity.entity(approval, MediaType.APPLICATION_JSON);
		response = builder.put(entity, Response.class);
		Assert.assertEquals(response.getStatus() == 201, true);
	}

	// @Test
	public void testRetrieveApproval() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvalId").queryParam("approvalId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApprovalForDoctorPharma() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvals").queryParam("requesterId", 1).queryParam("userId", 1)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApprovalForUser() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.queryParam("userId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

}
