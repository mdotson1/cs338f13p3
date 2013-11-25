package controllers.root.student_login.student_login.course_schedules.semester.department;

import controllers.root.Resource;
import models.course.Course;
import models.course.Semester.Season;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.course_schedules.semester.department.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Department extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department) {
        return controllers.root.student_login.student_login.course_schedules.
                semester.department.routes.Department.get(studentId,
                seasonAndYear, department).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String dept) throws SQLException {

        final String context = Department.url(studentId, seasonAndYear, dept);

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<Course> courses = CourseOfferingRepository.getInstance().
                coursesBySemesterDepartment(season, year, dept);

        return ok(department.render(courses, context, Resource.BACK_LINK(context),
                seasonAndYear, dept));
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department) {

        try {
            return render(studentId, seasonAndYear, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
