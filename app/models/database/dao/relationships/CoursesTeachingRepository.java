package models.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import models.course.Semester;
import models.course.Semester.*;
import models.database.connection.DBHelper;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.concrete.SemesterRepository;
import models.database.repository.TwoIntKeyRelationshipRepository;
import models.course.CourseOffering;
import models.person.Professor;

public class CoursesTeachingRepository
        implements TwoIntKeyRelationshipRepository<Professor,CourseOffering> {

    private static class SingletonHolder {
        public static final CoursesTeachingRepository INSTANCE =
                new CoursesTeachingRepository();
    }

    public static CoursesTeachingRepository getInstance() {

        return SingletonHolder.INSTANCE;
    }

    private CoursesTeachingRepository() {

    }

    private void createCoursesTeachingTable(final Statement st)
            throws SQLException {
        final String createTableStatement = "CREATE TABLE CoursesTeaching(" +
                "professorId INT NOT NULL, " +
                "courseOfferingId INT NOT NULL, " +
                "PRIMARY KEY (professorId, courseOfferingId), "  +
                "FOREIGN KEY (professorId) references Professor(professorId), " +
                "FOREIGN KEY (courseOfferingId) references CourseOffering(courseOfferingId) " +
                ") Engine=InnoDB;";
        st.execute(createTableStatement);

    }

    private void databaseCreationCheck(DatabaseMetaData dbm, Statement st)
            throws SQLException {

        ProfessorRepository.databaseCreationCheck(dbm, st);
        CourseOfferingRepository.databaseCreationCheck(dbm, st);

        final ResultSet tables = dbm.getTables(null, null, "CoursesTeaching",
                null);
        if (!tables.next()) {
            // Table does not exist
            createCoursesTeachingTable(st);
        }
    }

    @Override
    public void add(final int professorId, final int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        String insertCourseTeachingQuery = "INSERT INTO CoursesTeaching(" +
                "professorId, courseOfferingId) VALUES ("+ professorId + ", " +
                courseOfferingId + ");";

        st.executeUpdate(insertCourseTeachingQuery,
                Statement.RETURN_GENERATED_KEYS);

        c.close();
        st.close();
    }

    // return all courses taught by professor
    public Iterator<CourseOffering> findAllCoursesTaughtByProfessor(
            final int professorId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTeachingQuery = "SELECT courseOfferingId "+
                "FROM CoursesTeaching WHERE professorId = " + professorId + ";";

        final ResultSet courseTeachingRes = st.executeQuery(
                SelectCourseTeachingQuery);

        final Collection<CourseOffering> courseOfferingList =
                new ArrayList<CourseOffering>();

        while(courseTeachingRes.next()) {
            courseOfferingList.add(CourseOfferingRepository.
                    getInstance().findById(
                    courseTeachingRes.getInt("courseOfferingId")));
        }

        c.close();
        courseTeachingRes.close();
        st.close();

        return courseOfferingList.iterator();
    }

    // return all semesters taught by professor
    public Iterator<Semester> allSemestersProfessorTaughtIn(
            final int professorId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTeachingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTeaching WHERE professorId = " + professorId + ";";

        final ResultSet courseOfferingIdsRS = st.executeQuery(
                selectCourseTeachingQuery);

        final Collection<Semester> semesterList = new ArrayList<Semester>();

        while(courseOfferingIdsRS.next()) {
            final CourseOffering co = CourseOfferingRepository.getInstance().findById(
                    courseOfferingIdsRS.getInt("courseOfferingId"));

            final Semester sem = co.getSemester();
            if (!semesterList.contains(sem)) {
                semesterList.add(sem);
            }
        }

        c.close();
        courseOfferingIdsRS.close();
        st.close();

        return semesterList.iterator();
    }

    // return the professors for the section
    public Iterator<Professor> findProfessorsForCourse(
            final int courseOfferingId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectCourseTeachingQuery = "SELECT professorId "+
                "FROM CoursesTeaching WHERE courseOfferingId = " +
                courseOfferingId + ";";


        final ResultSet courseTeachingRes = st.executeQuery(
                selectCourseTeachingQuery);

        Collection<Professor> professorList = new ArrayList<Professor>();

        while(courseTeachingRes.next()) {

            final Professor professor = ProfessorRepository.getInstance().
                    findById(courseTeachingRes.getInt("professorId"));
            professorList.add(professor);
        }

        c.close();
        courseTeachingRes.close();
        st.close();

        return professorList.iterator();
    }

    public Iterator<CourseOffering> getCoursesTaughtByProfessorForSemester(
            final int professorId, final Season season, final short year)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String SelectCourseTakingQuery = "SELECT courseOfferingId " +
                "FROM CoursesTeaching " + "WHERE professorId = " + professorId +
                ";";

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

    @Override
    // returns true if something was deleted
    public boolean delete(int professorId, int courseOfferingId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String deleteCourseTeachingQuery = "DELETE FROM CoursesTeaching" +
                " WHERE professorId = "+ professorId + " AND " +
                "courseOfferingId = " + courseOfferingId + ";";

        final boolean result = st.execute(deleteCourseTeachingQuery);

        c.close();
        st.close();

        return result;
    }
}
