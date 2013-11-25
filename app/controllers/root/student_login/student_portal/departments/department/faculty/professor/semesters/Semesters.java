package controllers.root.student_login.student_portal.departments.department.faculty.professor.semesters;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.department.faculty.professor.semesters.*;
import views.html.helpers.*;

public class Semesters extends Controller {

    public static String url(final int studentId, final String department,
                             final int professorId) {
        return controllers.root.student_login.student_portal.departments.department.
                faculty.professor.semesters.routes.Semesters.get(studentId,
                department, professorId).url();
    }

    private static Result render(final int studentId, final String department,
                                 final int professorId) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String department,
                             final int professorId) {

        return render(studentId, department, professorId);
    }
}
