package controllers.resources;

import controllers.services.Bursar;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class PaymentsResource {

    private final static Form<Payment> PAYMENT_FORM = Form.form(Payment.class);

    // this function is used to obtain the URI for the resource containing
    // all of the specified student's Payments
    public static String allPaymentsURI(final int studentId) {
        return controllers.root.admin.students.student.payments.routes.Payments.
                get(studentId).url();
    }

    public static Result get(final int studentId,
                             final Map<String,String> backLink) {
        try {
            return ok(payments.render(PaymentHistoryRepository.
                    getInstance().findAllPaymentsByStudent(studentId),
                    PaymentsResource.allPaymentsURI(studentId), PAYMENT_FORM,
                    studentId, backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final int studentId,
                              final Map<String,String> backLink) {

        final Form<Payment> filledForm = PAYMENT_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final Payment p = new Payment(data.get("Payment Type"),
                    Double.parseDouble(data.get("Payment Amount (USD)")));

            int paymentId = PaymentRepository.getInstance().add(p);
            Bursar.payBalance(studentId, paymentId);
            return ok(payments.render(
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId),
                    PaymentsResource.allPaymentsURI(studentId), filledForm,
                    studentId, backLink));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

}
