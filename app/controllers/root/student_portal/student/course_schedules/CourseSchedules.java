package controllers.root.student_portal.student.course_schedules;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.course_schedules.*;
import views.html.helpers.*;

public class CourseSchedules extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_portal.student.course_schedules.
                routes.CourseSchedules.get(studentId).url();
    }

    private static Result render(final int studentId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId) {

        return render(studentId);
    }
}
