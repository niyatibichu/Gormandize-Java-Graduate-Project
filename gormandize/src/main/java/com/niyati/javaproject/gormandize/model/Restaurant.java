package com.niyati.javaproject.gormandize.model;

public class Restaurant {
	private int restaurant_id;
	private String restaurant_name;
	private int average_rating;
	private String username;
	private int user_id;
	private String review;

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Restaurant(int restaurant_id, String restaurant_name, int average_rating) {
		this.restaurant_id = restaurant_id;
		this.restaurant_name = restaurant_name;
		this.average_rating = average_rating;
	}

	public Restaurant(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public Restaurant() {
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public int getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(int average_rating) {
		this.average_rating = average_rating;
	}

}
