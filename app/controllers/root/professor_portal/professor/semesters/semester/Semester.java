package controllers.root.professor_portal.professor.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.semesters.semester.*;
import views.html.helpers.*;

public class Semester extends Controller {

    public static String url(final int professorId, final String seasonAndYear)
    {
        return controllers.root.professor_portal.professor.semesters.semester.
                routes.Semester.get(professorId, seasonAndYear).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId, final String seasonAndYear)
    {
        return render(professorId, seasonAndYear);
    }
}
