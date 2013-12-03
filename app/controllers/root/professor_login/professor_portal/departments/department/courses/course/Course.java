package controllers.root.professor_login.professor_portal.departments.department.courses.course;

import controllers.root.Resource;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.courses.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    public static String url(final int professorId, final String department,
                             final String courseNum) {
        return controllers.root.professor_login.professor_portal.departments.
                department.courses.course.routes.Course.get(professorId,
                department, courseNum).url();
    }

    private static Result render(final int professorId, final String dept,
                                 final String courseNum) throws SQLException {

        final String context = Course.url(professorId, dept, courseNum);
        final short courseNumber = Short.parseShort(courseNum);

        final models.course.Course one_course = CourseRepository.getInstance().
                findById(dept, courseNumber);

        return ok(course.render(one_course, Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId, final String dept,
                             final String courseNum) {

        try {
            return render(professorId, dept, courseNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
