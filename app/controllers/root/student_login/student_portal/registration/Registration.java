package controllers.root.student_login.student_login.registration;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.registration.*;
import views.html.helpers.*;

public class Registration extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_login.registration.routes.
                Registration.get(studentId).url();
    }

    private static Result render(final int studentId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId) {

        return render(studentId);
    }
}
