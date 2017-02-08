package com.niyati.javaproject.gormandize.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.niyati.javaproject.gormandize.model.Restaurant;
import com.niyati.javaproject.gormandize.model.Review;
import com.niyati.javaproject.gormandize.service.ReviewService;

@Path("/reviews")
public class ReviewResource {
	ReviewService reviewService = new ReviewService();
	Restaurant rest = new Restaurant();

	// get reviews
	@Path("view/{restaurant_name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Review> getReviews(@PathParam("restaurant_name") String restaurant_name) {
		System.out.println("I am in review resource!!" + restaurant_name);
		return reviewService.getReviews(restaurant_name);

	}

	// post review for a particular restaurant
	@Path("review_exist_update/{restaurant_name}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public Review addReview(Review rev) {
		System.out.println("I am in add review !!" + rev.getReview());

		return reviewService.addReview(rev);

	}

}
