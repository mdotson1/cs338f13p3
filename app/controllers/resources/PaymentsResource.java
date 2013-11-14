package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.students.student.payments.Payments;
import controllers.services.Bursar;
import controllers.services.PaymentService;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class PaymentsResource {

    private final static Form<Payment> PAYMENT_FORM = Form.form(Payment.class);

    public static Result payments_get(final int studentId) {

        final String context = Payments.url(studentId);

        try {
            return ok(payments.render(PaymentHistoryRepository.
                    getInstance().findAllPaymentsByStudent(studentId),
                    context, PAYMENT_FORM, studentId,
                    Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result payments_post(final int studentId) {

        final String context = Payments.url(studentId);

        final Form<Payment> filledForm = PAYMENT_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            int paymentId = PaymentService.createPayment(filledForm.data());

            Bursar.payBalance(studentId, paymentId);
            return ok(payments.render(
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId),
                    context, filledForm, studentId,
                    Resource.BACK_LINK(context)));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

}
