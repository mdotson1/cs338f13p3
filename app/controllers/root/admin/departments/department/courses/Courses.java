package controllers.root.admin.departments.department.courses;

import controllers.resources.CoursesResource;
import controllers.root.admin.departments.Departments;
import controllers.root.admin.departments.department.Department;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Courses extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.courses.routes.
                Courses.get(dept).url();
    }

    private static Map<String,String> GENERATE_BACK_LINK(final String dept) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Department.url(dept));
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final String dept) {

        return CoursesResource.get(dept, GENERATE_BACK_LINK(dept),
                Courses.url(dept));
    }

    public static Result post(final String dept) {
        return CoursesResource.post(dept, GENERATE_BACK_LINK(dept),
                Courses.url(dept));
    }
}
