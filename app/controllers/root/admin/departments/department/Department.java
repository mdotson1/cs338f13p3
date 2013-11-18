package controllers.root.admin.departments.department;

import controllers.root.Resource;
import controllers.root.admin.departments.department.courses.Courses;
import controllers.root.admin.departments.department.faculty.Faculty;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Department extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.routes.Department.
                get(dept).url();
    }

    private static Result render(final String dept)
            throws SQLException {

        final String context = Department.url(dept);

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("Faculty Pages", Faculty.url(dept));
        namesAndURLs.put("Course Catalog", Courses.url(dept));

        return ok(department.render(dept, namesAndURLs,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final String dept) {
        try {
            return render(dept);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
