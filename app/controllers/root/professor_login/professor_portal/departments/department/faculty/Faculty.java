package controllers.root.professor_login.professor_portal.departments.department.faculty;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Faculty extends Controller {

    public static String url(final int professorId, final String department) {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.routes.Faculty.get(professorId, department).
                url();
    }

    private static Result render(final int professorId, final String dept)
            throws SQLException {

        final String context = Faculty.url(professorId, dept);

        final Iterator<Professor> profs = ProfessorRepository.getInstance().
                getFaculty(dept);

        return ok(faculty.render(profs, context, Resource.BACK_LINK(context),
                dept));
    }

    public static Result get(final int professorId, final String department) {

        try {
            return render(professorId, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
