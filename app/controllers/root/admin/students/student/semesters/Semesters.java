package controllers.root.admin.students.student.semesters;

import controllers.resources.SemestersResource;
import controllers.root.admin.students.student.Student;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Semesters extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.semesters.routes.
                Semesters.get(studentId).url();
    }

    private static Map<String,String> GENERATE_BACK_LINK(final int studentId) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Student.url(studentId));
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId) {

        return SemestersResource.get(studentId, GENERATE_BACK_LINK(studentId));
    }
}
