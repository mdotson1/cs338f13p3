package controllers.root.student_login.student_login.departments.department;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.departments.department.*;
import views.html.helpers.*;

public class Department extends Controller {

    public static String url(final int studentId, final String department) {
        return controllers.root.student_login.student_login.departments.department.
                routes.Department.get(studentId, department).url();
    }

    private static Result render(final int studentId, final String department) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String department) {

        return render(studentId, department);
    }
}
