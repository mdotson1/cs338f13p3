package controllers.root;

import controllers.root.admin_portal.AdminPortal;
import controllers.root.professor_portal.ProfessorPortal;
import controllers.root.student_portal.StudentPortal;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.*;
import views.html.helpers.*;


import java.util.LinkedHashMap;
import java.util.Map;

public class Application extends Controller {

    public static String url() {
        return controllers.root.routes.Application.get().url();
    }

    private static Result render() {

        final String context = Application.url();

        final Map<String, String> loginsAndURLs =
                new LinkedHashMap<String, String>();

        loginsAndURLs.put("Login as a student", StudentPortal.url());
        loginsAndURLs.put("Login as a professor", ProfessorPortal.url());
        loginsAndURLs.put("Login as an administrator", AdminPortal.url());

        return ok(root.render(loginsAndURLs));
    }

    public static Result get() {
        return render();
    }

    public static Result untrail(String path) {
        return movedPermanently("/" + path);
    }
}
