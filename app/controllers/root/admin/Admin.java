package controllers.root.admin;

import controllers.root.Resource;
import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.root.admin.departments.Departments;
import controllers.root.admin.students.Students;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.*;
import views.html.helpers.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Admin extends Controller {

    public static String url() {
        return controllers.root.admin.routes.Admin.get().url();
    }

    private static Result render() {

        final String context = Admin.url();

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("All Students", Students.url());
        namesAndURLs.put("All Course Schedules", CourseSchedules.url());
        namesAndURLs.put("All Departments", Departments.url());

        return ok(admin.render(namesAndURLs, Resource.BACK_LINK(context)));
    }

    public static Result get() {
        return render();
    }
}
