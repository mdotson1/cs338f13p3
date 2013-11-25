package controllers.root.student_login;

import controllers.root.Resource;
import controllers.root.student_login.student_loginlogin.student_portalPortal;
import models.person.UniversityPerson;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.student_login.*;
import views.html.helpers.*;

public class StudentLogin extends Controller {

    private final static Form<UniversityPerson> STUDENT_FORM =
            Form.form(UniversityPerson.class);

    private static Call postCall() {
        return controllers.root.student_login.routes.StudentPortal.post();
    }

    public static String url() {
        return controllers.root.student_login.routes.StudentPortal.get().url();
    }

    private static Result render() {

        final String context = StudentLogin.url();

        return ok(student_login.render(context, Resource.BACK_LINK(context),
                postCall(), STUDENT_FORM));
    }

    public static Result get() {

        return render();
    }

    public static Result post() {

        final Form<UniversityPerson> form = STUDENT_FORM.bindFromRequest();

        if (form.hasErrors()) {
            return badRequest();
        } else {
            return redirect(StudentPortal.url(
                    form.get().getId()));
        }
    }
}
