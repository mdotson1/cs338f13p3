package controllers.root.admin.departments.department.faculty.professor;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Professor extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin.departments.department.faculty.professor
                .routes.Professor.get(dept, professorId).url();
    }

    private static Result render(final String department, final int professorId)
            throws SQLException {

        final String context = Professor.url(department, professorId);

        return ok(professor.render(
                ProfessorRepository.getInstance().findById(professorId),
                context, null, Resource.BACK_LINK(context)));
    }

    public static Result get(final String department, final int professorId) {

        try {
            return render(department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
