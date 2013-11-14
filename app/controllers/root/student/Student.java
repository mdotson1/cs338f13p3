package controllers.root.student;

import play.mvc.Controller;
import play.mvc.Result;

public class Student extends Controller {

    public static String url() {
        return controllers.root.student.routes.Student.get().url();
    }

    public static Result get() {

        return ok();
    }
}
