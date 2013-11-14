package controllers.root.admin.course_schedules.semester;

import controllers.resources.OneSemesterResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Semester extends Controller {

    public static String url(final String seasonAndYear) {
        return controllers.root.admin.course_schedules.semester.routes.
                Semester.get(seasonAndYear).url();
    }

    public static Result get(final String seasonAndYear) {

        return OneSemesterResource.course_schedule_get(seasonAndYear);
    }
}
