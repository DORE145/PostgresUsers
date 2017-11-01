package com.dore.postgesUsers.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnection {

	public static Connection getPostgesConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost:5433";
		String dbName = "mydb";
		String userName = "testUser";
		String password = "123456";
		return getPostgesConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getPostgesConnection(String hostName, String dbName, String user, String password) throws ClassNotFoundException, SQLException {		
		Class.forName("org.postgresql.Driver");		//Initializing JDBC Driver
		String url = "jdbc:postgresql://" + hostName + "/" + dbName; //Database adders construction
		Properties properties = new Properties(); // Setting user and password for database connection
		properties.setProperty("user", user);
		properties.setProperty("password", password);
		Connection connection = DriverManager.getConnection(url, properties); // Gathering connection
		return connection;
	}
}
