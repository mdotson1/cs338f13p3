package controllers.root.professor_portal.professor.departments;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.departments.*;
import views.html.helpers.*;

public class Departments extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_portal.professor.departments.routes.
                Departments.get(professorId).url();
    }

    private static Result render(final int professorId) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId) {

        return render(professorId);
    }
}
