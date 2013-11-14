package controllers.resources;

import controllers.root.admin.course_schedules.semester.department.CourseSchedulesDepartment;
import controllers.root.admin.departments.department.Department;
import controllers.root.admin.departments.department.courses.Courses;
import controllers.root.admin.departments.department.faculty.Faculty;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class OneDepartmentResource {

    public static Result departments_get(final String dept) {

        final String context = Department.url(dept);

        Map<String,String> namesAndURLs = new LinkedHashMap<String, String>();
        namesAndURLs.put("Faculty Pages", Faculty.url(dept));
        namesAndURLs.put("Section Catalog", Courses.url(dept));

        return ok(one_department.render(dept, namesAndURLs,
                Resource.BACK_LINK(context)));
    }

    public static Result course_schedules_get(final String seasonAndYear,
                                              final String department) {

        final String context = CourseSchedulesDepartment.url(seasonAndYear, department);

        /*
        try {
            return ok(one_department.render(dept, namesAndURLs,
                    Resource.BACK_LINK(context));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
        */
        return ok();

    }
}
