package controllers.root.admin.students;

import controllers.resources.StudentsResource;
import controllers.root.admin.Admin;
import play.mvc.Controller;
import play.mvc.Result;

public class Students extends Controller {

    public static String url() {
        return controllers.root.admin.students.routes.Students.get().url();
    }

    public static Result get() {

        return StudentsResource.students_get();
    }

    public static Result post() {
        return StudentsResource.students_post();
    }
}
