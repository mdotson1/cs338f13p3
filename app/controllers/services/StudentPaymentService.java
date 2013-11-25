package controllers.services;

import java.sql.SQLException;

public class StudentPaymentService {

    private StudentPaymentService() { } // cannot instantiate services

    public static void payBalance(final int studentId, final int paymentId)
            throws SQLException {
        StudentService.payBalance(studentId, paymentId);
    }
}
