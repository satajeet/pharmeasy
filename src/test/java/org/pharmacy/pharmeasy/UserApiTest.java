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
import org.pharmacy.pharmeasy.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserApiTest {

	private static String basePath = "http://localhost:8080/pharmeasy/user";

	// @Test
	public void testCreateUser() {
		User user = new User();
		user.setPassword("pwd");
		user.setUserId(1);
		user.setUserName("name");
		user.setUserType("User");

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Entity<User> entity = Entity.entity(user, MediaType.APPLICATION_JSON);
		response = builder.post(entity, Response.class);
		Assert.assertEquals(response.getStatus() == 201, true);
	}

	// @Test
	public void testRetrieveUserByDetails() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("login").queryParam("userName", "satajeet").queryParam("password", "satajeet")
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

	// @Test
	public void testRetrieveUserByType() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(basePath);
		Response response = null;
		Builder builder = webTarget.path("listUsers").queryParam("userType", "User").request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		response = builder.get();
		Assert.assertEquals(response.getStatus() == 200, true);
	}

}
