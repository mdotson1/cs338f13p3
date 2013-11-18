package controllers.root.professor;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.professor.*;
import views.html.helpers.*;

public class Professor extends Controller {

    public static String url() {
        return controllers.root.professor.routes.Professor.get().url();
    }

    private static Result render() {
        // TODO
        return ok();
    }

    public static Result get() {
        return render();
    }
}
