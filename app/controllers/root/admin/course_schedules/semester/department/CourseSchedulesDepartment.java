package controllers.root.admin.course_schedules.semester.department;

import controllers.resources.DepartmentsResource;
import controllers.resources.OneDepartmentResource;
import play.mvc.Controller;
import play.mvc.Result;

public class CourseSchedulesDepartment extends Controller {

    public static String url(final String seasonAndYear,
                             final String department) {
        return controllers.root.admin.course_schedules.semester.department.
                routes.CourseSchedulesDepartment.get(seasonAndYear, department).
                url();
    }

    public static Result get(final String seasonAndYear,
                             final String department) {

        return OneDepartmentResource.course_schedules_get(seasonAndYear,
                department);
    }
}
