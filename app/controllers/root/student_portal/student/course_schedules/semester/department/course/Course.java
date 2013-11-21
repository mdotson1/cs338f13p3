package controllers.root.student_portal.student.course_schedules.semester.department.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.course_schedules.semester.department.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.student_portal.student.course_schedules.
                semester.department.course.routes.Course.get(studentId,
                seasonAndYear, department, courseNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum) {

        return render(studentId, seasonAndYear, department, courseNum);
    }
}
