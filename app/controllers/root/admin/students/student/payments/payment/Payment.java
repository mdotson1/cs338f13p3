package controllers.root.admin.students.student.payments.payment;

import controllers.resources.OnePaymentResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Payment extends Controller {

    public static String url(final int studentId, final int paymentId) {
        return controllers.root.admin.students.student.payments.payment.routes.
                Payment.get(studentId, paymentId).url();
    }

    public static Result get(final int studentId, final int paymentId) {

        return OnePaymentResource.get(studentId, paymentId);
    }
}
