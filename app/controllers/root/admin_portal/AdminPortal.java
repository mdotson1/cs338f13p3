package controllers.root.admin_portal;

import controllers.root.Resource;
import controllers.root.admin_portal.course_schedules.CourseSchedules;
import controllers.root.admin_portal.departments.Departments;
import controllers.root.admin_portal.students.Students;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.*;
import views.html.helpers.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminPortal extends Controller {

    public static String url() {
        return controllers.root.admin_portal.routes.AdminPortal.get().url();
    }

    private static Result render() {

        final String context = AdminPortal.url();

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
