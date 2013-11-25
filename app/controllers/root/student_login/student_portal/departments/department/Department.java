package controllers.root.student_login.student_portal.departments.department;

import controllers.root.Resource;
import controllers.root.student_login.student_portal.departments.department.courses.Courses;
import controllers.root.student_login.student_portal.departments.department.faculty.Faculty;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.department.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Department extends Controller {

    public static String url(final int studentId, final String department) {
        return controllers.root.student_login.student_portal.departments.
                department.routes.Department.get(studentId, department).url();
    }

    private static Result render(final int studentId, final String dept)
            throws SQLException {

        final String context = Department.url(studentId, dept);

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("Faculty Pages", Faculty.url(studentId, dept));
        namesAndURLs.put("Course Catalog", Courses.url(studentId, dept));

        return ok(department.render(dept, namesAndURLs,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId, final String dept) {
        try {
            return render(studentId, dept);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
