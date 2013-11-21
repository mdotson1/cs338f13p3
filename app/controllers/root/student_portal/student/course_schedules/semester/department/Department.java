package controllers.root.student_portal.student.course_schedules.semester.department;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.course_schedules.semester.department.*;
import views.html.helpers.*;

public class Department extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department) {
        return controllers.root.student_portal.student.course_schedules.
                semester.department.routes.Department.get(studentId,
                seasonAndYear, department).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String department) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department) {

        return render(studentId, seasonAndYear, department);
    }
}
