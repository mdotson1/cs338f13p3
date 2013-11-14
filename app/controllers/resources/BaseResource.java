package controllers.resources;

import controllers.root.Application;
import controllers.root.admin.Admin;
import controllers.root.professor.Professor;
import controllers.root.student.Student;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

public class BaseResource {

    public static Result root_get() {

        final String context = Application.url();

        final Map<String, String> loginsAndURLs =
                new LinkedHashMap<String, String>();

        loginsAndURLs.put("Login as a student", Student.url());
        loginsAndURLs.put("Login as a professor", Professor.url());
        loginsAndURLs.put("Login as an administrator", Admin.url());

        return ok(base.render(loginsAndURLs));
    }
}
