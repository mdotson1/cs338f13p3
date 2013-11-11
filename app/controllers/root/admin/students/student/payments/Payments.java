package controllers.root.admin.students.student.payments;

import controllers.resources.PaymentsResource;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Payments extends Controller {

    private static Map<String,String> GENERATE_BACK_LINK(final int studentId) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", controllers.root.admin.students.student.routes.
                Student.get(studentId).url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId) {

        return PaymentsResource.get(studentId, GENERATE_BACK_LINK(studentId));
    }

    public static Result post(final int studentId) {
        return PaymentsResource.post(studentId, GENERATE_BACK_LINK(studentId));
    }
}
