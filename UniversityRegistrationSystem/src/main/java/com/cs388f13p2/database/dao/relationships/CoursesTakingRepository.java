package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.repository.Pair;
import com.cs388f13p2.database.repository.TwoIntKeyRelationshipRepository;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.person.Student;

public class CoursesTakingRepository implements TwoIntKeyRelationshipRepository<Student,CourseOffering> {
	private static class SingletonHolder { 
		public static final CoursesTakingRepository INSTANCE = new CoursesTakingRepository();
	}

	public static CoursesTakingRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CoursesTakingRepository() {
		
	}
	
	private void createCoursesTakingTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CoursesTaking(" +
				"studentId INT NOT NULL, " +
				"courseOfferingId INT NOT NULL, " +
				"PRIMARY KEY (studentId, courseOfferingId), " + 
				"FOREIGN KEY (studentId) references Student(id), " +
				"FOREIGN KEY (courseOfferingId) references CourseOffering(courseOfferingId) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(final DatabaseMetaData dbm, final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CoursesTaking", null);
		if (!tables.next()) {
			// Table does not exist
			createCoursesTakingTable(st);
		}
	}

	@Override
	public void add(final int studentId, final int courseId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
	}

	// return the number of courses taken by a student
	public int findNumberOfCoursesTakenByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return -1;
	}
	
	// return the number of students taking a course
	public int findNumberOfStudentsTakingCourse(final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return -1;
	}
	
	// returns true if exists in database
	public boolean contains(final int studentId, final int courseOfferingId) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}
	
	// return all students taking a particular course
	public Iterator<Student> getStudentsTakingCourse(final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return new ArrayList<Student>().iterator();
	}
	
	// return all courses taken by a particular student
	public Iterator<CourseOffering> getCoursesTakingByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return new ArrayList<CourseOffering>().iterator();
	}


	public Pair<Student,CourseOffering> findById(final int studentId, 
			final int courseOfferingId) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return new Pair<Student,CourseOffering>(null, null);
	}
	
	@Override
	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final int studentId, final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}
}
