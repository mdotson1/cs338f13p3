package controllers.root.student_login.student_portal.semesters.semester;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import models.person.Student;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.student_login.student_portal.semesters.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semester extends Controller {
    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.student_login.student_portal.semesters.semester.
                routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear)
            throws SQLException {

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String context = Semester.url(studentId, seasonAndYear);

        final Iterator<CourseOffering> courses = CoursesTakingRepository.
                getInstance().getCoursesTakingByStudentForSemester(studentId,
                season, year);

        final Student stu = StudentRepository.getInstance().findById(studentId);

        return ok(semester.render(courses, context, Resource.BACK_LINK(context),
                stu, seasonAndYear));
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        try {
            return render(studentId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
