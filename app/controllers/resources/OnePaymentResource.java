package controllers.resources;

import models.database.dao.concrete.PaymentRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.ok;

public class OnePaymentResource {
    public static Result get(final Integer studentId, final Integer paymentId,
                             final Map<String,String> backLink) {

        final String PAYMENTS_URI = controllers.root.admin.students.student.
                payments.payment.routes.Payment.get(studentId, paymentId).url();

        try {
            return ok(one_payment.render(
                    PaymentRepository.getInstance().findById(paymentId),
                    backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
