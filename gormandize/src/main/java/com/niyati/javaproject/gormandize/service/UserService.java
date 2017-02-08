package com.niyati.javaproject.gormandize.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.niyati.javaproject.gormandize.model.User;

public class UserService {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	ArrayList<String> loginDetails = new ArrayList<String>();

	public User addUser(User user) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();
			// String query="INSERT INTO
			// users(firstname,lastname,username,email,password) VALUES
			// (?,?,?,?,?)";
			String query = "insert into users (firstname,lastname,username,email,password)" + " values ('"
					+ user.getFirstname() + "','" + user.getLastname() + "','" + user.getUsername() + "','"
					+ user.getEmail() + "','" + user.getPassword() + "')";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			/*
			 * preparedStatement.setString (1, user.getFirstname());
			 * preparedStatement.setString(2, user.getLastname());
			 * preparedStatement.setString(3, user.getUsername());
			 * preparedStatement.setString(4, user.getEmail());
			 * preparedStatement.setString(5, user.getPassword());
			 */

			preparedStatement.execute(query);

			
			System.out.println("User registered successfully");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
		return user;

	}

	public String getUser(String username, String password) {
		User loginuser = new User();

		try {

			loginuser.setUsername(username);
			loginuser.setPassword(password);
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gormandize?" + "user=root&password=niyati&useSSL=false");
			conn.setSchema("gormandize");
			stmt = conn.createStatement();
			String loginquery = "select * from users where username='" + username + "'and password='" + password + "'";
			PreparedStatement preparedStatement = conn.prepareStatement(loginquery);
			rs = preparedStatement.executeQuery(loginquery);
			// System.out.println("Hi these are login details:
			// "+loginuser.getUsername()+"..."+loginuser.getPassword());
			// while (rs.next()) {
			// System.out.println("talking from the resultset: "+
			// rs.getString("username"));
			if (!rs.next()) {
				System.out.println("invalid user");
				return "invalid user";
			} else {
				System.out.println("valid user");
				System.out.println("Welcome " + rs.getString("username") + "!!");
				return rs.getString("username");

			}
			// }

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
