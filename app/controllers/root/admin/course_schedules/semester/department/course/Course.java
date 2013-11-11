package controllers.root.admin.course_schedules.semester.department.course;

import play.mvc.Controller;
import play.mvc.Result;

public class Course extends Controller {
    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum) {

        return ok();
    }
}
