package controllers.root.professor_portal.professor;

import controllers.root.Resource;
import controllers.root.professor_portal.professor.course_schedules.CourseSchedules;
import controllers.root.professor_portal.professor.departments.Departments;
import controllers.root.professor_portal.professor.semesters.Semesters;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_portal.professor.*;
import views.html.helpers.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Professor extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_portal.professor.routes.Professor.
                get(professorId).url();
    }

    private static Result render(final int professorId) {
        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();

        final String context = Professor.url(professorId);

        namesAndURLs.put("All Course Schedules", CourseSchedules.url(professorId));
        namesAndURLs.put("All Departments", Departments.url(professorId));
        namesAndURLs.put("Courses Teaching", Semesters.url(professorId));

        return ok(professor.render(namesAndURLs, Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId) {

        return render(professorId);
    }
}
