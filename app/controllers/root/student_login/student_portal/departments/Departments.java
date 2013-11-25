package controllers.root.student_login.student_login.departments;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.departments.*;
import views.html.helpers.*;

public class Departments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_login.departments.routes.
                Departments.get(studentId).url();
    }

    private static Result render(final int studentId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId) {

        return render(studentId);
    }
}
