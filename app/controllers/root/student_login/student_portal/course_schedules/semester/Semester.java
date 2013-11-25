package controllers.root.student_login.student_login.course_schedules.semester;

import controllers.root.Resource;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.student_loginlogin.student_portal.course_schedules.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semester extends Controller {

    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.student_login.student_login.course_schedules.
                semester.routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Result render(final int studentId, final String seasonAndYear)
            throws SQLException {

        final String context = Semester.url(studentId, seasonAndYear);

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<String> depts = CourseOfferingRepository.getInstance().
                departmentsOfferingCoursesBySemester(season, year);

        return ok(semester.render(depts, context, Resource.BACK_LINK(context),
                seasonAndYear));
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        try {
            return render(studentId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
