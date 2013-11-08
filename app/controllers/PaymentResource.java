package controllers;

import models.database.dao.concrete.PaymentRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.ContactInformation;
import models.person.Payment;
import models.person.Professor;
import models.services.Bursar;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class PaymentResource extends Controller {

    private final static Form<Payment> paymentForm = Form.form(Payment.class);

    public static Result getPaymentHistoryForStudent(final Integer studentId) {

        final String paymentsUri = routes.PaymentResource.
                getPaymentHistoryForStudent(studentId).absoluteURL(request());

        try {
            return ok(payments_table.render(
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId), paymentsUri,
                    paymentForm, studentId));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result addPaymentForStudent(final Integer studentId) {

        final String paymentsUri = routes.PaymentResource.
                getPaymentHistoryForStudent(studentId).absoluteURL(request());

        final Form<Payment> filledForm = paymentForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final Payment p = new Payment(data.get("paymentType"),
                    Double.parseDouble(data.get("paymentAmount")));

            int paymentId = PaymentRepository.getInstance().add(p);
            Bursar.payBalance(studentId, paymentId);
            return ok(payments_table.render(
                    PaymentHistoryRepository.getInstance().
                            findAllPaymentsByStudent(studentId), paymentsUri,
                    filledForm, studentId));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result getPayment(final Integer studentId,
                                    final Integer paymentId) {

        final String paymentsUri = routes.PaymentResource.
                getPaymentHistoryForStudent(studentId).absoluteURL(request());

        try {
            return ok(single_payment.render(
                    PaymentRepository.getInstance().findById(paymentId),
                    paymentsUri));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result updatePayment(final Integer studentId,
                                       final Integer paymentId) {
        return ok();
    }
}
