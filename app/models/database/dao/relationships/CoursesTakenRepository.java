package models.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.course.Semester;
import models.database.connection.DBHelper;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.repository.Pair;
import models.database.repository.TwoIntKeyRelationshipRepository;
import models.course.CourseOffering;
import models.person.Student;

public class CoursesTakenRepository
        implements TwoIntKeyRelationshipRepository<Student,CourseOffering> {

    private static class SingletonHolder {
        public static final CoursesTakenRepository INSTANCE =
                new CoursesTakenRepository();
    }

    public static CoursesTakenRepository getInstance() {

        return SingletonHolder.INSTANCE;
    }

    private CoursesTakenRepository() {

    }

    private void createCoursesTakenTable(final Statement st)
            throws SQLException {
        final String createTableStatement = "CREATE TABLE CoursesTaken(" +
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
        final ResultSet tables = dbm.getTables(null, null, "CoursesTaken",
                null);

        CourseOfferingRepository.getInstance().databaseCreationCheck(dbm, st);

        if (!tables.next()) {
            // Table does not exist
            createCoursesTakenTable(st);
        }
    }

    @Override
    public void add(final int studentId, final int courseId) throws SQLException
    {
        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final ResultSet courseRes = st.executeQuery("SELECT studentId, " +
                "courseOfferingId FROM CoursesTaken WHERE studentId =	" +
                studentId + " AND courseOfferingId = " + courseId + ";");

        if(!courseRes.next()){

            String insertCourseTakingQuery = "INSERT INTO CoursesTaken(" +
                    "studentId, courseOfferingId) VALUES (" + studentId + ", " +
                    courseId + ");";

            st.executeUpdate(insertCourseTakingQuery,
                    Statement.RETURN_GENERATED_KEYS);
        }
    }

    // return the number of semesters taken by a student
    public int findNumberOfCoursesTakenByStudent(final int studentId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTaken "+
                "WHERE studentId = "+ studentId + ";";


        final ResultSet CourseTakingRes =
                st.executeQuery(SelectCourseTakingQuery);

        int courseNum = 0;

        while(CourseTakingRes.next()) {
            courseNum++;
        }

        return courseNum;
    }

    // return the number of students taking a course
    public int findNumberOfStudentsTakingCourse(final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId "+
                "FROM CoursesTaken "+
                "WHERE courseOfferingId = "+ courseOfferingId + ";";


        final ResultSet courseTakingRes =
                st.executeQuery(selectCourseTakingQuery);

        int studentNum = 0;

        while(courseTakingRes.next()) {
            studentNum++;
        }

        return studentNum;

    }

    // returns true if exists in database
    public boolean contains(final int studentId, final int courseOfferingId)
            throws SQLException {

        return (!findById(studentId, courseOfferingId).compare(null, null));
    }

    // return all students taking a particular course
    public Iterator<Student> getStudentsTakingCourse(final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId "+
                "FROM CoursesTaken "+
                "WHERE courseOfferingId = " + courseOfferingId + ";";


        final ResultSet courseTakingRes =
                st.executeQuery(selectCourseTakingQuery);

        final List<Student> studentList = new ArrayList<Student>();

        while(courseTakingRes.next())
            studentList.add(StudentRepository.getInstance().findById(
                    courseTakingRes.getInt("studentId")));

        return studentList.iterator();
    }

    // return all semesters taken by a particular student
    public Iterator<Semester> allSemestersStudentAttended(final int studentId)
            throws SQLException {

        final List<Semester> semesterList = new ArrayList<Semester>();

        Iterator<CourseOffering> coursesTaking =
                getCoursesTakenByStudent(studentId);

        while (coursesTaking.hasNext()) {
            CourseOffering course = coursesTaking.next();

            semesterList.add(course.getSemester());
        }
        return semesterList.iterator();
    }

    public Iterator<CourseOffering> getCoursesTakenByStudent(
            final int studentId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId "+
                "FROM CoursesTaken "+
                "WHERE studentId = "+ studentId + ";";

        final ResultSet CourseTakingRes =
                st.executeQuery(SelectCourseTakingQuery);

        final List<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();

        while(CourseTakingRes.next()){
            courseOfferingList.add(CourseOfferingRepository.
                    getInstance().findById(
                    CourseTakingRes.getInt("courseOfferingId")));
        }

        return courseOfferingList.iterator();
    }

    public Pair<Student,CourseOffering> findById(final int studentId,
                                                 final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTakingQuery = "SELECT studentId, " +
                "courseOfferingId FROM CoursesTaken WHERE studentId = " +
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
        return pair;
    }

    @Override
    // return true if an entry was deleted or false if no entry deleted
    public boolean delete(final int studentId, final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String deleteCourseTakingQuery = "DELETE FROM CoursesTaken " +
                "WHERE studentId = " + studentId + " AND courseOfferingId = " +
                courseOfferingId + ";";

        return st.execute(deleteCourseTakingQuery);
    }
}
