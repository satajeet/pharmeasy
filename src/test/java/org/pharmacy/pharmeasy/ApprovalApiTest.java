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
	public void testCreateApproval_Success() {
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
	public void testCreateApproval_Failure() {
		Approval approval = new Approval();
		approval.setApproval("false");
		approval.setUserId(0);
		approval.setPrescriptionId(1);
		approval.setRequesterId(1);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Approval> entity = Entity.entity(approval, MediaType.APPLICATION_JSON);
		response = builder.post(entity, Response.class);
		Assert.assertEquals(response.getStatus() != 201, true);
	}

	// @Test
	public void testUpdateApproval_Success() {
		Approval approval = new Approval();
		approval.setApproval("true");
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
	public void testUpdateApproval_Failure() {
		Approval approval = new Approval();
		approval.setApproval("true");
		approval.setUserId(0);
		approval.setPrescriptionId(1);
		approval.setRequesterId(1);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Approval> entity = Entity.entity(approval, MediaType.APPLICATION_JSON);
		response = builder.put(entity, Response.class);
		Assert.assertEquals(response.getStatus() != 201, true);
	}

	// @Test
	public void testRetrieveApproval_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvalId").queryParam("approvalId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApproval_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvalId").queryParam("approvalId", 100).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

	// @Test
	public void testRetrieveApprovalForUserByRequester_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvals").queryParam("requesterId", 2).queryParam("userId", 1)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApprovalForUserByRequester_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvals").queryParam("requesterId", 1).queryParam("userId", 1)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

	// @Test
	public void testRetrieveApprovalForUser_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvalsForUser").queryParam("userId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApprovalForUser_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("approvalsForUser").queryParam("userId", 2).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

	// @Test
	public void testRetrieveApprovalForRequester_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("requester").queryParam("requesterId", 2).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveApprovalForRequester_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("requester").queryParam("requesterId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

	// @Test
	public void testRejectApproval_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.queryParam("approvalId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.delete();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRejectApproval_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.queryParam("approvalId", 100).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.delete();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

}
