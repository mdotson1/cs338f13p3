package controllers.root.professor_login.professor_portal.departments.department.faculty;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.*;
import views.html.helpers.*;

public class Faculty extends Controller {

    public static String url(final int professorId, final String department) {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.routes.Faculty.get(professorId, department).
                url();
    }

    private static Result render(final int professorId, final String department)
    {
        // TODO
        return ok();
    }

    public static Result get(final int professorId, final String department) {

        return render(professorId, department);
    }
}
