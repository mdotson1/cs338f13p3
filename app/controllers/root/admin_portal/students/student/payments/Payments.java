package controllers.root.admin_portal.students.student.payments;

import controllers.root.Resource;
import controllers.services.Bursar;
import controllers.services.PaymentService;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.payments.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Payments extends Controller {

    private final static Form<Payment> PAYMENT_FORM = Form.form(Payment.class);

    public static Call postCall(final int studentId) {
        return controllers.root.admin_portal.students.student.payments.routes.Payments.
                post(studentId);
    }

    public static String url(final int studentId) {
        return controllers.root.admin_portal.students.student.payments.routes.Payments.
                get(studentId).url();
    }

    private static Result render(final boolean create, final int studentId)
            throws SQLException {

        final String context = Payments.url(studentId);
        final Form<Payment> form;

        if (create) {

            form = PAYMENT_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            int paymentId = PaymentService.createPayment(form.data());

            Bursar.payBalance(studentId, paymentId);
        } else {
            form = PAYMENT_FORM;
        }
        return ok(payments.render(PaymentHistoryRepository.getInstance().
                findAllPaymentsByStudent(studentId), context, form, studentId,
                Resource.BACK_LINK(context), postCall(studentId)));
    }

    public static Result get(final int studentId) {

        try {
            return render(false, studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final int studentId) {
        try {
            return render(true, studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
