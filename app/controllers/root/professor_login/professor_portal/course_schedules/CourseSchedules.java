package controllers.root.professor_login.professor_portal.course_schedules;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.course_schedules.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class CourseSchedules extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                routes.CourseSchedules.get(professorId).url();
    }

    private static Result render(final int professorId) throws SQLException {

        final String context = CourseSchedules.url(professorId);

        final Iterator<Semester> semesters = CoursesTeachingRepository.
                getInstance().allSemestersProfessorTaughtIn(professorId);

        return ok(course_schedules.render(semesters, context,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId) {

        try {
            return render(professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}