package controllers.root.student_login.student_login.course_schedules.semester.department.course;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.course_schedules.semester.department.course.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Course extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.student_login.student_login.course_schedules.
                semester.department.course.routes.Course.get(studentId,
                seasonAndYear, department, courseNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum) throws SQLException {

        final String context = Course.url(studentId, seasonAndYear, department,
                courseNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<CourseOffering> courses = CourseOfferingRepository.getInstance().
                findAllSections(season, year, department, Short.parseShort(courseNum));

        return ok(course.render(courses, context, Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum) {

        try {
            return render(studentId, seasonAndYear, department, courseNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
