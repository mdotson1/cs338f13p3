package controllers.root.student_portal.student.departments.department.faculty;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.departments.department.faculty.*;
import views.html.helpers.*;

public class Faculty extends Controller {

    public static String url(final int studentId, final String department) {
        return controllers.root.student_portal.student.departments.department.
                faculty.routes.Faculty.get(studentId, department).url();
    }

    private static Result render(final int studentId, final String department) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String department) {

        return render(studentId, department);
    }
}
