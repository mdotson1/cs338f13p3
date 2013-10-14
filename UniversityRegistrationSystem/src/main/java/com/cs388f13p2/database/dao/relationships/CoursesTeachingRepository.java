package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.repository.TwoIntKeyRelationshipRepository;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.person.Professor;

public class CoursesTeachingRepository implements TwoIntKeyRelationshipRepository<Professor,CourseOffering> {

	private static class SingletonHolder { 
		public static final CoursesTeachingRepository INSTANCE = new CoursesTeachingRepository();
	}

	public static CoursesTeachingRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CoursesTeachingRepository() {
		
	}
	
	private void createCoursesTeachingTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CoursesTeaching(" +
				"professorId INT NOT NULL, " +
				"courseOfferingId INT NOT NULL, " +
				"PRIMARY KEY (professorId, courseOfferingId), " + 
				"FOREIGN KEY (professorId) references Professor(id), " +
				"FOREIGN KEY (courseOfferingId) references CourseOffering(courseOfferingId) " + 
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(DatabaseMetaData dbm, Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CoursesTeaching", null);
		if (!tables.next()) {
			// Table does not exist
			createCoursesTeachingTable(st);
		}
	}
	
	@Override
	public void add(final int professorId, final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
	}

	// return all courses taught by professor
	public Iterator<CourseOffering> findAllCoursesTaughtByProfessor(final int professorId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}
	
	// return the professor for the course
	public Professor findProfessorForCourse(final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}

	@Override
	// returns true if something was deleted
	public boolean delete(int firstId, int secondId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}

}
