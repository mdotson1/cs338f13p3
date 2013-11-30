package models.database.dao.concrete;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import models.database.connection.DBHelper;
import models.database.repository.ConcreteIntKeyRepository;
import models.course.CourseOffering;
import models.person.Payment;

public class PaymentRepository implements ConcreteIntKeyRepository<Payment> {

	private static class SingletonHolder { 
		public static final PaymentRepository INSTANCE = new PaymentRepository();
	}

	public static PaymentRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private PaymentRepository() {
		
	}
	
	private static void createPaymentTable(final Statement st)
            throws SQLException {

		final String createTableStatement = "CREATE TABLE Payment(" +
				"paymentId INT NOT NULL AUTO_INCREMENT, " +
				"paymentType VARCHAR(20) NOT NULL, " +
				"paymentAmount DOUBLE NOT NULL, " +
				"PRIMARY KEY (paymentId) " + 
				") Engine=InnoDB;";
		st.execute(createTableStatement);
	}
	
	public static void databaseCreationCheck(final DatabaseMetaData dbm,
			final Statement st) throws SQLException {
		final ResultSet tables = dbm.getTables(null, null, "Payment", null);

		if (!tables.next()) {
			// Table does not exist
			createPaymentTable(st);
		}
        tables.close();
	}
	
	@Override
	public int add(final Payment obj) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);

		final String insertPaymentStatement = "INSERT INTO Payment(paymentType," +
				" paymentAmount) VALUES('" + obj.getPaymentType() + 
				"', " + obj.getPaymentAmount() + ");";

		st.executeUpdate(insertPaymentStatement, Statement.RETURN_GENERATED_KEYS);
		final ResultSet rs = st.getGeneratedKeys();
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
            throw new SQLException("Creating payment failed, no generated key obtained.");
        }
	}

	@Override
	public Payment findById(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseQuery = "SELECT paymentId, paymentType, paymentAmount " +
										"FROM Payment WHERE paymentId = "+ id + ";";
				

		final ResultSet paymentRes = st.executeQuery(SelectCourseQuery);

		Payment payment = null;

		while(paymentRes.next()){
			payment = new Payment(paymentRes.getInt("paymentId"), paymentRes.getString("paymentType"),
							paymentRes.getDouble("paymentAmount"));
		}

        c.close();
        paymentRes.close();
        st.close();
		
		return payment;
	}

	@Override
	public boolean delete(final int id) throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		final String deletePaymentQuery = "DELETE FROM Payment WHERE paymentId = " + id + ";";

        final boolean retval = st.execute(deletePaymentQuery);

        c.close();
        st.close();

		return retval;
	}

	@Override
	public boolean contains(final int id) throws SQLException {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Iterator<Payment> getAll() throws SQLException {
		
		final Connection c = DBHelper.getConnection();
		final Statement st = c.createStatement();

		databaseCreationCheck(c.getMetaData(), st);
		
		final String SelectCourseQuery = "SELECT paymentId, paymentType, paymentAmount " +
											"FROM Payment;";

		final ResultSet paymentRes = st.executeQuery(SelectCourseQuery);

		Payment payment = null;
		
		final Collection<Payment> paymentList = new ArrayList<Payment>();

		while(paymentRes.next()){
			payment = new Payment(paymentRes.getInt("paymentId"), paymentRes.getString("paymentType"),
								paymentRes.getDouble("paymentAmount"));
			paymentList.add(payment);
		}

        c.close();
        paymentRes.close();
        st.close();
		
		return paymentList.iterator();
	}
}
