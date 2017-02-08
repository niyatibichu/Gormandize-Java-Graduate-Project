package com.niyati.javaproject.gormandize.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.niyati.javaproject.gormandize.model.Restaurant;

public class RestaurantService {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ArrayList<Restaurant> getAllRestaurants() {
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();
			String restaurantquery = "select * from restaurants";
			PreparedStatement preparedStatement = conn.prepareStatement(restaurantquery);
			rs = preparedStatement.executeQuery(restaurantquery);

			while (rs.next()) {
				Restaurant restObj = new Restaurant();
				restObj.setRestaurant_id(rs.getInt("restaurant_id"));
				restObj.setRestaurant_name(rs.getString("restaurant_name"));
				restObj.setAverage_rating(rs.getInt("average_rating"));
				restaurantsList.add(restObj);
				/*
				 * System.out.println(restObj.getRestaurant_id());
				 * System.out.println(restObj.getRestaurant_name());
				 * System.out.println(restObj.getAverage_rating());
				 */
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return restaurantsList;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();
			int count = 1;
			// add to restaurants
			String query = "insert into restaurants (restaurant_name,average_rating,count)" + " values ('"
					+ restaurant.getRestaurant_name() + "','" + restaurant.getAverage_rating() + "','" + count + "')";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.execute(query);
			System.out.println("Restaurant added successfully");

			// query to get user_id
			String getUserId = "select user_id from users where username='" + restaurant.getUsername() + "'";
			PreparedStatement ps1 = conn.prepareStatement(getUserId);
			ResultSet rs1 = ps1.executeQuery(getUserId);
			while (rs1.next()) {
				System.out.println("The userid is: " + rs1.getInt("user_id"));
				restaurant.setUser_id(rs1.getInt("user_id"));
			}

			// query to get restaurant id
			String getRestaurantId = "select restaurant_id from restaurants where restaurant_name='"
					+ restaurant.getRestaurant_name() + "'";
			PreparedStatement ps2 = conn.prepareStatement(getRestaurantId);
			ResultSet rs2 = ps2.executeQuery(getRestaurantId);
			while (rs2.next()) {
				System.out.println("The rest id is: " + rs2.getInt("restaurant_id"));
				restaurant.setRestaurant_id(rs2.getInt("restaurant_id"));
			}

			// also update reviews table by inserting a review for that
			// restaurant
			String reviewInsert = "insert into reviews(user_id,restaurant_id,review,rating) values ('"
					+ restaurant.getUser_id() + "','" + restaurant.getRestaurant_id() + "','" + restaurant.getReview()
					+ "','" + restaurant.getAverage_rating() + "')";
			PreparedStatement ps3 = conn.prepareStatement(reviewInsert);
			ps3.execute(reviewInsert);
			System.out.println("Review has been inserted successfully");

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return restaurant;
	}

}
