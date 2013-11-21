package controllers.root.student_portal.student.departments.department.faculty.professor;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.departments.department.faculty.professor.*;
import views.html.helpers.*;

public class Professor extends Controller{

    public static String url(final int studentId, final String department,
                             final int professorId) {
        return controllers.root.student_portal.student.departments.department.
                faculty.professor.routes.Professor.get(studentId, department,
                professorId).url();
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
