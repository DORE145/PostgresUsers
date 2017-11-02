package com.dore.postgesUsers.utilits;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dore.postgesUsers.beans.User;

public class DBUtils {
	
	public static List<User> queryUsers(Connection connection) throws SQLException {
		String sql = "select * from public.\"Users\";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();  //Gathering data from table
		List<User> list = new ArrayList<User>();				
		while (resultSet.next()) {
			int id = resultSet.getInt("Id");			// Gathering record's fields
			String name = resultSet.getString("Name");
			String surname = resultSet.getString("Surname");
			String login = resultSet.getString("Login");
			String password = resultSet.getString("Password");
			Date birthday = resultSet.getDate("Birthday");
			User user = new User(); 				// Creating and filling User object
			user.setId(id);
			user.setName(name);
			user.setSurname(surname);
			user.setLogin(login);
			user.setPassword(password);
			user.setBirthday(birthday);
			list.add(user);
		}
		return list;
	}
	
	public static User findUser(Connection connection, int id) throws SQLException {
		String sql = "SELECT * FROM public.\"Users\" where \"Id\"=?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();	//Gathering data from table
		
		while (resultSet.next()) {
			int i = resultSet.getInt("Id");			// Gathering record's fields
			String name = resultSet.getString("Name");
			String surname = resultSet.getString("Surname");
			String login = resultSet.getString("Login");
			String password = resultSet.getString("Password");
			Date birthday = resultSet.getDate("Birthday");
			User user = new User(); 				// Creating and filling User object
			user.setId(i);
			user.setName(name);
			user.setSurname(surname);
			user.setLogin(login);
			user.setPassword(password);
			user.setBirthday(birthday);
			return user;
		}
		return null;
	}

	public static void updateUser(Connection connection, User user) throws SQLException {
		String sql = "UPDATE public.\"Users\" set \"Name\" = ?, \"Surname\" = ?, \"Login\" = ?, "
				+ "\"Password\" = ?, \"Birthday\" = ? WHERE \"Id\" = ?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getName());		//Filling statement with arguments
		statement.setString(2,user.getSurname());
		statement.setString(3, user.getLogin());
		statement.setString(4, user.getPassword());
		statement.setDate(5, new Date(user.getBirthday().getTime()));
		statement.setInt(6, user.getId());
		
		statement.executeUpdate();
	}
	
	public static void insertUser(Connection connection, User user) throws SQLException {
		String sql = "INSERT INTO public.\"Users\" (\"Name\", \"Surname\", \"Login\", \"Password\", \"Birthday\") VALUES (?, ?, ?, ?, ?);";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getName());		//Filling statement with arguments
		statement.setString(2,user.getSurname());
		statement.setString(3, user.getLogin());
		statement.setString(4, user.getPassword());
		statement.setDate(5, new Date(user.getBirthday().getTime()));
		
		statement.executeUpdate();
	}
	
	public static void deleteUser(Connection connection, int id) throws SQLException {
		String sql = "DELETE FROM public.\"Users\" where \"Id\"=?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		
		statement.executeUpdate();
	}
}