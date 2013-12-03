package controllers.root.student_login.student_portal.payments;

import controllers.root.Resource;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.forms.payment.PaymentForm1;
import models.person.Payment;
import models.person.Student;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.payments.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Payments extends Controller {

    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.payments.routes.
                Payments.get(studentId).url();
    }

    private static Call postCall(final int studentId) {
        return controllers.root.student_login.student_portal.payments.routes.
                Payments.post(studentId);
    }

    private static Result render(final int studentId, final boolean create)
            throws SQLException {

        final String context = Payments.url(studentId);

        if (create) {

            final Form<PaymentForm1> form = Form.form(PaymentForm1.class).
                    bindFromRequest();

            final Iterator<Payment> paymentsByStudent =
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId);

            if(form.hasErrors()) {
                return badRequest(payments.render(paymentsByStudent, context,
                        form, studentId, Resource.BACK_LINK(context),
                        Payments.postCall(studentId)));
            }

            final Payment p = form.get().toPayment();

            final int paymentId = PaymentRepository.getInstance().add(p);

            final Student s = StudentRepository.getInstance().
                    findById(studentId);

            double newBalance = s.getCurrentBalance() - p.getPaymentAmount();

            StudentRepository.getInstance().updateBalance(studentId,
                    newBalance);

            PaymentHistoryRepository.getInstance().add(studentId, paymentId);
        }

        final Iterator<Payment> paymentsByStudent = PaymentHistoryRepository.
                getInstance().findAllPaymentsByStudent(studentId);

        return ok(payments.render(paymentsByStudent, context,
                Form.form(PaymentForm1.class), studentId,
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
