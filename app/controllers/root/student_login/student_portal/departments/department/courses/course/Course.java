package controllers.root.student_login.student_login.departments.department.courses.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.departments.department.courses.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int studentId, final String department,
                             final String courseNum) {
        return controllers.root.student_login.student_login.departments.department.
                courses.course.routes.Course.get(studentId, department,
                courseNum).url();
    }

    private static Result render(final int studentId, final String department,
                                 final String courseNum) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String department,
                             final String courseNum) {

        return render(studentId, department, courseNum);
    }
}
