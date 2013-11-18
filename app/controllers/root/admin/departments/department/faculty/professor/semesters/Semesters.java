package controllers.root.admin.departments.department.faculty.professor.semesters;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semesters.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Semesters extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.routes.Semesters.get(dept,
                        professorId).url();
    }

    private static Result render(final String department, final int professorId)
            throws SQLException {

        final String context = Semesters.url(department, professorId);

        return ok(semesters.render(CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId), context,
                Resource.BACK_LINK(context),
                ProfessorRepository.getInstance().findById(professorId)));
    }

    public static Result get(final String department, final int professorId) {

        final String context = Semesters.url(department, professorId);

        try {
            return render(department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
