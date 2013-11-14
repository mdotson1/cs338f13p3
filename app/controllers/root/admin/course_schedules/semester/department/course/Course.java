package controllers.root.admin.course_schedules.semester.department.course;

import controllers.resources.OneCourseResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Course extends Controller {

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.admin.course_schedules.semester.department.
                course.routes.Course.get(seasonAndYear, department, courseNum).
                url();
    }

    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum) {

        return OneCourseResource.course_schedules_get(seasonAndYear, department,
                courseNum);
    }

    public static Result post(final String seasonAndYear,
                              final String department, final String courseNum) {
        return OneCourseResource.course_schedules_post(seasonAndYear, department,
                courseNum);
    }
}
