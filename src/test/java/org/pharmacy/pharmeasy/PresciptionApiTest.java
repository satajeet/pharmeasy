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
import org.pharmacy.pharmeasy.model.Prescription;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PresciptionApiTest {

	private static String basePath = "http://localhost:8080/pharmeasy/prescription";

	// @Test
	public void testCreatePrescription_Success() {
		Prescription prescription = new Prescription();
		prescription.setMedicalRecords("medRec");
		prescription.setPrescription("presc");
		prescription.setPrescriptionName("prescname");
		prescription.setUserId(1);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Prescription> entity = Entity.entity(prescription, MediaType.APPLICATION_JSON);
		response = builder.post(entity, Response.class);
		Assert.assertEquals(response.getStatus() == 201, true);
	}

	// @Test
	public void testCreatePrescription_Failure() {
		Prescription prescription = new Prescription();
		prescription.setMedicalRecords("medRec");
		prescription.setPrescription("presc");
		prescription.setPrescriptionName("prescname");
		prescription.setUserId(0);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<Prescription> entity = Entity.entity(prescription, MediaType.APPLICATION_JSON);
		response = builder.post(entity, Response.class);
		Assert.assertEquals(response.getStatus() != 201, true);
	}

	// @Test
	public void testRetrievePrescription_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("presciption").queryParam("prescriptionId", 1)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrievePrescription_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("presciption").queryParam("prescriptionId", 100)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

	// @Test
	public void testRetrievePrescriptionByUserId_Success() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.queryParam("userId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrievePrescriptionByUserId_Failure() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.queryParam("userId", 1).request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() != 200, true);
	}

}
