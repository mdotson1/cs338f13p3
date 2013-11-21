package controllers.root.student_portal.student.payments;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.payments.*;
import views.html.helpers.*;

public class Payments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_portal.student.payments.routes.
                Payments.get(studentId).url();
    }

    private static Result render(final int studentId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId) {

        return render(studentId);
    }

    public static Result post(final int studentId) {

        return render(studentId);
    }
}
