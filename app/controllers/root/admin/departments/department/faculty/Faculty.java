package controllers.root.admin.departments.department.faculty;

import play.mvc.Controller;
import play.mvc.Result;

public class Faculty extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.faculty.routes.
                Faculty.get(dept).url();
    }

    public static Result get(final String department) {

        return ok();
    }

    public static Result post(final String department) {
        return ok();
    }
}
