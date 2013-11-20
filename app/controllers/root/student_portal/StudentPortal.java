package controllers.root.student_portal;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.student.*;
import views.html.helpers.*;

public class StudentPortal extends Controller {

    public static String url() {
        return controllers.root.student_portal.routes.StudentPortal.get().url();
    }

    private static Result render() {
        // TODO
        return ok();
    }

    public static Result get() {

        return render();
    }
}
