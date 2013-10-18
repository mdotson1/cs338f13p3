package com.cs388f13p2.database.dao.relationships;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.dao.concrete.PaymentRepository;
import com.cs388f13p2.database.repository.TwoIntKeyRelationshipRepository;
import com.cs388f13p2.model.person.Payment;
import com.cs388f13p2.model.person.Student;

public class PaymentHistoryRepository implements TwoIntKeyRelationshipRepository<Student,Payment> {

	private static class SingletonHolder { 
		public static final PaymentHistoryRepository INSTANCE = new PaymentHistoryRepository();
	}

	public static PaymentHistoryRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private PaymentHistoryRepository() {
		
	}
	
	private void createPaymentHistoryTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE PaymentHistory(" +
				"studentId INT NOT NULL, " +
				"paymentId INT NOT NULL, " +
				"PRIMARY KEY (paymentId, studentId), " + 
				"FOREIGN KEY (studentId) references Student(Id), " +
				"FOREIGN KEY (paymentId) references Payment(paymentId) " +
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(final DatabaseMetaData dbm, final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "PaymentHistory", null);
		if (!tables.next()) {
			// Table does not exist
			createPaymentHistoryTable(st);
		}
	}
	
	@Override
	public void add(final int studentId, final int paymentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String insertStudentStatement = "INSERT INTO PaymentHistory(studentId, paymentId) VALUES(" +
				studentId +  ", " + paymentId + ");";

		st.execute(insertStudentStatement);
		
		
		
	}

	public Iterator<Payment> findAllPaymentsByStudent(final int studentId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String selectPaymentHistoryQuery = "SELECT paymentId" +
				" FROM PaymentHistory WHERE studentId = '" + studentId + "';";
		
		final ResultSet paymentHistoryRS = st.executeQuery(selectPaymentHistoryQuery);   

		final List<Payment> allPayments = new ArrayList<Payment>();
		
		while ( paymentHistoryRS.next() ) {
			allPayments.add(PaymentRepository.getInstance().findById(paymentHistoryRS.getInt("paymentId")));
		}
		return allPayments.iterator();
	}

	@Override
	public boolean delete(final int oneId, final int manyId) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();
		
		databaseCreationCheck(c.getMetaData(), st);
		
		final String deleteStudentQuery = "DELETE FROM PaymentHistory WHERE studentId = '" + oneId +
				" AND paymentId = '" + manyId + "';";

		return st.execute(deleteStudentQuery);
	}

}
