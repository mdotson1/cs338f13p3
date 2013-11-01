package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class PaymentResource extends Controller {
    public static Result getPaymentHistoryForStudent(final Integer studentId) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addPaymentForStudent(final Integer studentId) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getPayment(final Integer paymentId) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result removePayment() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updatePayment(final Integer paymentId) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
