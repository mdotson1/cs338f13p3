package models.database.dao.concrete;

import models.course.Semester;
import models.course.Semester.Season;
import models.database.connection.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SemesterRepository {

    private static class SingletonHolder {
        public static final SemesterRepository INSTANCE = new SemesterRepository();
    }

    public static SemesterRepository getInstance() {

        return SingletonHolder.INSTANCE;
    }

    private void createSemesterTable(final Statement st) throws SQLException {
        String createTableStatement = "CREATE TABLE Semester(" +
                "season VARCHAR(6) NOT NULL," +
                "year SMALLINT NOT NULL," +
                "PRIMARY KEY (season, year), " +
                "INDEX (year)" +
                ") Engine=InnoDB;";
        st.execute(createTableStatement);
    }

    private void databaseCreationCheck(DatabaseMetaData dbm, Statement st)
            throws SQLException {
        final ResultSet tables = dbm.getTables(null, null, "Semester", null);
        if (!tables.next()) {
            // Table does not exist
            createSemesterTable(st);
        }
    }

    public void add(final Semester obj) throws SQLException {
        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String insertSemesterStatement = "INSERT INTO Semester (season," +
                " year) VALUES('" + obj.getSeason() + "', " + obj.getYear() +
                ");";

        st.executeUpdate(insertSemesterStatement);
    }

    public Semester findById(final Season season, final short year)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectSemesterQuery = "SELECT season, year, cost " +
                "FROM Semesters WHERE season = '" + season.toString() +
                "' AND year = " + year + ";";

        final ResultSet semesterRes = st.executeQuery(selectSemesterQuery);

        Semester semester = null;

        while(semesterRes.next()){
            semester = new Semester(Semester.Season.valueOf(
                    semesterRes.getString("season")),
                    semesterRes.getShort("year"));
        }
        return semester;
    }

    public boolean delete(final Season season, final short year)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String deleteCourseOfferingQuery = "DELETE FROM Semester WHERE " +
                "season = '" + season.toString() + "' AND year = " + year + ";";

        return st.execute(deleteCourseOfferingQuery);
    }

    public Iterator<Semester> getAll() throws SQLException {
        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectSemesterQuery = "SELECT season, year FROM " +
                "Semester;";

        final ResultSet semesterRes = st.executeQuery(selectSemesterQuery);

        final List<Semester> semesterList = new ArrayList<Semester>();

        while(semesterRes.next() ){
            Semester semester = new Semester(Season.valueOf(
                    semesterRes.getString("season")),
                    semesterRes.getShort("year"));
            semesterList.add(semester);
        }
        return semesterList.iterator();
    }
}
