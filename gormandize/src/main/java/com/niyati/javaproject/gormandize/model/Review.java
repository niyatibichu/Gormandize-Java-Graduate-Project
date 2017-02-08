package com.niyati.javaproject.gormandize.model;

public class Review {
	private int review_id;
	private int user_id;
	private String username;
	private int restaurant_id;
	// private String restaurant_name;
	private String review;
	private int rating;
	private int average_rating;

	public int getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(int average_rating) {
		this.average_rating = average_rating;
	}

	public Review(int review_id, int user_id, int restaurant_id, String review, int rating) {
		this.review_id = review_id;
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.review = review;
		this.rating = rating;
	}

	public Review() {

	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * public String getRestaurant_name() { return restaurant_name; }
	 * 
	 * public void setRestaurant_name(String restaurant_name) {
	 * this.restaurant_name = restaurant_name; }
	 */

}
