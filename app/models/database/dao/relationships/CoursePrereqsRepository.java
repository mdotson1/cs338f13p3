package models.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import models.database.connection.DBHelper;
import models.course.Course;

public class CoursePrereqsRepository {
	private static class SingletonHolder { 
		public static final CoursePrereqsRepository INSTANCE =
                new CoursePrereqsRepository();
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

	private void databaseCreationCheck(DatabaseMetaData dbm, Statement st)
            throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CoursePrereqs",
                null);
		if (!tables.next()) {
			// Table does not exist
			createCoursePrereqsTable(st);
		}
	}

	public int add(final String department, final short courseNumber,
			final String prereqDepartment, final short prereqCourseNumber)
					throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final ResultSet CourseRes = st.executeQuery("SELECT department, " +
                "courseNumber FROM Course WHERE department = '" + department +
                "' AND courseNumber = " + courseNumber+ ";");

		if(!CourseRes.next()){

			String insertCoursePrereqQuery = "INSERT INTO CoursePrereqs(" +
                    "department, courseNumber, prereqDepartment, " +
                    "prereqCourseNumber) VALUES ('" + department + "', " +
                    courseNumber +", '"+ prereqDepartment + "', " +
                    prereqCourseNumber + ");";

			st.executeUpdate(insertCoursePrereqQuery, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("Creating Semesters " +
                        "prerequisite failed, no generated key obtained.");
			}
		}
		else 
			return -1;
	}

	// return an iterator of all prereqs for a section
	public Iterator<Course> findAllPrereqsForCourse(final String department,
			final short courseNumber) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCoursePrereqQuery = "SELECT  prereqDepartment, "+
				"prereqCourseNumber FROM CoursePrereqs"+
				"WHERE department = '"+ department + " AND courseNumber = "+ 
				courseNumber +";";

		final ResultSet CoursePrereqRes =
                st.executeQuery(SelectCoursePrereqQuery);

		final Collection<Course> courseList = new ArrayList<Course>();

		Course course = null;

		while(CoursePrereqRes.next()){
			final short courseNum = CoursePrereqRes.getShort("prereqCourseNumber");
			final String dep = CoursePrereqRes.getString("prereqDepartment");

			final String SelectCourseQuery = "SELECT department, " +
                    "courseNumber, cost, courseDescription FROM CoursePrereqs" +
                    " WHERE courseNumber = "+ courseNum +
                    " AND department = '" + dep + "';";

			final ResultSet CourseRes = st.executeQuery(SelectCourseQuery);

			while(CourseRes.next()){
				course = new Course(CourseRes.getString("department"),
                        CourseRes.getShort("courseNumber"),
						CourseRes.getDouble("cost"),
                        CourseRes.getString("courseDescription"));

				courseList.add(course);
			}
		}
		return courseList.iterator();
	}

	// return true if an entry was deleted or false if no entry deleted
	public boolean delete(final String department, final short courseNumber,
			final String prereqDepartment, final short prereqCourseNumber)
					throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String deletePrereqQuery = "DELETE FROM CoursePrereqs WHERE " +
                "department = '" + department + "' AND courseNumber = " +
                courseNumber + " AND " + "prereqDepartment = '" +
                prereqDepartment + "' AND " + "prereqCourseNumber = " +
                prereqCourseNumber + ";";

		return st.execute(deletePrereqQuery);
	}
}
