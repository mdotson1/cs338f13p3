package controllers.root.admin_portal.students.student.payments;

import controllers.root.Resource;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.payments.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Payments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin_portal.students.student.payments.routes.Payments.
                get(studentId).url();
    }

    private static Result render(final int studentId) throws SQLException {

        final String context = Payments.url(studentId);
        final Form<Payment> form;

        final Iterator<Payment> paymentsByStudent = PaymentHistoryRepository.getInstance().
                findAllPaymentsByStudent(studentId);
        return ok(payments.render(paymentsByStudent, context, studentId,
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
