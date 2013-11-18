package models.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import models.database.connection.DBHelper;
import models.database.repository.ConcreteIntKeyRepository;
import models.person.ContactInformation;
import models.person.Student;

public class StudentRepository implements ConcreteIntKeyRepository<Student> {
	
	private static class SingletonHolder { 
		public static final StudentRepository INSTANCE = new StudentRepository();
	}

	public static StudentRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}
	
	private  StudentRepository() {
		
	}
	
	private void createStudentTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE Student(" +
				"studentId INT NOT NULL AUTO_INCREMENT, " +
				"dateOfBirth VARCHAR(10) NOT NULL, " +
				"homeAddress VARCHAR(200) NOT NULL, " +
				"workAddress VARCHAR(200), " + 
				"lastName VARCHAR(30) NOT NULL, " +
				"firstName VARCHAR(20) NOT NULL, " +
				"workPhone CHAR(13), " +
				"homePhone CHAR(13), " +
				"cellPhone CHAR(13), " +
				"currentBalance DOUBLE NOT NULL, " +
				"PRIMARY KEY ( studentId ) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(final DatabaseMetaData dbm,
			final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "Student", null);
		if (!tables.next()) {
			// Table does not exist
			createStudentTable(st);
		}
	}

	@Override
	// returns the student added
	public int add(final Student obj) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);

		final ContactInformation ci = obj.getContactInformation();

		final String insertStudentStatement = "INSERT INTO Student(" +
                "dateOfBirth, homeAddress, workAddress, lastName, firstName, " +
                "workPhone, homePhone, cellPhone, currentBalance) VALUES('" +
				obj.getDateOfBirth() + "', '" + ci.getHomeAddress() + "', '" +
                ci.getWorkAddress() + "', '" + ci.getFirstName() + "', '" +
                ci.getLastName() + "', '" + ci.getWorkPhone() + "', '" +
                ci.getHomePhone() + "', '" + ci.getCellPhone() + "', " +
                obj.getCurrentBalance() + ");";

		st.executeUpdate(insertStudentStatement, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
        } else {
            throw new SQLException("Creating Student failed, no generated key obtained.");
        }
	}

	@Override
	public Student findById(final int id) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);

		final String selectStudentQuery = "SELECT studentId, dateOfBirth, " +
                "homeAddress, workAddress, lastName, firstName, workPhone, " +
                "homePhone, cellPhone, currentBalance FROM Student WHERE " +
                "studentId = " + id + ";";
		
		final ResultSet studentRS = st.executeQuery(selectStudentQuery);   

		Student student = null;
		ContactInformation contactInformation = null;

		while ( studentRS.next() ) {
			contactInformation = new ContactInformation(
                    studentRS.getString("homeAddress"),
					studentRS.getString("workAddress"),
                    studentRS.getString("lastName"),
					studentRS.getString("firstName"),
                    studentRS.getString("workPhone"),
					studentRS.getString("homePhone"),
                    studentRS.getString("cellPhone"));
			
			student = new Student(contactInformation, id,
                    studentRS.getString("dateOfBirth"),
						studentRS.getDouble("currentBalance"));
		}
		return student;
	}
	
	public void updateBalance(final int studentId, final double newBalance) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		

		final String updateBalanceStatement = "UPDATE Student SET " +
                "currentBalance=" + newBalance + " WHERE studentId=" +
                studentId + ";";

		st.execute(updateBalanceStatement);
	}

	@Override
	public Iterator<Student> getAll() throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);

		final String selectStudentsQuery = "SELECT studentId, dateOfBirth, " +
                "homeAddress, workAddress, lastName, firstName, workPhone, " +
                "homePhone, cellPhone, currentBalance FROM Student;";

		final ResultSet studentRS = st.executeQuery(selectStudentsQuery);   

		final Collection<Student> studentList = new ArrayList<Student>();

		Student student = null;
		ContactInformation contactInformation = null;
		
		while ( studentRS.next() ) {
			contactInformation = new ContactInformation(
                    studentRS.getString("homeAddress"),
					studentRS.getString("workAddress"),
                    studentRS.getString("lastName"),
					studentRS.getString("firstName"),
                    studentRS.getString("workPhone"),
					studentRS.getString("homePhone"),
                    studentRS.getString("cellPhone"));
			
			student = new Student(contactInformation,
                    studentRS.getInt("studentId"),
					studentRS.getString("dateOfBirth"),
                    studentRS.getDouble("currentBalance"));
			
			studentList.add(student);
		}
		return studentList.iterator();
	}

	@Override
	public boolean delete(final int id) throws SQLException {
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String deleteStudentQuery = "DELETE FROM Student WHERE " +
                "studentId = " + id + ";";

		return st.execute(deleteStudentQuery);
	}

	@Override
	public boolean contains(final int id) throws SQLException {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	

}
