package controllers.root.admin_portal.departments.department.faculty.professor.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semesters.semester.*;
import views.html.helpers.*;

public class Semester extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear) {
        return controllers.root.admin_portal.departments.department.faculty.professor
                .semesters.semester.routes.Semester.get(dept, professorId,
                        seasonAndYear).url();
    }

    private static Result render(final String department, final int professorId,
                                 final String seasonAndYear) {

        // TODO
        return ok();
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear) {

        return render(department, professorId, seasonAndYear);
    }
}
