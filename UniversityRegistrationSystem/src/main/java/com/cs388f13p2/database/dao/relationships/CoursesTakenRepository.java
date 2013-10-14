package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.model.course.Course;

public class CoursesTakenRepository {

	private static class SingletonHolder { 
		public static final CoursesTakenRepository INSTANCE = new CoursesTakenRepository();
	}

	public static CoursesTakenRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CoursesTakenRepository() {
		
	}
	
	private void createCoursesTakenTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CoursesTaken(" +
				"studentId INT NOT NULL, " +
				"department VARCHAR(20) NOT NULL," +
				"courseNumber SMALLINT NOT NULL," +
				"PRIMARY KEY (studentId, department, courseNumber), " + 
				"FOREIGN KEY (studentId) references Student(id), " +
				"FOREIGN KEY (department) references Course(department), " + 
				"FOREIGN KEY (courseNumber) references Course(courseNumber) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(DatabaseMetaData dbm, Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CoursesTaken", null);
		if (!tables.next()) {
			// Table does not exist
			createCoursesTakenTable(st);
		}
	}
	
	public void add(int studentId, final String department,
			final short courseNumber) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
	}
	
	// return the courses taken by the student
	public Iterator<Course> findAllCoursesTakenByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}

	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final int studentId, final String department,
			final short courseNumber) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}
}
