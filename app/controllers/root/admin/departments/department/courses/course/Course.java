package controllers.root.admin.departments.department.courses.course;

import controllers.resources.OneCourseResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Course extends Controller {

    public static String url(final String dept, final String courseNum) {
        return controllers.root.admin.departments.department.courses.course.
                routes.Course.get(dept, courseNum).url();
    }

    public static Result get(final String dept, final String courseNum) {

        return OneCourseResource.departments_get(dept, courseNum);
    }
}
