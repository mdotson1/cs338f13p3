package controllers.root.professor_portal.professor.departments.department.faculty.professor.semesters;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.departments.department.faculty.professor.semesters.*;
import views.html.helpers.*;

public class Semesters extends Controller {

    public static String url(final int thisId, final String department,
                             final int professorId) {
        return controllers.root.professor_portal.professor.departments.
                department.faculty.professor.semesters.routes.Semesters.get(
                thisId, department, professorId).url();
    }

    private static Result render(final int thisId, final String department,
                                 final int professorId) {
        // TODO
        return ok();
    }

    public static Result get(final int thisId, final String department,
                             final int professorId) {

        return render(thisId, department, professorId);
    }
}
