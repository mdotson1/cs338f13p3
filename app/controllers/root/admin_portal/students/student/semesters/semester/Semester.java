package controllers.root.admin_portal.students.student.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.semesters.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Semester extends Controller {

    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.admin_portal.students.student.semesters.semester.
                routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear) throws SQLException
    {
        final String context = Semester.url(studentId, seasonAndYear);

        /*
        // TODO
         return ok(payments.render(PaymentHistoryRepository.getInstance().
                findAllPaymentsByStudent(studentId), context, form, studentId,
                Resource.BACK_LINK(context), postCall(studentId)));
         */
         return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        try {
            return render(studentId, seasonAndYear);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
