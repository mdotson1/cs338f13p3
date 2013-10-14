package com.cs388f13p2.database.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {

	public static final String DATABASE_NAME = "dotson_tchassem_v0_0_1";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "secret";

	// creates the database, since it does not exist yet
	private static Connection createDatabase(Connection c) {
		
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306", USERNAME, PASSWORD);
			
			Statement st = c.createStatement();
			
			st.execute("CREATE DATABASE " + DATABASE_NAME);
			
			st.execute("USE " + DATABASE_NAME);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("DBHelper: Check Where  your mySQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;

		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			// happens when databse doesn't exist yet.
			if (e.getErrorCode() == 1049) {
				connection = createDatabase(connection);
				return connection;
			} else {
				System.out.println("DBHelper: Connection Failed! Check output console");
				e.printStackTrace();
				return null;
			}
		}

		return connection;
	}
}
