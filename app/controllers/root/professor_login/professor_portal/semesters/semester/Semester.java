package controllers.root.professor_login.professor_portal.semesters.semester;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.professor_login.professor_portal.semesters.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semester extends Controller {

    public static String url(final int professorId, final String seasonAndYear)
    {
        return controllers.root.professor_login.professor_portal.semesters.
                semester.routes.Semester.get(professorId, seasonAndYear).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear)
            throws SQLException {

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String context = Semester.url(professorId, seasonAndYear);

        final Iterator<CourseOffering> courses = CoursesTeachingRepository.
                getInstance().getCoursesTaughtByProfessorForSemester(
                professorId, season, year);

        final Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        return ok(semester.render(courses, context, Resource.BACK_LINK(context),
                prof, seasonAndYear));
    }

    public static Result get(final int professorId, final String seasonAndYear)
    {
        try {
            return render(professorId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
