package models.database.dao.concrete;

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
import models.person.ContactInformation;
import models.person.Student;

public class CourseRepository {

	private static class SingletonHolder { 
		public static final CourseRepository INSTANCE = new CourseRepository();
	}

	public static CourseRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CourseRepository() {

	}

	private void createCourseTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE Course(" +
				"department VARCHAR(20) NOT NULL, " +
				"courseNumber smallint NOT NULL, " +
				"cost DOUBLE NOT NULL, " +
				"courseDescription VARCHAR(100) NOT NULL, " +
				"PRIMARY KEY (department, courseNumber), " +
				"INDEX (courseNumber)" +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}

	private void databaseCreationCheck(final DatabaseMetaData dbm, 
			final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "Course", null);
		if (!tables.next()) {
			// Table does not exist
			createCourseTable(st);
		}
	}

    public Iterator<Course> getAll() throws SQLException {
        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCoursesQuery = "SELECT department, courseNumber, " +
                "cost, courseDescription FROM Course;";

        final ResultSet courseRS = st.executeQuery(selectCoursesQuery);

        final List<Course> courseList = new ArrayList<Course>();

        Course course = null;

        while ( courseRS.next() ) {

            course = new Course(courseRS.getString("department"),
                    courseRS.getShort("courseNumber"),courseRS.getShort("cost"),
                    courseRS.getString("courseDescription"));

            courseList.add(course);
        }
        return courseList.iterator();
    }

	public void add(final Course obj) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String insertStudentStatement = "INSERT INTO Course(department, courseNumber, " +
				"cost, courseDescription)" + " VALUES('" + obj.getDepartment() + "', " + 
				obj.getCourseNumber() + ", " + obj.getCost() + ", '" + 
				obj.getCourseDescription() + "');";

		st.execute(insertStudentStatement);
	}


	public Course findById(final String department, final short courseNumber)
			throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCourseQuery = "SELECT department, courseNumber, cost, courseDescription " +
				"FROM Course WHERE courseNumber = "+ courseNumber + 
				" AND department = '"+ department + "';";

		final ResultSet CourseRes = st.executeQuery(SelectCourseQuery);

		Course course = null;

		while(CourseRes.next()){
			course = new Course(CourseRes.getString("department"), CourseRes.getShort("courseNumber"),
					CourseRes.getDouble("cost"), CourseRes.getString("courseDescription"));
		}
		return course;
	}

	public boolean delete(final String department, final short courseNumber)
			throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String deleteCourseQuery = "DELETE FROM Course WHERE courseNumber = "+ courseNumber + 
				" AND department = '"+ department + "';";

		return st.execute(deleteCourseQuery);

	}
}
