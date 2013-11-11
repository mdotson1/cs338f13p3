package controllers.root.admin.course_schedules.semester.department.course.section;

import play.mvc.Controller;
import play.mvc.Result;

public class Section extends Controller {
    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        return ok();
    }
}
