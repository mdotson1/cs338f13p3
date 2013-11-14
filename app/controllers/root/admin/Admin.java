package controllers.root.admin;

import controllers.resources.AdminResource;
import controllers.root.Application;
import play.mvc.Controller;
import play.mvc.Result;

public class Admin extends Controller {

    public static String url() {
        return controllers.root.admin.routes.Admin.get().url();
    }

    public static Result get() {
        return AdminResource.admin_get();
    }
}
