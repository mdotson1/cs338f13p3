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
import models.person.Professor;

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
				"professorId INT NOT NULL AUTO_INCREMENT," +
				"dateOfBirth VARCHAR(10) NOT NULL," +
				"homeAddress VARCHAR(200) NOT NULL," +
				"workAddress VARCHAR(200)," + 
				"firstName VARCHAR(30) NOT NULL," +
				"lastName VARCHAR(20) NOT NULL," +
				"workPhone CHAR(13)," +
				"homePhone CHAR(13)," +
				"cellPhone CHAR(13)," +
				"department VARCHAR(20) NOT NULL," +
				"PRIMARY KEY ( professorId ) " +
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
	
	@Override
	public int add(final Professor obj) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final ContactInformation ci = obj.getContactInformation();

		final String insertProfessorStatement = "INSERT INTO Professor(" +
                "dateOfBirth, homeAddress, workAddress, firstName, lastName, " +
                "workPhone, homePhone, cellPhone, department) VALUES('" +
				obj.getDateOfBirth() + "', '" + ci.getHomeAddress() + "', '" +
                ci.getWorkAddress() + "', '" + ci.getFirstName() + "', '" +
                ci.getLastName() + "', '" + ci.getWorkPhone() + "', '" +
				ci.getHomePhone() + "', '" + ci.getCellPhone() + "', '" +
                obj.getDepartment() + "');";

		st.executeUpdate(insertProfessorStatement, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if (rs.next()) {
			return rs.getInt(1);
        } else {
            throw new SQLException("Creating Professor failed, no generated" +
                    " key obtained.");
        }
	}

	@Override
	public Professor findById(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String selectProfessorQuery = "SELECT professorId, dateOfBirth," +
                " homeAddress, workAddress, firstName, lastName, workPhone, " +
                "homePhone, cellPhone, department FROM Professor WHERE " +
                "professorId =" + id + ";";

		final ResultSet professorRS = st.executeQuery(selectProfessorQuery);   

		Professor professor = null;
		ContactInformation contactInformation = null;

		while ( professorRS.next() ) {
			contactInformation = new ContactInformation(
                    professorRS.getString("homeAddress"),
					professorRS.getString("workAddress"),
                    professorRS.getString("firstName"),
					professorRS.getString("lastName"),
                    professorRS.getString("workPhone"),
					professorRS.getString("homePhone"),
                    professorRS.getString("cellPhone"));

			professor = new Professor(contactInformation, id,
                    professorRS.getString("dateOfBirth"),
					professorRS.getString("department"));
		}
		return professor;
	}

	@Override
	public Iterator<Professor> getAll() throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final ResultSet professorRS = st.executeQuery("SELECT professorId, " +
                "dateOfBirth, homeAddress, workAddress, firstName, lastName, " +
                "workPhone, homePhone, cellPhone, department FROM Professor;");

		final Collection<Professor> profList = new ArrayList<Professor>();

		Professor professor = null;
		ContactInformation contactInformation = null;
		
		while ( professorRS.next() ) {
			contactInformation = new ContactInformation(
                    professorRS.getString("homeAddress"),
					professorRS.getString("workAddress"),
                    professorRS.getString("firstName"),
                    professorRS.getString("lastName"),
                    professorRS.getString("workPhone"),
					professorRS.getString("homePhone"),
                    professorRS.getString("cellPhone"));
			
			professor = new Professor(contactInformation,
                    professorRS.getInt("professorId"),
					professorRS.getString("dateOfBirth"),
                    professorRS.getString("department"));
			
			profList.add(professor);
		}
		return profList.iterator();
	}

    public Iterator<Professor> getFaculty(final String department)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final ResultSet professorRS = st.executeQuery("SELECT professorId, " +
                "dateOfBirth, homeAddress, workAddress, firstName, lastName, " +
                "workPhone, homePhone, cellPhone, department FROM Professor " +
                "WHERE department='" + department + "';");

        final Collection<Professor> profList = new ArrayList<Professor>();

        Professor professor = null;
        ContactInformation contactInformation = null;

        while ( professorRS.next() ) {
            contactInformation = new ContactInformation(
                    professorRS.getString("homeAddress"),
                    professorRS.getString("workAddress"),
                    professorRS.getString("firstName"),
                    professorRS.getString("lastName"),
                    professorRS.getString("workPhone"),
                    professorRS.getString("homePhone"),
                    professorRS.getString("cellPhone"));

            professor = new Professor(contactInformation,
                    professorRS.getInt("professorId"),
                    professorRS.getString("dateOfBirth"),
                    professorRS.getString("department"));

            profList.add(professor);
        }
        return profList.iterator();
    }

    public Iterator<String> allDepartments() throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final ResultSet departmentRS = st.executeQuery("SELECT department " +
                "FROM Professor GROUP BY department;");

        final Collection<String> departmentList = new ArrayList<String>();

        while ( departmentRS.next() ) {
            departmentList.add(departmentRS.getString("department"));
        }
        return departmentList.iterator();
    }
	

	@Override
	public boolean delete(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		return st.execute("DELETE FROM Professor WHERE professorId = " + id +
                ";");
		
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
