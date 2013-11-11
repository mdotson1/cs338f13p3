package controllers;

import models.database.dao.concrete.PaymentRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import controllers.services.Bursar;

import play.data.Form;
import play.mvc.*;

import java.sql.SQLException;
import java.util.Map;

public class AllPayments extends Controller {

    private final static Form<Payment> paymentForm = Form.form(Payment.class);

    // this function is used to obtain the URI for the resource containing
    // all of the specified student's Payments
    public static String allPaymentsURI(final int studentId) {
        return routes.AllPayments.getPaymentHistoryForStudent(studentId).
                absoluteURL(request());
    }

    public static Result getPaymentHistoryForStudent(final Integer studentId) {

        try {
            return ok(payments_table.render(PaymentHistoryRepository.
                    getInstance().findAllPaymentsByStudent(studentId),
                    AllPayments.allPaymentsURI(studentId), paymentForm,
                    studentId));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result addPaymentForStudent(final Integer studentId) {

        final Form<Payment> filledForm = paymentForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final Payment p = new Payment(data.get("paymentType"),
                    Double.parseDouble(data.get("paymentAmount")));

            int paymentId = PaymentRepository.getInstance().add(p);
            Bursar.payBalance(studentId, paymentId);
            return ok(payments_table.render(
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId),
                    AllPayments.allPaymentsURI(studentId), filledForm,
                    studentId));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result updatePayment(final Integer studentId,
                                       final Integer paymentId) {
        return ok();
    }
}
