package controllers.root.admin.students.student.payments.payment;

import controllers.resources.OnePaymentResource;
import controllers.resources.PaymentsResource;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Payment extends Controller {

    private static Map<String,String> GENERATE_BACK_LINK(final int studentId) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", controllers.root.admin.students.student.payments.
                routes.Payments.get(studentId).url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId, final int paymentId) {

        return OnePaymentResource.get(studentId, paymentId,
                GENERATE_BACK_LINK(studentId));
    }
}
