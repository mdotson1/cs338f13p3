package controllers.root.student_login.student_portal.departments;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Departments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.departments.routes.
                Departments.get(studentId).url();
    }

    private static Result render(final int studentId) throws SQLException {

        final String context = Departments.url(studentId);

        final Iterator<String> allDepts = ProfessorRepository.getInstance().
                allDepartments();

        return ok(departments.render(allDepts, context,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId) {

        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
