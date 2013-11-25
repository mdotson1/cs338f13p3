package controllers.root.student_login.student_portal.departments.department.faculty;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.department.faculty.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Faculty extends Controller {

    public static String url(final int studentId, final String department) {
        return controllers.root.student_login.student_portal.departments.department.
                faculty.routes.Faculty.get(studentId, department).url();
    }

    private static Result render(final int studentId, final String department)
            throws SQLException {

        final String context = Faculty.url(studentId, department);

        final Iterator<Professor> profs = ProfessorRepository.getInstance().
                getFaculty(department);
        return ok(faculty.render(profs, context, Resource.BACK_LINK(context),
                department));
    }

    public static Result get(final int studentId, final String department) {

        try {
            return render(studentId, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
