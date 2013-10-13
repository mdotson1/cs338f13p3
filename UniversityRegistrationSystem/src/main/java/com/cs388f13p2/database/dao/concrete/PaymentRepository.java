package com.cs388f13p2.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cs388f13p2.database.connection.DBHelper;
import com.cs388f13p2.database.repository.ConcreteIntKeyRepository;
import com.cs388f13p2.model.person.Payment;

public class PaymentRepository implements ConcreteIntKeyRepository<Payment> {

	private static class SingletonHolder { 
		public static final PaymentRepository INSTANCE = new PaymentRepository();
	}

	public static PaymentRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private PaymentRepository() {
		
	}
	
	private void createPaymentTable(final Statement st) throws SQLException {
		final String createTableStatement = "CREATE TABLE CourseOffering(" +
				"paymentId INT NOT NULL, " +
				"paymentType VARCHAR(10) NOT NULL, " +
				"paymentAmount INT NOT NULL, " +
				"PRIMARY KEY (paymentId) " + 
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	private void databaseCreationCheck(final DatabaseMetaData dbm, 
			final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "Payment", null);
		if (!tables.next()) {
			// Table does not exist
			createPaymentTable(st);
		}
	}
	
	@Override
	public void add(final Payment obj) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String insertPaymentStatement = "INSERT INTO Payment(paymentId, paymentType," +
				" paymentAmount) VALUES(" + obj.getPaymentId() + ", '" + obj.getPaymentType() + 
				"', " + obj.getPaymentAmount() + ");";

		st.execute(insertPaymentStatement);
	}

	@Override
	public Payment findById(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return null;
	}

	@Override
	public boolean delete(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		// TODO marcellin
		return false;
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
