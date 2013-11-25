package controllers.root.professor_login.professor_portal.departments.department.courses.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.courses.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int professorId, final String department,
                             final String courseNum) {
        return controllers.root.professor_login.professor_portal.departments.
                department.courses.course.routes.Course.get(professorId,
                department, courseNum).url();
    }

    private static Result render(final int professorId, final String department,
                                 final String courseNum) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId, final String department,
                             final String courseNum) {

        return render(professorId, department, courseNum);
    }
}
