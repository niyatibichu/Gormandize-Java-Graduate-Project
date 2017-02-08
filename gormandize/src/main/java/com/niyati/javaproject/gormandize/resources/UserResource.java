package com.niyati.javaproject.gormandize.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.niyati.javaproject.gormandize.model.User;
import com.niyati.javaproject.gormandize.service.UserService;

@Path("/users")
public class UserResource {
	UserService userService = new UserService();

	// Register
	@Path("signup")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User user) {
		// System.out.println(user.getEmail());
		return userService.addUser(user);
	}

	// Login
	@Path("login/{username}/{password}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("username") String username, @PathParam("password") String password) {
		return userService.getUser(username, password);
	}
}
