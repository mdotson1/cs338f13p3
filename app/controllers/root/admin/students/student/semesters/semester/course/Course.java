package controllers.root.admin.students.student.semesters.semester.course;

import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course extends Controller {

    private static Map<String,String> GENERATE_BACK_LINK(final int studentId,
                                                   final String seasonAndYear) {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", controllers.root.admin.students.student.semesters.
                semester.routes.Semester.get(studentId, seasonAndYear).url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String courseNum, final String sectionNum) {

        return ok();
    }
}
