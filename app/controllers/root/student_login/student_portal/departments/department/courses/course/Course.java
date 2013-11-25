package controllers.root.student_login.student_portal.departments.department.courses.course;

import controllers.root.Resource;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.department.courses.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    public static String url(final int studentId, final String department,
                             final String courseNum) {
        return controllers.root.student_login.student_portal.departments.department.
                courses.course.routes.Course.get(studentId, department,
                courseNum).url();
    }

    private static Result render(final int studentId, final String dept,
                                 final String courseNum)
            throws SQLException {

        final String context = Course.url(studentId, dept, courseNum);
        final short courseNumber = Short.parseShort(courseNum);

        final models.course.Course one_course = CourseRepository.getInstance().
                findById(dept, courseNumber);

        return ok(course.render(one_course, Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId, final String dept,
                             final String courseNum) {

        try {
            return render(studentId, dept, courseNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
