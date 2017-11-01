package com.dore.postgesUsers.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return PostgresConnection.getPostgesConnection();
	}
	
	public static void closeQuietly(Connection connection) {
		try {
			connection.close();
		} catch (Exception ignored) {}
	}
}
