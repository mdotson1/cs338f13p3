package controllers.root.admin.students.student.semesters.semester;

import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Semester extends Controller {

    private static Map<String,String> GENERATE_BACK_LINK(final int studentId) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", controllers.root.admin.students.student.semesters.
                routes.Semesters.get(studentId).url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        return ok();
    }
}
