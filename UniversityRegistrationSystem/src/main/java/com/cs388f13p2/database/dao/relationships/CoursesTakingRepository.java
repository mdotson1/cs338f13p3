package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.dao.concrete.CourseOfferingRepository;
import com.cs388f13p2.database.dao.concrete.StudentRepository;
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
		
		String insertCourseTakingQuery = "INSERT INTO CoursesTaking(studentId, "+
										"courseOfferingId) VALUES ("+ studentId +", " + 
										courseId + ");";

		st.executeUpdate(insertCourseTakingQuery, Statement.RETURN_GENERATED_KEYS);
		
		
		// TODO marcellin
	}

	// return the number of courses taken by a student
	public int findNumberOfCoursesTakenByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakingQuery = "SELECT courseOfferingId "+ 
												"FROM CoursesTaking "+
												"WHERE studentId = "+ studentId + ";";


		final ResultSet CourseTakingRes = st.executeQuery(SelectCourseTakingQuery);

		int courseNum = 0;

		while(CourseTakingRes.next())
			courseNum++;
		
		return courseNum;
	}
	
	// return the number of students taking a course
	public int findNumberOfStudentsTakingCourse(final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakingQuery = "SELECT  studentId"+ 
												"FROM CoursesTaking"+
												"WHERE courseOfferingId = '"+ courseOfferingId + "';";


		final ResultSet CourseTakingRes = st.executeQuery(SelectCourseTakingQuery);

		int studentNum = 0;

		while(CourseTakingRes.next())
			studentNum++;

// TODO marcellin
		return studentNum;
		
		
	}
	
	// returns true if exists in database
	public boolean contains(final int studentId, final int courseOfferingId) throws SQLException {
		
		return (!findById(studentId, courseOfferingId).compare(null, null));
		
		// TODO marcellin
	}
	
	// return all students taking a particular course
	public Iterator<Student> getStudentsTakingCourse(final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakingQuery = "SELECT studentId "+ 
												"FROM CoursesTaking "+
												"WHERE courseOfferingId = " + courseOfferingId + ";";


		final ResultSet CourseTakingRes = st.executeQuery(SelectCourseTakingQuery);
		
		final List<Student> studentList = new ArrayList<Student>();
		
		studentList.add(StudentRepository.getInstance().findById(CourseTakingRes.getInt("studentId")));

		return studentList.iterator();
	}
	
	// return all courses taken by a particular student
	public Iterator<CourseOffering> getCoursesTakingByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakingQuery = "SELECT courseOfferingId "+ 
												"FROM CoursesTaking "+
												"WHERE studentId = "+ studentId + ";";

		final ResultSet CourseTakingRes = st.executeQuery(SelectCourseTakingQuery);
		
		final List<CourseOffering> courseOfferingList = new ArrayList<CourseOffering>();
		
		while(CourseTakingRes.next()){
			courseOfferingList.add(CourseOfferingRepository.
					getInstance().findById(CourseTakingRes.getInt("courseOfferingId")));
		}
		
		// TODO marcellin
		return courseOfferingList.iterator();
	}


	public Pair<Student,CourseOffering> findById(final int studentId, 
			final int courseOfferingId) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakingQuery = "SELECT studentId, courseOfferingId "+ 
												"FROM CoursesTaking "+
												"WHERE studentId = "+ studentId + " AND courseOfferingId = "+
												courseOfferingId + ";";

		final ResultSet CourseTakingRes = st.executeQuery(SelectCourseTakingQuery);

		final Pair<Student,CourseOffering> pair = new Pair<Student, CourseOffering>(null, null);

		while(CourseTakingRes.next()){
			pair.setFirst(StudentRepository.getInstance().findById(CourseTakingRes.getInt("studentId")));
			pair.setSecond(CourseOfferingRepository.
					getInstance().findById(CourseTakingRes.getInt("courseOfferingId")));
		}
		return pair;
	}
	
	@Override
	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final int studentId, final int courseOfferingId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String deleteCourseTakingQuery = "DELETE FROM CoursesTaking"+
												"WHERE studentId = '"+ studentId + "' AND courseOfferingId = '"+
												courseOfferingId + "';";
		
		return st.execute(deleteCourseTakingQuery);
		
		// TODO marcellin
		
	}
}
