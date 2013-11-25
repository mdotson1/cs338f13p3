package controllers.root.student_login.student_login.semesters;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.semesters.*;
import views.html.helpers.*;

public class Semesters extends Controller {
    public static String url(final int studentId) {
        return controllers.root.student_login.student_login.semesters.routes.
                Semesters.get(studentId).url();
    }

    private static Result render(final int studentId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId) {

        return render(studentId);
    }
}
