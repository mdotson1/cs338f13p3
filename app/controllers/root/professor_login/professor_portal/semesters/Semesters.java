package controllers.root.professor_login.professor_portal.semesters;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import models.person.Student;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.semesters.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semesters extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_login.professor_portal.semesters.
                routes.Semesters.get(professorId).url();
    }

    private static Result render(final int professorId) throws SQLException {

        final String context = Semesters.url(professorId);

        final Iterator<Semester> sems = CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId);

        final Professor prof =
                ProfessorRepository.getInstance().findById(professorId);

        return ok(semesters.render(sems, context, Resource.BACK_LINK(context),
                prof));
    }

    public static Result get(final int professorId) {
        try {
            return render(professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
