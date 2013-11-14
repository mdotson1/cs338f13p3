package controllers.root.admin.departments.department.courses;

import controllers.resources.CoursesResource;
import controllers.root.admin.departments.department.Department;
import play.mvc.Controller;
import play.mvc.Result;

public class Courses extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.courses.routes.
                Courses.get(dept).url();
    }

    public static Result get(final String dept) {

        return CoursesResource.course_catalog_get(dept);
    }

    public static Result post(final String dept) {
        return CoursesResource.course_catalog_post(dept);
    }
}
