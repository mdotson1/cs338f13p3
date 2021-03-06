package controllers.root.professor_login.professor_portal.departments.department;

import controllers.root.Resource;
import controllers.root.professor_login.professor_portal.departments.department.courses.Courses;
import controllers.root.professor_login.professor_portal.departments.department.faculty.Faculty;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Department extends Controller {

    public static String url(final int professorId, final String department) {
        return controllers.root.professor_login.professor_portal.departments.
                department.routes.Department.get(professorId, department).url();
    }

    private static Result render(final int professorId, final String dept)
    {
        final String context = Department.url(professorId, dept);

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("Faculty Pages", Faculty.url(professorId, dept));
        namesAndURLs.put("Course Catalog", Courses.url(professorId, dept));

        return ok(department.render(dept, namesAndURLs,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId, final String department) {
        return render(professorId, department);
    }
}
