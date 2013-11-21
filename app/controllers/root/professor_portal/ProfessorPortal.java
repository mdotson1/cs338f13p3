package controllers.root.professor_portal;

import controllers.root.professor_portal.professor.course_schedules.CourseSchedules;
import controllers.root.professor_portal.professor.departments.Departments;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.professor_portal.*;
import views.html.helpers.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorPortal extends Controller {

    public static String url() {
        return controllers.root.professor_portal.routes.ProfessorPortal.get().
                url();
    }

    private static Result render() {
        final String context = ProfessorPortal.url();

        // TODO

        return ok();
    }

    public static Result get() {
        return render();
    }
}
