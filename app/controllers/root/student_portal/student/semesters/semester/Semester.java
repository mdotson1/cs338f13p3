package controllers.root.student_portal.student.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.semesters.semester.*;
import views.html.helpers.*;

public class Semester extends Controller {
    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.student_portal.student.semesters.semester.
                routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        return render(studentId, seasonAndYear);
    }
}