package com.niyati.javaproject.gormandize.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.Math;

import com.niyati.javaproject.gormandize.model.Review;

public class ReviewService {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ArrayList<Review> getReviews(String restaurant_name) {

		ArrayList<Review> restaurantsList = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();
			String viewReviewquery = "select r.review,.r.rating,u.username from reviews r,users u,restaurants t where "
					+ "(r.user_id=u.user_id and r.restaurant_id=t.restaurant_id and t.restaurant_name='"
					+ restaurant_name + "')";

			PreparedStatement preparedStatement = conn.prepareStatement(viewReviewquery);
			rs = preparedStatement.executeQuery(viewReviewquery);

			while (rs.next()) {
				Review reviewObj = new Review();

				reviewObj.setUsername(rs.getString("username"));
				reviewObj.setReview(rs.getString("review"));
				reviewObj.setRating(rs.getInt("rating"));
				restaurantsList.add(reviewObj);
				System.out.println(reviewObj.getReview());
				System.out.println(reviewObj.getUsername());
				System.out.println(reviewObj.getRating());
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

	public Review addReview(Review rev) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();

			// query to get user_id
			String getUserId = "select user_id from users where username='" + rev.getUsername() + "'";
			PreparedStatement ps1 = conn.prepareStatement(getUserId);
			ResultSet rs1 = ps1.executeQuery(getUserId);
			while (rs1.next()) {
				System.out.println("The userid is: " + rs1.getInt("user_id"));
				rev.setUser_id(rs1.getInt("user_id"));
			}

			// query to get restaurant rating
			String getRestaurantRating = "select average_rating from restaurants where restaurant_id='"
					+ rev.getRestaurant_id() + "'";
			PreparedStatement ps2 = conn.prepareStatement(getRestaurantRating);
			ResultSet rs2 = ps2.executeQuery(getRestaurantRating);
			while (rs2.next()) {
				System.out.println("The average rest rating is: " + rs2.getInt("average_rating"));
				rev.setAverage_rating(rs2.getInt("average_rating"));
			}

			// query to insert review
			String query1 = "insert into reviews (user_id,restaurant_id,review,rating)" + " values ('"
					+ rev.getUser_id() + "','" + rev.getRestaurant_id() + "','" + rev.getReview() + "','"
					+ rev.getRating() + "')";
			PreparedStatement preparedStatement = conn.prepareStatement(query1);
			preparedStatement.execute(query1);
			System.out.println("Review added successfully");

			int count = 0;
			// get count from rest
			String selectCount = "select count from restaurants where restaurant_id='" + rev.getRestaurant_id() + "'";
			PreparedStatement sc = conn.prepareStatement(selectCount);
			ResultSet rsCount = sc.executeQuery(selectCount);
			while (rsCount.next()) {
				count = rsCount.getInt("count");
			}

			int average = (int) Math.floor(((rev.getAverage_rating() * count) + rev.getRating()) / (count + 1));

			// query to update avg_rating
			String update_query = "update restaurants set average_rating='" + average + "'" + "where restaurant_id='"
					+ rev.getRestaurant_id() + "'";
			PreparedStatement pst = conn.prepareStatement(update_query);
			pst.executeUpdate(update_query);
			System.out.println("Rest avg rating updated successfully!!");

			int updatedCount = count + 1;
			// query to update count
			String upCount = "update restaurants set count='" + updatedCount + "'" + "where restaurant_id='"
					+ rev.getRestaurant_id() + "'";
			PreparedStatement pst1 = conn.prepareStatement(upCount);
			pst1.executeUpdate(upCount);
			System.out.println("Count updated successfully!!");

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return rev;
	}

}
