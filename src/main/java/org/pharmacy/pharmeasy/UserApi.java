package org.pharmacy.pharmeasy;

import java.util.List;

import javax.ws.rs.Consumes;
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
import org.pharmacy.pharmeasy.model.User;

@Path("user")
public class UserApi {

	private PharmacyManager pharmacyManager;

	private void injectDependencies() {

		pharmacyManager = new PharmacyManagerImpl();
	}

	/**
	 * for new user signup
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createUser(User user) {
		injectDependencies();
		User userObj = pharmacyManager.createUser(user);
		if (user != null) {
			return Response.ok().entity(userObj).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(user).build();
		}
	}

	/**
	 * If user wants to change password
	 * 
	 * @param user
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUser(User user) {
		injectDependencies();
		User userObj = pharmacyManager.updateUser(user);
		if (user != null) {
			return Response.ok().entity(userObj).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(user).build();
		}
	}

	/**
	 * api for login of user
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@GET
	@Path("login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveUserByDetails(@QueryParam("userName") String userName,
			@QueryParam("password") String password) {
		injectDependencies();
		User user = pharmacyManager.retrieveUserByDetails(userName, password);
		if (user != null) {
			return Response.ok().entity(user).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(user).build();
		}
	}

	/**
	 * View list of patients by a doctor or pharmacist
	 * 
	 * @param userType
	 * @return
	 */
	@GET
	@Path("listUsers")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveUserByType(@QueryParam("userType") String userType) {
		injectDependencies();
		List<User> user = pharmacyManager.retrieveUserByType(userType);
		if (user != null) {
			return Response.ok().entity(user).build();
		} else {
			return Response.status(Status.SERVICE_UNAVAILABLE).entity(user).build();
		}
	}
}
