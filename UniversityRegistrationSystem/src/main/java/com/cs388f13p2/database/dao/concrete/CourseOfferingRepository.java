package com.cs388f13p2.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.repository.ConcreteIntKeyRepository;
import com.cs388f13p2.model.course.Course;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;

public class CourseOfferingRepository implements ConcreteIntKeyRepository<CourseOffering> {

	private static class SingletonHolder { 
		public static final CourseOfferingRepository INSTANCE = new CourseOfferingRepository();
	}

	public static CourseOfferingRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CourseOfferingRepository() {

	}

	private void createCourseOfferingTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CourseOffering(" +
				"courseOfferingId INT NOT NULL AUTO_INCREMENT, " +
				"department VARCHAR(20) NOT NULL, " +
				"courseNumber smallint NOT NULL, " +
				"sectionNumber SMALLINT NOT NULL," +
				"season VARCHAR(6) NOT NULL," +
				"year SMALLINT NOT NULL," +
				"PRIMARY KEY (courseOfferingId), " + 
				"FOREIGN KEY (department) references Course(department), " +
				"FOREIGN KEY (courseNumber) references Course(courseNumber)" + 
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}

	private void databaseCreationCheck(DatabaseMetaData dbm, Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "CourseOffering", null);
		if (!tables.next()) {
			// Table does not exist
			createCourseOfferingTable(st);
		}
	}

	@Override
	public int add(final CourseOffering obj) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String insertCourseOfferingStatement = "INSERT INTO CourseOffering (department," +
				" courseNumber, sectionNumber, season, year) VALUES('" + 
				obj.getCourse().getDepartment() + "', " + obj.getCourse().getCourseNumber() + 
				", " + obj.getSectionNumber() + ", '" + obj.getSeason() + "', " + obj.getYear() + ");";

		st.executeUpdate(insertCourseOfferingStatement, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Creating Payment failed, no generated key obtained.");
		}

	}

	@Override
	public CourseOffering findById(final int id) throws SQLException {


		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCourseOffQuery = "SELECT courseOfferingId, department, courseNumber, sectionNumber, "+
				"sectionNumber, sectionNumber, season, year FROM CourseOffering "+
				"WHERE courseOfferingId = " + id + ";";


		final ResultSet CourseOffRes = st.executeQuery(SelectCourseOffQuery);

		CourseOffering courseOffering = null;

		while(CourseOffRes.next()){
			Course course = CourseRepository.getInstance()
					.findById(CourseOffRes.getString("department"), CourseOffRes.getShort("courseNumber"));

			courseOffering = new CourseOffering(CourseOffRes.getShort("courseOfferingID"),
					course, Season.valueOf(CourseOffRes.getString("season")), 
					CourseOffRes.getShort("year"), CourseOffRes.getShort("sectionNumber"));
		}
		return courseOffering;
	}


	@Override
	public boolean delete(final int id) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String deleteCourseOfferingQuery = "DELETE FROM CourseOffering WHERE courseOfferingId = " + id +";";

		return st.execute(deleteCourseOfferingQuery);
	}

	@Override
	public boolean contains(final int id) throws SQLException {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	public Iterator<CourseOffering> findAllCoursesBySemester(String season,
			short year) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCourseOffQuery = "SELECT courseOfferingID, department, courseNumber, sectionNumber, "+
				"sectionNumber, sectionNumber, season, year FROM CourseOffering "+
				"WHERE season = '"+ season + "' AND year = "+ year +";";


		final ResultSet CourseOffRes = st.executeQuery(SelectCourseOffQuery);

		final List<CourseOffering> courseOfferingList = new ArrayList<CourseOffering>();

		CourseOffering courseOffering = null;

		while(CourseOffRes.next()){
			Course course = CourseRepository.getInstance()
					.findById(CourseOffRes.getString("department"), CourseOffRes.getShort("courseNum"));

			courseOffering = new CourseOffering(CourseOffRes.getShort("courseOfferingID"),
					course, Season.valueOf(CourseOffRes.getString("season")), 
					CourseOffRes.getShort("year"), CourseOffRes.getShort("section"));

			courseOfferingList.add(courseOffering);
		}

		return courseOfferingList.iterator();
	}


	@Override
	public Iterator<CourseOffering> getAll() throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCourseOffQuery = "SELECT courseOfferingID, department, courseNumber, sectionNumber, "+
				" season, year FROM CourseOffering;";


		final ResultSet CourseOffRes = st.executeQuery(SelectCourseOffQuery);

		final List<CourseOffering> courseOfferingList = new ArrayList<CourseOffering>();

		CourseOffering courseOffering = null;


		while(CourseOffRes.next() ){
			Course course = CourseRepository.getInstance()
					.findById(CourseOffRes.getString("department"), CourseOffRes.getShort("courseNumber"));

			courseOffering = new CourseOffering(CourseOffRes.getShort("courseOfferingID"),
					course, Season.valueOf(CourseOffRes.getString("season")), 
					CourseOffRes.getShort("year"), CourseOffRes.getShort("sectionNumber"));

			courseOfferingList.add(courseOffering);
		}

		return  courseOfferingList.iterator();
	}



}
