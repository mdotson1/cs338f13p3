package controllers.root.student_login.student_portal.registration;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.registration.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Registration extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.registration.routes.
                Registration.get(studentId).url();
    }

    private static Result render(final int studentId) throws SQLException {

        final String context = Registration.url(studentId);

        final Iterator<Semester> sems = SemesterRepository.getInstance().
                getAll();

        return ok(registration.render(sems, context, Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId) {
        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
