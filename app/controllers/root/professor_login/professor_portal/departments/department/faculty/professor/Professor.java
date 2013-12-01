package controllers.root.professor_login.professor_portal.departments.department.faculty.professor;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.professor.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professor extends Controller {

    public static String url(final int thisId, final String department,
                             final int professorId) {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.professor.routes.Professor.get(thisId,
                department, professorId).url();
    }

    private static Result render(final int thisId, final String department,
                                 final int professorId) throws SQLException {

        final String context = Professor.url(thisId, department, professorId);

        final models.person.Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        final Iterator<Semester> sems = CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId);

        return ok(professor.render(prof, context, sems,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int thisId, final String department,
                             final int professorId) {

        try {
            return render(thisId, department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
