package controllers.root.admin.course_schedules;

import controllers.resources.CourseSchedulesResource;
import controllers.resources.SemestersResource;
import play.mvc.Controller;
import play.mvc.Result;

public class CourseSchedules extends Controller {

    public static String url() {
        return controllers.root.admin.course_schedules.routes.CourseSchedules.
                get().url();
    }

    public static Result get() {

        return CourseSchedulesResource.course_schedules_get();
    }

    public static Result post() {

        return CourseSchedulesResource.course_schedules_post();
    }
}
