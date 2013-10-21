package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.dao.concrete.CourseOfferingRepository;
import com.cs388f13p2.database.dao.concrete.ProfessorRepository;
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
				"PRIMARY KEY (professorId, courseOfferingId), "  + 
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
		
		String insertCourseTeachingQuery = "INSERT INTO CoursesTeaching(professorId, " +
											"courseOfferingId) VALUES ("+ professorId + ", " + 
											courseOfferingId + ");";

		st.executeUpdate(insertCourseTeachingQuery, Statement.RETURN_GENERATED_KEYS);
		
	}

	// return all courses taught by professor
	public Iterator<CourseOffering> findAllCoursesTaughtByProfessor(final int professorId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTeachingQuery = "SELECT  courseOfferingId "+ 
												"FROM CoursesTeaching "+
												"WHERE studentId = "+ professorId + ";";

		final ResultSet CourseTeachingRes = st.executeQuery(SelectCourseTeachingQuery);

		final List<CourseOffering> courseOfferingList = new ArrayList<CourseOffering>();

		while(CourseTeachingRes.next()){
			courseOfferingList.add(CourseOfferingRepository.
					getInstance().findById(CourseTeachingRes.getInt("courseOfferingId")));
		}

		// TODO marcellin
		return courseOfferingList.iterator();
	}
	
	// return the professor for the course
	public Professor findProfessorForCourse(final int courseOfferingId) throws SQLException {
		Professor professor = null;
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTeachingQuery = "SELECT professorId "+ 
												"FROM CoursesTeaching "+
												"WHERE courseOfferingId = "+ courseOfferingId + ";";


		final ResultSet CourseTeachingRes = st.executeQuery(SelectCourseTeachingQuery);

	

		while(CourseTeachingRes.next()){
			professor = ProfessorRepository.getInstance().findById(CourseTeachingRes.getInt("professorId"));
		}
		
		// TODO marcellin*/
		return professor;
	}

	
	@Override
	// returns true if something was deleted
	public boolean delete(int professorId, int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String deleteCourseTeachingQuery = "DELETE FROM CoursesTeaching "+
												"WHERE professorId = "+ professorId + 
												" AND courseOfferingId = "+ courseOfferingId + ";";

		return st.execute(deleteCourseTeachingQuery);
		
		// TODO marcellin
		
	}

}
