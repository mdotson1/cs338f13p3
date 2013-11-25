package controllers.root.admin_portal.departments.department.faculty.professor;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professor extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin_portal.departments.department.faculty.professor
                .routes.Professor.get(dept, professorId).url();
    }

    private static Result render(final String department, final int professorId)
            throws SQLException {

        final String context = Professor.url(department, professorId);

        final models.person.Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        final Iterator<Semester> sems = CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId);

        return ok(professor.render(prof, context, null,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final String department, final int professorId) {

        try {
            return render(department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
