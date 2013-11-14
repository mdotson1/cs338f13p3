package controllers.root.admin.students.student.payments;

import controllers.resources.PaymentsResource;
import controllers.root.admin.students.student.Student;
import play.mvc.Controller;
import play.mvc.Result;

public class Payments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.payments.routes.Payments.
                get(studentId).url();
    }

    public static Result get(final int studentId) {

        return PaymentsResource.payments_get(studentId);
    }

    public static Result post(final int studentId) {
        return PaymentsResource.payments_post(studentId);
    }
}
