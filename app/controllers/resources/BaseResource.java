package controllers.resources;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

public class BaseResource {

    public static Result get() {

        Map<String, String> loginsAndURLs = new LinkedHashMap<String, String>();

        loginsAndURLs.put("Login as a student", controllers.root.student.
                routes.Student.get().url());
        loginsAndURLs.put("Login as a professor", controllers.root.professor.
                routes.Professor.get().url());
        loginsAndURLs.put("Login as an administrator", controllers.root.admin.
                routes.Admin.get().url());

        return ok(base.render(loginsAndURLs));
    }
}
