package models.forms.payment;

import models.person.Payment;
import play.data.validation.Constraints;

import java.text.DecimalFormat;

public class PaymentForm1 {

    @Constraints.Required()
    public String paymentType;
    @Constraints.Required()
    @Constraints.Min(value=1, message="Please enter at least 1 USD")
    @Constraints.Max(value = 99998, message="Please enter a value less than 99999")
    public double paymentAmount;

    final static double roundTwoDecimals(final double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public Payment toPayment() {
        return new Payment(paymentType, roundTwoDecimals(paymentAmount));
    }
}