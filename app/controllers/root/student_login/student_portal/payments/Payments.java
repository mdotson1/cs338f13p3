package controllers.root.student_login.student_login.payments;

import controllers.root.Resource;
import controllers.services.PaymentService;
import controllers.services.StudentPaymentService;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_loginlogin.student_portal.payments.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Payments extends Controller {

    private final static Form<Payment> PAYMENT_FORM =
            Form.form(Payment.class);

    public static String url(final int studentId) {
        return controllers.root.student_login.student_login.payments.routes.
                Payments.get(studentId).url();
    }

    private static Call postCall(final int studentId) {
        return controllers.root.student_login.student_login.payments.routes.
                Payments.post(studentId);
    }

    private static Result render(final int studentId, final boolean create)
            throws SQLException {

        final String context = Payments.url(studentId);
        final Form<Payment> form;

        if (create) {

            form = PAYMENT_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            final int paymentId = PaymentService.createPayment(form.data());
            StudentPaymentService.payBalance(studentId, paymentId);
        } else {
            form = PAYMENT_FORM;
        }
        final Iterator<Payment> paymentsByStudent = PaymentHistoryRepository.
                getInstance().findAllPaymentsByStudent(studentId);

        return ok(payments.render(paymentsByStudent, context, form, studentId,
                Resource.BACK_LINK(context), Payments.postCall(studentId)));
    }

    public static Result get(final int studentId) {

        try {
            return render(studentId, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final int studentId) {

        try {
            return render(studentId, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
