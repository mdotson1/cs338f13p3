package controllers.resources;

import play.mvc.Controller;
import play.mvc.Result;


import java.sql.SQLException;

import static play.mvc.Results.ok;

public class OnePaymentResource {
    public static Result get(final Integer studentId, final Integer paymentId) {

        /*
        final String PAYMENTS_URI =
                routes.controllers.resources.OnePaymentResource.get(studentId, paymentId);

        try {
            return ok(single_payment.render(
                    PaymentRepository.getInstance().findById(paymentId),
                    PAYMENTS_URI));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        return ok();
    }
}
