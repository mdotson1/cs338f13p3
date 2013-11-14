package controllers.resources;

import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.root.admin.students.student.payments.payment.Payment;
import models.database.dao.concrete.PaymentRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.ok;

public class OnePaymentResource {
    public static Result get(final Integer studentId, final Integer paymentId) {

        final String context = Payment.url(studentId, paymentId);

        try {
            return ok(one_payment.render(
                    PaymentRepository.getInstance().findById(paymentId),
                    Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
