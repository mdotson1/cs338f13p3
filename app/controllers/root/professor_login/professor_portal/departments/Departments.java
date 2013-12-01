package controllers.root.professor_login.professor_portal.departments;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Departments extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_login.professor_portal.departments.
                routes.Departments.get(professorId).url();
    }

    private static Result render(final int professorId) throws SQLException {

        final String context = Departments.url(professorId);

        final Iterator<String> allDepts = ProfessorRepository.getInstance().
                allDepartments();

        return ok(departments.render(allDepts, context,
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
