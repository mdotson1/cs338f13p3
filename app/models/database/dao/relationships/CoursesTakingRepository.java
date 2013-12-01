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

import models.course.Course;
import models.course.Semester;
import models.database.connection.DBHelper;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.SemesterRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.repository.Pair;
import models.database.repository.TwoIntKeyRelationshipRepository;
import models.course.CourseOffering;
import models.person.Student;
import models.course.Semester.Season;

public class CoursesTakingRepository
        implements TwoIntKeyRelationshipRepository<Student,CourseOffering> {

    private static class SingletonHolder {
        public static final CoursesTakingRepository INSTANCE =
                new CoursesTakingRepository();
    }

    public static CoursesTakingRepository getInstance() {

        return SingletonHolder.INSTANCE;
    }

    private CoursesTakingRepository() {

    }

    private void createCoursesTakingTable(final Statement st)
            throws SQLException {
        final String createTableStatement = "CREATE TABLE CoursesTaking(" +
                "studentId INT NOT NULL, " +
                "courseOfferingId INT NOT NULL, " +
                "PRIMARY KEY (studentId, courseOfferingId), " +
                "FOREIGN KEY (studentId) references Student(studentId), " +
                "FOREIGN KEY (courseOfferingId) references CourseOffering(courseOfferingId) " +
                ") Engine=InnoDB;";
        st.execute(createTableStatement);
    }

    private void databaseCreationCheck(final DatabaseMetaData dbm,
                                       final Statement st) throws SQLException {
        final ResultSet tables = dbm.getTables(null, null, "CoursesTaking",
                null);

        CourseOfferingRepository.databaseCreationCheck(dbm, st);
        StudentRepository.databaseCreationCheck(dbm, st);

        if (!tables.next()) {
            // Table does not exist
            createCoursesTakingTable(st);
        }
        tables.close();
    }

    @Override
    public void add(final int studentId, final int courseId) throws SQLException
    {
        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final ResultSet courseRes = st.executeQuery("SELECT studentId, " +
                "courseOfferingId FROM CoursesTaking WHERE studentId =	" +
                studentId + " AND courseOfferingId = " + courseId + ";");

        if(!courseRes.next()){

            String insertCourseTakingQuery = "INSERT INTO CoursesTaking(" +
                    "studentId, courseOfferingId) VALUES (" + studentId + ", " +
                    courseId + ");";

            st.executeUpdate(insertCourseTakingQuery,
                    Statement.RETURN_GENERATED_KEYS);
        }

        c.close();
        courseRes.close();
        st.close();
    }

    public Iterator<Student> findStudentsTakingCourse(final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId "+
                "FROM CoursesTaking " +
                "WHERE courseOfferingId = " + courseOfferingId + ";";


        final ResultSet courseTakingRes =
                st.executeQuery(selectCourseTakingQuery);

        final Collection<Student> studentList = new ArrayList<Student>();

        while(courseTakingRes.next()) {
            studentList.add(StudentRepository.getInstance().findById(
                    courseTakingRes.getInt("studentId")));
        }

        c.close();
        courseTakingRes.close();
        st.close();

        return studentList.iterator();
    }

    public int findNumberOfCoursesTakingByStudent(final int studentId,
                                                  final Season season,
                                                  final short year)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTaking "+
                "WHERE studentId = "+ studentId + ";";

        final ResultSet courseTakingRes =
                st.executeQuery(SelectCourseTakingQuery);

        final Collection<CourseOffering> allCoursesTaking =
                new ArrayList<CourseOffering>();

        while(courseTakingRes.next()) {
            final CourseOffering co = CourseOfferingRepository.getInstance().
                    findById(courseTakingRes.getInt("courseOfferingId"));
            final Semester sem = co.getSemester();
            if (sem.getSeason().equals(season) && sem.getYear() == year) {
                allCoursesTaking.add(co);
            }
        }

        c.close();
        courseTakingRes.close();
        st.close();

        return allCoursesTaking.size();
    }

    // return the number of students taking a section
    public int findNumberOfStudentsTakingCourse(final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId "+
                "FROM CoursesTaking "+
                "WHERE courseOfferingId = "+ courseOfferingId + ";";


        final ResultSet courseTakingRes =
                st.executeQuery(selectCourseTakingQuery);

        int studentNum = 0;

        while(courseTakingRes.next()) {
            studentNum++;
        }

        c.close();
        courseTakingRes.close();
        st.close();

        return studentNum;

    }

    // returns true if exists in database
    public boolean contains(final int studentId, final int courseOfferingId)
            throws SQLException {

        return (!findById(studentId, courseOfferingId).compare(null, null));
    }

    // return all semesters taken by a particular student
    public Iterator<Semester> allSemestersStudentAttended(final int studentId)
            throws SQLException {

        final List<Semester> semesterList = new ArrayList<Semester>();

        final Iterator<CourseOffering> coursesTaking =
                getCoursesTakingByStudent(studentId);

        while (coursesTaking.hasNext()) {
            final CourseOffering course = coursesTaking.next();

            final Semester sem = course.getSemester();

            System.out.println(semesterList.contains(sem));
            if (!semesterList.contains(sem)) {
                semesterList.add(sem);
            }
        }

        return semesterList.iterator();
    }

    public Iterator<CourseOffering> getCoursesTakingByStudentForSemester(
            final int studentId, final Season season, final short year)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTaking " + "WHERE studentId = " + studentId + ";";

        final ResultSet courseTakingRes =
                st.executeQuery(SelectCourseTakingQuery);

        final Collection<CourseOffering> courseList =
                new ArrayList<CourseOffering>();

        while(courseTakingRes.next()){
            final CourseOffering co = CourseOfferingRepository.getInstance().
                    findById(courseTakingRes.getInt("courseOfferingId"));

            final Semester sem = SemesterRepository.getInstance().findById(
                    season, year);

            if (co.getSemester().equals(sem)) {
                courseList.add(co);
            }
        }

        c.close();
        courseTakingRes.close();
        st.close();

        return courseList.iterator();
    }

    public Iterator<CourseOffering> getCoursesTakingByStudent(
            final int studentId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTaking " + "WHERE studentId = " + studentId + ";";

        final ResultSet courseTakingRes =
                st.executeQuery(SelectCourseTakingQuery);

        final Collection<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();

        while(courseTakingRes.next()){
            courseOfferingList.add(CourseOfferingRepository.
                    getInstance().findById(
                    courseTakingRes.getInt("courseOfferingId")));
        }

        c.close();
        courseTakingRes.close();
        st.close();

        return courseOfferingList.iterator();
    }

    public Pair<Student,CourseOffering> findById(final int studentId,
                                                 final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId, " +
                "courseOfferingId FROM CoursesTaking WHERE studentId = " +
                studentId + " AND courseOfferingId = " + courseOfferingId + ";";

        final ResultSet courseTakingRes =
                st.executeQuery(selectCourseTakingQuery);

        final Pair<Student,CourseOffering> pair =
                new Pair<Student, CourseOffering>(null, null);

        while(courseTakingRes.next()){
            pair.setFirst(StudentRepository.getInstance().findById(
                    courseTakingRes.getInt("studentId")));
            pair.setSecond(CourseOfferingRepository.getInstance().
                    findById(courseTakingRes.getInt("courseOfferingId")));
        }
        c.close();
        courseTakingRes.close();
        st.close();

        return pair;
    }

    @Override
    // return true if an entry was deleted or false if no entry deleted
    public boolean delete(final int studentId, final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String deleteCourseTakingQuery = "DELETE FROM CoursesTaking " +
                "WHERE studentId = " + studentId + " AND courseOfferingId = " +
                courseOfferingId + ";";

        final boolean result = st.execute(deleteCourseTakingQuery);

        c.close();
        st.close();

        return result;
    }
}
