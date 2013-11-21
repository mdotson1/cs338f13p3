package controllers.root.professor_portal.professor.course_schedules.semester.department;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.course_schedules.semester.department.*;
import views.html.helpers.*;

public class Department extends Controller {

    public static String url(final int professorId,final String seasonAndYear,
                             final String department) {
        return controllers.root.professor_portal.professor.course_schedules.
                semester.department.routes.Department.get(professorId,
                seasonAndYear, department).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String department) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId,final String seasonAndYear,
                             final String department) {

        return render(professorId, seasonAndYear, department);
    }
}
