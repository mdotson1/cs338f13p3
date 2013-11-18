package controllers.root.student;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.student.*;
import views.html.helpers.*;

public class Student extends Controller {

    public static String url() {
        return controllers.root.student.routes.Student.get().url();
    }

    private static Result render() {
        // TODO
        return ok();
    }

    public static Result get() {

        return render();
    }
}
