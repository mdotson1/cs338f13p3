package controllers.root.admin_portal.students.student.payments.payment;

import controllers.root.Resource;
import models.database.dao.concrete.PaymentRepository;
import models.person.Professor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.payments.payment.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Payment extends Controller {

    public static String url(final int studentId, final int paymentId) {
        return controllers.root.admin_portal.students.student.payments.payment.routes.
                Payment.get(studentId, paymentId).url();
    }

    private static Result render(final int studentId, final int paymentId)
            throws SQLException {

        final String context = Payment.url(studentId, paymentId);
        final Form<Professor> form;

        return ok(payment.render(
                PaymentRepository.getInstance().findById(paymentId),
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId, final int paymentId) {

        try {
            return render(studentId, paymentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
