package com.cs388f13p2.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	public static Connection getConnection() {
		 
		System.out.println("DBHelper: -------- mySQL " + "JDBC Connection  ------------");
 
		try {
 
			Class.forName("com.mysql.jdbc.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("DBHelper: Check Where  your mySQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: mySQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "secret");
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
	            }
 
		} catch (SQLException e) {
 
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: You have a database connection!");
		
		return connection;
	}
}
