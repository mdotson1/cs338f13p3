package controllers.root.admin_portal.departments.department.faculty.professor.semester;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semester extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear) {
        return controllers.root.admin_portal.departments.department.faculty.
                professor.semester.routes.Semester.get(dept, professorId,
                        seasonAndYear).url();
    }

    private static Result render(final String department, final int professorId,
                                 final String seasonAndYear) throws SQLException
    {
        final String context = Semester.url(department, professorId,
                seasonAndYear);

        final String[] split = seasonAndYear.split(" ");
        final models.course.Semester.Season season =
                models.course.Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<CourseOffering> cos = CoursesTeachingRepository.
                getInstance().getCoursesTaughtByProfessorForSemester(
                professorId, season, year);

        final Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        return ok(semester.render(cos, context, Resource.BACK_LINK(context),
                prof, seasonAndYear));
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear) {

        try {
            return render(department, professorId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
