package controllers.resources;

import play.mvc.*;
import views.html.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class AdminResource {

    public static Result get(Map<String,String> backLink) {

        Map<String,String> namesAndURLs = new LinkedHashMap<String, String>();
        namesAndURLs.put("All Students", controllers.root.admin.students.
                routes.Students.get().url());
        namesAndURLs.put("All Course Schedules", controllers.root.admin.
                course_schedules.routes.CourseSchedules.get().url());
        namesAndURLs.put("All Departments", controllers.root.admin.departments.
                routes.Departments.get().url());
        namesAndURLs.putAll(backLink);

        return ok(admin.render(namesAndURLs));
    }
}
