package controllers.root.professor_login.professor_portal.departments.department.courses;

import controllers.root.Resource;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.courses.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Courses extends Controller {

    public static String url(final int professorId, final String department) {
        return controllers.root.professor_login.professor_portal.departments.
                department.courses.routes.Courses.get(professorId, department).
                url();
    }

    private static Result render(final int professorId, final String dept)
            throws SQLException {

        final String context = Courses.url(professorId, dept);

        final Iterator<Course> coursesByDept = CourseRepository.getInstance().
                getAllByDepartment(dept);
        return ok(courses.render(coursesByDept, context, dept,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId, final String dept) {

        try {
            return render(professorId, dept);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
