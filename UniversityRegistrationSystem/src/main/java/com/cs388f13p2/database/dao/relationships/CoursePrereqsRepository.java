package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.model.course.Course;

public class CoursePrereqsRepository {
	private static class SingletonHolder { 
		public static final CoursePrereqsRepository INSTANCE = new CoursePrereqsRepository();
	}

	public static CoursePrereqsRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CoursePrereqsRepository() {
		
	}
	
	private void createCoursePrereqsTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CoursePrereqs(" +
				"department VARCHAR(20) NOT NULL, " +
				"courseNumber SMALLINT NOT NULL, " +
				"prereqDepartment VARCHAR(20) NOT NULL," +
				"prereqCourseNumber SMALLINT NOT NULL," +
				"PRIMARY KEY (department, courseNumber, prereqDepartment, prereqCourseNumber), " + 
				"FOREIGN KEY (department) references Course(department), " +
				"FOREIGN KEY (courseNumber) references Course(courseNumber), " + 
				"FOREIGN KEY (prereqDepartment) references Course(department), " +
				"FOREIGN KEY (prereqCourseNumber) references Course(courseNumber) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(DatabaseMetaData dbm, Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CoursePrereqs", null);
		if (!tables.next()) {
			// Table does not exist
			createCoursePrereqsTable(st);
		}
	}

	public void add(final String department, final short courseNumber,
			final String prereqDepartment, final short prereqCourseNumber)
					throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
	}
	
	// return an iterator of all prereqs for a course
	public Iterator<Course> findAllPrereqsForCourse(final String department,
			final short courseNumber) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}

	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final String department, final short courseNumber,
			final String prereqDepartment, final short prereqCourseNumber)
					throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}
}
