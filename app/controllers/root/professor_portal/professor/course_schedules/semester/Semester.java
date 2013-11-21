package controllers.root.professor_portal.professor.course_schedules.semester;

import controllers.root.Resource;
import controllers.services.CourseOfferingService;
import models.database.dao.concrete.CourseOfferingRepository;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.professor_portal.professor.course_schedules.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semester extends Controller {

    public static String url(final int professorId, final String seasonAndYear)
    {
        return controllers.root.professor_portal.professor.course_schedules.
                semester.routes.Semester.get(professorId, seasonAndYear).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear)
            throws SQLException {

        final String context = Semester.url(professorId, seasonAndYear);

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<String> departments = CourseOfferingRepository.
                getInstance().departmentsOfferingCoursesBySemester(season,
                year);

        return ok(semester.render(departments, context,
                Resource.BACK_LINK(context), seasonAndYear));
    }

    public static Result get(final int professorId,final String seasonAndYear) {

        try {
            return render(professorId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
