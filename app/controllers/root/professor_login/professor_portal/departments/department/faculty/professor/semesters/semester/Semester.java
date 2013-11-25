package controllers.root.professor_login.professor_portal.departments.department.faculty.professor.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.professor.semesters.semester.*;
import views.html.helpers.*;

public class Semester extends Controller {

    public static String url(final int thisId, final String department,
                             final int professorId, final String seasonAndYear)
    {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.professor.semesters.semester.routes.Semester.
                get(thisId, department, professorId, seasonAndYear).url();
    }

    private static Result render(final int thisId, final String department,
                                 final int professorId,
                                 final String seasonAndYear) {
        // TODO
        return ok();
    }

    public static Result get(final int thisId, final String department,
                             final int professorId, final String seasonAndYear)
    {
        return render(thisId, department, professorId, seasonAndYear);
    }
}
