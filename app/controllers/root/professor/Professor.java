package controllers.root.professor;

import play.mvc.Controller;
import play.mvc.Result;

public class Professor extends Controller {

    public static String url() {
        return controllers.root.professor.routes.Professor.get().url();
    }

    public static Result get() {

        return ok();
    }
}
