package controllers.root.professor_login.professor_portal.course_schedules.semester.department;

import controllers.root.Resource;
import models.course.Course;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.course_schedules.semester.department.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Department extends Controller {

    public static String url(final int professorId,final String seasonAndYear,
                             final String department) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                semester.department.routes.Department.get(professorId,
                seasonAndYear, department).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String dept) throws SQLException {

        final String context = Department.url(professorId, seasonAndYear,
                dept);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<Course> courses = CourseOfferingRepository.getInstance().
                coursesBySemesterDepartment(season, year, dept);

        return ok(department.render(courses, context, Resource.BACK_LINK(context),
                seasonAndYear, dept));
    }

    public static Result get(final int professorId,final String seasonAndYear,
                             final String department) {

        try {
            return render(professorId, seasonAndYear, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
