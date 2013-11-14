package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.root.admin.departments.Departments;
import controllers.root.admin.students.Students;
import play.mvc.*;
import views.html.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class AdminResource {

    public static Result admin_get() {

        final String context = Admin.url();

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("All Students", Students.url());
        namesAndURLs.put("All Section Schedules", CourseSchedules.url());
        namesAndURLs.put("All Departments", Departments.url());

        return ok(admin.render(namesAndURLs, Resource.BACK_LINK(context)));
    }
}
