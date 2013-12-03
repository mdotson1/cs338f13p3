package models.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import models.course.Semester;
import models.database.connection.DBHelper;
import models.database.repository.ConcreteIntKeyRepository;
import models.course.Course;
import models.course.CourseOffering;
import models.course.Semester.Season;

public class CourseOfferingRepository
        implements ConcreteIntKeyRepository<CourseOffering>{

	private static class SingletonHolder {
		public static final CourseOfferingRepository INSTANCE =
                new CourseOfferingRepository();
	}

	public static CourseOfferingRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CourseOfferingRepository() {

	}

	private static void createCourseOfferingTable(final Statement st)
            throws SQLException {
		final String createTableStatement = "CREATE TABLE CourseOffering(" +
				"courseOfferingId INT NOT NULL AUTO_INCREMENT, " +
				"department VARCHAR(20) NOT NULL, " +
				"courseNumber smallint NOT NULL, " +
				"sectionNumber SMALLINT NOT NULL," +
				"season VARCHAR(6) NOT NULL," +
				"year SMALLINT NOT NULL," +
				"PRIMARY KEY (courseOfferingId), " +
				"FOREIGN KEY (department) references Course(department), " +
				"FOREIGN KEY (courseNumber) references Course(courseNumber), " +
                "FOREIGN KEY (season) references Semester(season), " +
                "FOREIGN KEY (year) references Semester(year)" +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}

	public static void databaseCreationCheck(DatabaseMetaData dbm, Statement st)
            throws SQLException {

		final ResultSet tables = dbm.getTables(null, null, "CourseOffering",
                null);

        CourseRepository.databaseCreationCheck(dbm, st);
        SemesterRepository.databaseCreationCheck(dbm, st);

		if (!tables.next()) {
			// Table does not exist
			createCourseOfferingTable(st);
		}

        tables.close();
	}

	public int add(final CourseOffering obj) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String insertCourseOfferingStatement = "INSERT INTO " +
                "CourseOffering (department, courseNumber, sectionNumber, " +
                "season, year) VALUES('" + obj.getCourse().getDepartment() +
                "', " + obj.getCourse().getCourseNumber() + ", " +
                obj.getSectionNumber() + ", '" + obj.getSemester().getSeason() +
                "', " + obj.getSemester().getYear() + ");";

		st.executeUpdate(insertCourseOfferingStatement,
                Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();

		if (rs.next()) {
            final int retval = rs.getInt(1);
            c.close();
            rs.close();
            st.close();
			return retval;
		} else {
            c.close();
            rs.close();
            st.close();
			throw new SQLException("Creating payment failed, no generated " +
                    "key obtained.");
		}

	}

    public CourseOffering find(final String department, final short courseNum,
                               final short sectionNum, final Season season,
                               final short year) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, sectionNumber, " +
                "sectionNumber, season, year FROM CourseOffering WHERE " +
                "department = '" + department + "' AND courseNumber = " +
                courseNum + " AND sectionNumber = " + sectionNum +
                " AND season = '" + season.toString() + "' AND year = " + year +
                ";";


        final ResultSet courseOffRes = st.executeQuery(SelectCourseOffQuery);

        CourseOffering courseOffering = null;

        while(courseOffRes.next()){
            final Course course = CourseRepository.getInstance()
                    .findById(courseOffRes.getString("department"),
                            courseOffRes.getShort("courseNumber"));

            final Semester sem = new Semester(Season.valueOf(
                    courseOffRes.getString("season")),
                    courseOffRes.getShort("year"));

            courseOffering = new CourseOffering(
                    courseOffRes.getShort("courseOfferingID"), course, sem,
                    courseOffRes.getShort("sectionNumber"));
        }
        c.close();
        courseOffRes.close();
        st.close();

        return courseOffering;
    }

    @Override
    public CourseOffering findById(final int id) throws SQLException {


        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, sectionNumber, " +
                "sectionNumber, season, year FROM CourseOffering WHERE " +
                "courseOfferingId = " + id + ";";


        final ResultSet courseOffRes = st.executeQuery(SelectCourseOffQuery);

        CourseOffering courseOffering = null;

        while(courseOffRes.next()){
            final Course course = CourseRepository.getInstance()
                    .findById(courseOffRes.getString("department"),
                            courseOffRes.getShort("courseNumber"));

            final Semester sem = new Semester(Season.valueOf(
                    courseOffRes.getString("season")),
                    courseOffRes.getShort("year"));

            courseOffering = new CourseOffering(
                    courseOffRes.getShort("courseOfferingID"), course, sem,
                    courseOffRes.getShort("sectionNumber"));
        }

        c.close();
        courseOffRes.close();
        st.close();

        return courseOffering;
    }

	public CourseOffering findBySectionSemester(final Season season,
                                                final short year,
                                                final String department,
                                                final short courseNum,
                                                final short sectionNum)
            throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String selectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, sectionNumber, " +
                "sectionNumber, season, year FROM CourseOffering WHERE " +
                "season = '" + season.toString() + "' AND year = " + year +
                " AND department = '" + department + "' AND courseNumber = " +
                courseNum + " AND sectionNumber = " + sectionNum + ";";


		final ResultSet courseOffRes = st.executeQuery(selectCourseOffQuery);

		CourseOffering courseOffering = null;

		while(courseOffRes.next()){
			final Course course = CourseRepository.getInstance()
					.findById(courseOffRes.getString("department"),
                            courseOffRes.getShort("courseNumber"));

            final Semester sem = new Semester(Season.valueOf(
                    courseOffRes.getString("season")),
                    courseOffRes.getShort("year"));

			courseOffering = new CourseOffering(
                    courseOffRes.getShort("courseOfferingId"), course, sem,
                    courseOffRes.getShort("sectionNumber"));
		}

        c.close();
        courseOffRes.close();
        st.close();

		return courseOffering;
	}

    @Override
	public boolean delete(final int id) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String deleteCourseOfferingQuery = "DELETE FROM CourseOffering " +
                "WHERE courseOfferingId = " + id +";";

        final boolean result = st.execute(deleteCourseOfferingQuery);

        c.close();
        st.close();

		return result;
	}

    public boolean contains(final String department, final short courseNum,
                            final short sectionNum, final Season season,
                            final short year) throws SQLException {
        if (findBySectionSemester(season, year, department, courseNum,
                sectionNum) == null) {
            return false;
        } else {
            return true;
        }
    }

	@Override
	public boolean contains(final int id) throws SQLException {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

    public Iterator<Course> findAllCoursesBySemester(
            final Season season, final short year) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseQuery = "SELECT department, courseNumber " +
                "FROM CourseOffering WHERE season = '"+ season.toString() +
                "' AND year = " + year +" GROUP BY department, courseNumber;";


        final ResultSet courseRes = st.executeQuery(selectCourseQuery);

        final Collection<Course> courseList = new ArrayList<Course>();

        while(courseRes.next()){
            final Course course = CourseRepository.getInstance()
                    .findById(courseRes.getString("department"),
                            courseRes.getShort("courseNumber"));

            courseList.add(course);
        }

        c.close();
        courseRes.close();
        st.close();

        return courseList.iterator();
    }

    public Iterator<CourseOffering> findAllSections(final Season season,
                                                    final short year,
                                                    final String department,
                                                    final short courseNum)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, season, year FROM " +
                "CourseOffering WHERE season = '" + season.toString() +
                "' AND year = " + year + " AND department = '" + department +
                "' AND courseNumber = " + courseNum + ";";


        final ResultSet courseOffRes = st.executeQuery(selectCourseOffQuery);

        final Collection<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();


        while(courseOffRes.next() ){
            final Course course = CourseRepository.getInstance()
                    .findById(courseOffRes.getString("department"),
                            courseOffRes.getShort("courseNumber"));

            final Semester sem = new Semester(Season.valueOf(
                    courseOffRes.getString("season")),
                    courseOffRes.getShort("year"));

            final CourseOffering courseOffering = new CourseOffering(
                    courseOffRes.getShort("courseOfferingID"), course, sem,
                    courseOffRes.getShort("sectionNumber"));

            courseOfferingList.add(courseOffering);
        }

        c.close();
        courseOffRes.close();
        st.close();

        return courseOfferingList.iterator();
    }

    public Iterator<Course> coursesBySemesterDepartment(final Season season,
                                                        final short year,
                                                        final String department)
        throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseQuery = "SELECT department, courseNumber " +
                "FROM CourseOffering WHERE season = '" + season.toString() +
                "' AND year = " + year + " AND department = '" + department +
                "' GROUP BY department, courseNumber;";

        final ResultSet courseRes = st.executeQuery(selectCourseQuery);

        final Collection<Course> courseList = new ArrayList<Course>();

        while(courseRes.next()) {
            final Course course = CourseRepository.getInstance()
                    .findById(courseRes.getString("department"),
                            courseRes.getShort("courseNumber"));

            courseList.add(course);
        }

        c.close();
        courseRes.close();
        st.close();

        return courseList.iterator();
    }

	public Iterator<CourseOffering> findAllCourseOfferingsBySemester(
            final Season season, final short year) throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String selectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, season, year FROM " +
                "CourseOffering WHERE season = '"+ season.toString() +
                "' AND year = " + year +";";


		final ResultSet courseOffRes = st.executeQuery(selectCourseOffQuery);

		final Collection<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();


		while(courseOffRes.next()){
			CourseOffering course = CourseOfferingRepository.getInstance()
					.findById(courseOffRes.getInt("courseOfferingId"));

			courseOfferingList.add(course);
		}

        c.close();
        courseOffRes.close();
        st.close();

		return courseOfferingList.iterator();
	}

    public Iterator<String> departmentsOfferingCoursesBySemester(
            final Season season, final short year) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseOffQuery = "SELECT department FROM " +
                "CourseOffering WHERE season = '"+ season.toString() +
                "' AND year = " + year + " GROUP BY department;";


        final ResultSet departmentRes = st.executeQuery(selectCourseOffQuery);

        final Collection<String> departmentList =
                new ArrayList<String>();

        while(departmentRes.next()){
            departmentList.add(departmentRes.getString("department"));
        }

        c.close();
        departmentRes.close();
        st.close();

        return departmentList.iterator();
    }

	@Override
	public Iterator<CourseOffering> getAll() throws SQLException {

		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String SelectCourseOffQuery = "SELECT courseOfferingId, " +
                "department, courseNumber, sectionNumber, season, year FROM " +
                "CourseOffering;";


		final ResultSet courseOffRes = st.executeQuery(SelectCourseOffQuery);

		final Collection<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();


		while(courseOffRes.next() ){
			final Course course = CourseRepository.getInstance()
					.findById(courseOffRes.getString("department"),
                            courseOffRes.getShort("courseNumber"));

            final Semester sem = new Semester(Season.valueOf(
                    courseOffRes.getString("season")),
                    courseOffRes.getShort("year"));

			final CourseOffering courseOffering = new CourseOffering(
                    courseOffRes.getShort("courseOfferingID"), course, sem,
                    courseOffRes.getShort("sectionNumber"));

			courseOfferingList.add(courseOffering);
		}

        c.close();
        courseOffRes.close();
        st.close();

		return courseOfferingList.iterator();
	}



}
