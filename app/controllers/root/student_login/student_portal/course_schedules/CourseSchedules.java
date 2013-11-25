package controllers.root.student_login.student_portal.course_schedules;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.course_schedules.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class CourseSchedules extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.course_schedules.
                routes.CourseSchedules.get(studentId).url();
    }

    private static Result render(final int studentId) throws SQLException {

        final String context = CourseSchedules.url(studentId);

        final Iterator<Semester> semesters = SemesterRepository.getInstance().
                getAll();

        return ok(course_schedules.render(semesters, context,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId) {

        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
