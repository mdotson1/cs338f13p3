package controllers.root.admin.departments.department;

import controllers.resources.OneDepartmentResource;
import controllers.root.admin.departments.Departments;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Department extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.routes.Department.
                get(dept).url();
    }

    private static final Map<String,String> BACK = GENERATE_BACK_LINK();

    private static Map<String,String> GENERATE_BACK_LINK() {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Departments.url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get(final String dept) {

        return OneDepartmentResource.get(dept, BACK);
    }
}
