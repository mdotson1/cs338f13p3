package controllers.root.student_portal.student.payments.payment;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.payments.payment.*;
import views.html.helpers.*;

public class Payment extends Controller {

    public static String url(final int studentId, final int professorId) {
        return controllers.root.student_portal.student.payments.payment.routes.
                Payment.get(studentId, professorId).url();
    }

    private static Result render(final int studentId, final int professorId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final int professorId) {

        return render(studentId, professorId);
    }
}
