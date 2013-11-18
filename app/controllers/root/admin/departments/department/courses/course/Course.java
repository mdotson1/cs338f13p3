package controllers.root.admin.departments.department.courses.course;

import controllers.root.Resource;
import controllers.root.admin.course_schedules.CourseSchedules;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.courses.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    public static String url(final String dept, final String courseNum) {
        return controllers.root.admin.departments.department.courses.course.
                routes.Course.get(dept, courseNum).url();
    }

    private static Result render(final String dept, final String courseNum)
            throws SQLException {

        final String course_schedules = CourseSchedules.url();
        final String context = Course.url(dept, courseNum);
        final short courseNumber = Short.parseShort(courseNum);

        return ok(course.render(CourseRepository.getInstance().findById(
                dept, courseNumber), course_schedules,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final String dept, final String courseNum) {

        try {
            return render(dept, courseNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
