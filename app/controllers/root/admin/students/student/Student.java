package controllers.root.admin.students.student;

import controllers.resources.OneStudentResource;
import controllers.root.admin.students.Students;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.routes.Student.
                get(studentId).url();
    }

    private static final Map<String,String> BACK = GENERATE_BACK_LINK();

    private static Map<String,String> GENERATE_BACK_LINK() {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Students.url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId) {

        return OneStudentResource.get(studentId, BACK);
    }
}
