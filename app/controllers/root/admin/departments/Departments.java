package controllers.root.admin.departments;

import controllers.resources.DepartmentsResource;
import controllers.root.admin.Admin;
import play.mvc.Controller;
import play.mvc.Result;

public class Departments extends Controller {

    public static String url() {
        return controllers.root.admin.departments.routes.Departments.get().
                url();
    }

    public static Result get() {

        return DepartmentsResource.admin_get();
    }

    public static Result post() {
        return DepartmentsResource.admin_post();
    }
}
