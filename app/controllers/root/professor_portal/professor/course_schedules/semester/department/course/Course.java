package controllers.root.professor_portal.professor.course_schedules.semester.department.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.course_schedules.semester.department.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int professorId,final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.professor_portal.professor.course_schedules.
                semester.department.course.routes.Course.get(professorId,
                seasonAndYear, department, courseNum).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId,final String seasonAndYear,
                             final String department, final String courseNum) {

        return render(professorId, seasonAndYear, department, courseNum);
    }
}
