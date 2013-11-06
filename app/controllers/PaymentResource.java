package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class PaymentResource extends Controller {
    public static Result getPaymentHistoryForStudent(final Integer studentId) {
        return ok();
    }

    public static Result addPaymentForStudent(final Integer studentId) {
        return ok();
    }

    public static Result getPayment(final Integer paymentId) {
        return ok();
    }

    public static Result removePayment() {
        return ok();
    }

    public static Result updatePayment(final Integer paymentId) {
        return ok();
    }
}
