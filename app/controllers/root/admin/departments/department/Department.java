package controllers.root.admin.departments.department;

import controllers.resources.OneDepartmentResource;
import controllers.root.admin.departments.Departments;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Department extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.routes.Department.
                get(dept).url();
    }

    public static Result get(final String dept) {

        return OneDepartmentResource.departments_get(dept);
    }
}
