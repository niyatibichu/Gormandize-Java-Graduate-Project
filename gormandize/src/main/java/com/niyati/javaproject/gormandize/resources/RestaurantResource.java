package com.niyati.javaproject.gormandize.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.niyati.javaproject.gormandize.model.Restaurant;
import com.niyati.javaproject.gormandize.service.RestaurantService;

@Path("/restaurants")
public class RestaurantResource {
	RestaurantService restaurantService = new RestaurantService();

	// get restaurants
	@Path("view")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	// add restaurant
	@Path("add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Restaurant addRestaurant(Restaurant restaurant) {
		System.out.println("I am inside add restaurant resrc" + restaurant.getAverage_rating());
		return restaurantService.addRestaurant(restaurant);

	}
}
