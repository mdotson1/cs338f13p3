package controllers.root.professor_portal;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.professor.*;
import views.html.helpers.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorPortal extends Controller {

    public static String url() {
        return controllers.root.professor_portal.routes.ProfessorPortal.get().url();
    }

    private static Result render() {
        final String context = ProfessorPortal.url();

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("All Course Schedules", CourseSchedules.url());
        namesAndURLs.put("All Departments", Departments.url());

        return ok(professor.render(namesAndURLs, Resource.BACK_LINK(context)));
    }

    public static Result get() {
        return render();
    }
}
