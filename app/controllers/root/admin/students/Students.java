package controllers.root.admin.students;

import controllers.resources.StudentsResource;
import controllers.root.admin.Admin;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Students extends Controller {

    public static String url() {
        return controllers.root.admin.students.routes.Students.get().url();
    }

    private static final Map<String,String> BACK = GENERATE_BACK_LINK();

    private static Map<String,String> GENERATE_BACK_LINK() {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Admin.url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get() {

        return StudentsResource.get(BACK);
    }

    public static Result post() {
        return StudentsResource.post(BACK);
    }
}
