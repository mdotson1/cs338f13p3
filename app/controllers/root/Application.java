package controllers.root;

import controllers.resources.BaseResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static String url() {
        return controllers.root.routes.Application.get().url();
    }

    public static Result get() {

        return BaseResource.root_get();
    }

    public static Result untrail(String path) {
        return movedPermanently("/" + path);
    }
}
