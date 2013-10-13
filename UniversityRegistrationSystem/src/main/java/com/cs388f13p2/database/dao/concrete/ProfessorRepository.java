package com.cs388f13p2.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.repository.ConcreteIntKeyRepository;
import com.cs388f13p2.model.person.ContactInformation;
import com.cs388f13p2.model.person.Professor;

public class ProfessorRepository implements ConcreteIntKeyRepository<Professor> {

	private static class SingletonHolder { 
		public static final ProfessorRepository INSTANCE = new ProfessorRepository();
	}

	public static ProfessorRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}
	
	private ProfessorRepository() {
		
	}
	
	private void createProfessorTable(final Statement st) throws SQLException {
		String createTableStatement = "CREATE TABLE Professor(" +
				"id INT NOT NULL," +
				"dateOfBirth VARCHAR(10) NOT NULL," +
				"homeAddress VARCHAR(200) NOT NULL," +
				"workAddress VARCHAR(200)," + 
				"lastName VARCHAR(30) NOT NULL," +
				"firstName VARCHAR(20) NOT NULL," +
				"workPhone CHAR(13)," +
				"homePhone CHAR(13)," +
				"cellPhone CHAR(13)," +
				"department VARCHAR(20) NOT NULL," +
				"PRIMARY KEY ( id ) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(final DatabaseMetaData dbm, 
			final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "Professor", null);
		if (!tables.next()) {
			// Table does not exist
			createProfessorTable(st);
		}
	}
	
	public void add(final Professor obj) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final ContactInformation ci = obj.getContactInformation();

		final String insertProfessorStatement = "INSERT INTO Professor(id, dateOfBirth, homeAddress, workAddress, " +
				"lastName, firstName, workPhone, homePhone, cellPhone, department) VALUES(" + obj.getId() +  ", '" + 
				obj.getDateOfBirth() + "', '" + ci.getHomeAddress() + "', '" + ci.getWorkAddress() + "', '" +
				ci.getLastName() + "', '" + ci.getFirstName() + "', '" + ci.getWorkPhone() + "', " + 
				ci.getHomePhone() + ", '" + ci.getCellPhone() + "', '" + obj.getDepartment() + "');";

		st.execute(insertProfessorStatement);
	}

	public Professor findById(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String selectProfessorQuery = "SELECT id, dateOfBirth, homeAddress, workAddress, " +
				"lastName, firstName, workPhone, homePhone, cellPhone, department" +
				" FROM Professor WHERE id = '" + id + "'";

		final ResultSet professorRS = st.executeQuery(selectProfessorQuery);   

		Professor professor = null;
		ContactInformation contactInformation = null;

		while ( professorRS.next() ) {
			contactInformation = new ContactInformation(professorRS.getString("homeAddress"),
					professorRS.getString("workAddress"), professorRS.getString("firstName"),
					professorRS.getString("lastName"), professorRS.getString("workPhone"),
					professorRS.getString("homePhone"), professorRS.getString("cellPhone"));

			professor = new Professor(contactInformation, id, professorRS.getString("dateOfBirth"),
					professorRS.getString("department"));
		}
		return professor;
	}

	public Iterator<Professor> getAll() throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}

	public boolean delete(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
	}

	public boolean contains(final int id) throws SQLException {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

}
