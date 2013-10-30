package models.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.database.connection.DBHelper;
import models.course.Course;

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
	
	public int add(int studentId, final String department,
			final short courseNumber) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final ResultSet CourseRes = st.executeQuery("SELECT department, courseNumber FROM course WHERE "
				+ "department =	'"+department+ "' AND courseNumber = "
						+ courseNumber+ ";");
		
		if(!CourseRes.next()){
		
		String insertCourseTakenQuery = "INSERT INTO CoursesTaken(studentId, department, "+
										"courseNumber) VALUES ("+ studentId +", '"+department + "', " + 
										courseNumber + ");";

		st.executeUpdate(insertCourseTakenQuery, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Creating Course taken failed, no generated key obtained.");
		}}
		else return -1;

		// TODO marcellin
	}
	
	// return the courses taken by the student
	public Iterator<Course> findAllCoursesTakenByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseTakenQuery = "SELECT  department,"+
												"courseNumber FROM CourseTaken"+
												"WHERE studentId = "+ studentId + ";";


		final ResultSet CourseTakenRes = st.executeQuery(SelectCourseTakenQuery);

		final List<Course> courseList = new ArrayList<Course>();

		Course course = null;

		while(CourseTakenRes.next()){
			final short courseNum = CourseTakenRes.getShort("courseNumber");
			final String department = CourseTakenRes.getString("department");

			final String SelectCourseQuery = "SELECT department, courseNumber, cost, courseDescription " +
											"FROM Course WHERE courseNumber = "+ courseNum + 
											" AND department = '"+ department + "';";

			final ResultSet CourseRes = st.executeQuery(SelectCourseQuery);

			while(CourseRes.next()){
				course = new Course(CourseRes.getString("department"), CourseRes.getShort("courseNumber"),
						CourseRes.getDouble("cost"), CourseRes.getString("courseDescription"));

				courseList.add(course);

			}
		}
		return courseList.iterator();
	}

	
	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final int studentId, final String department,
			final short courseNumber) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String deleteCourseTakenQuery = "DELETE FROM CourseTaken WHERE studentId = "+ studentId +
											" AND department = '"+ department + "' AND courseNumber = "+ 
												courseNumber + ";";

		return st.execute(deleteCourseTakenQuery);
		// TODO marcellin
		
	}
}
