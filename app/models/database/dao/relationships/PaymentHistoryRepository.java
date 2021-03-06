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

import models.database.connection.DBHelper;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.repository.TwoIntKeyRelationshipRepository;
import models.person.Payment;
import models.person.Student;

public class PaymentHistoryRepository
        implements TwoIntKeyRelationshipRepository<Student,Payment> {

    private static class SingletonHolder {
        public static final PaymentHistoryRepository INSTANCE =
                new PaymentHistoryRepository();
    }

    public static PaymentHistoryRepository getInstance() {

        return SingletonHolder.INSTANCE;
    }

    private PaymentHistoryRepository() {

    }

    private void createPaymentHistoryTable(final Statement st)
            throws SQLException {
        final String createTableStatement = "CREATE TABLE PaymentHistory(" +
                "paymentId INT NOT NULL, " +
                "studentId INT NOT NULL, " +
                "PRIMARY KEY (paymentId, studentId), " +
                "FOREIGN KEY (studentId) references Student(studentId), " +
                "FOREIGN KEY (paymentId) references Payment(paymentId) " +
                ") Engine=InnoDB;";
        st.execute(createTableStatement);
    }

    private void databaseCreationCheck(final DatabaseMetaData dbm,
                                       final Statement st) throws SQLException {

        PaymentRepository.databaseCreationCheck(dbm, st);
        StudentRepository.databaseCreationCheck(dbm, st);

        final ResultSet historyTable = dbm.getTables(null, null,
                "PaymentHistory", null);
        if (!historyTable.next()) {
            // Table does not exist
            createPaymentHistoryTable(st);
        }
        historyTable.close();
    }

    @Override
    public void add(final int studentId, final int paymentId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final ResultSet courseRes = st.executeQuery("SELECT studentId, " +
                "paymentId FROM PaymentHistory WHERE studentId = " + studentId +
                " AND paymentId = " + paymentId+ ";");

        if(!courseRes.next()){

            final String insertStudentStatement = "INSERT INTO PaymentHistory(" +
                    "studentId, paymentId) VALUES(" + studentId +  ", " +
                    paymentId + ");";

            st.execute(insertStudentStatement);
        }

        c.close();
        courseRes.close();
        st.close();
    }

    public Iterator<Payment> findAllPaymentsByStudent(final int studentId)
            throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String selectPaymentHistoryQuery = "SELECT paymentId FROM " +
                "PaymentHistory WHERE studentId = " + studentId + ";";

        final ResultSet paymentHistoryRS = st.executeQuery(
                selectPaymentHistoryQuery);

        final Collection<Payment> allPayments = new ArrayList<Payment>();

        while ( paymentHistoryRS.next() ) {
            allPayments.add(PaymentRepository.getInstance().
                    findById(paymentHistoryRS.getInt("paymentId")));
        }

        c.close();
        paymentHistoryRS.close();
        st.close();

        return allPayments.iterator();
    }

    @Override
    public boolean delete(final int oneId, final int manyId) throws SQLException {

        final Connection c = DBHelper.getConnection();
        final Statement st = c.createStatement();

        databaseCreationCheck(c.getMetaData(), st);

        final String deleteStudentQuery = "DELETE FROM PaymentHistory WHERE " +
                "studentId = " + oneId + " AND paymentId = " + manyId + ";";

        final boolean result = st.execute(deleteStudentQuery);

        c.close();
        st.close();

        return result;
    }

}
