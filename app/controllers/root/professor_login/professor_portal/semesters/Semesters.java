package controllers.root.professor_login.professor_portal.semesters;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.semesters.*;
import views.html.helpers.*;

public class Semesters extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_login.professor_portal.semesters.
                routes.Semesters.get(professorId).url();
    }

    private static Result render(final int professorId) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId) {

        return render(professorId);
    }
}
