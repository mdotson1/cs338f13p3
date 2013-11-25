package controllers.root.professor_portal;

import controllers.root.Resource;
import models.person.UniversityPerson;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.professor_login.professor_portal.*;
import views.html.helpers.*;

public class ProfessorPortal extends Controller {

    private final static Form<UniversityPerson> PROFESSOR_FORM =
            Form.form(UniversityPerson.class);

    private static Call postCall() {
        return controllers.root.professor_portal.routes.ProfessorPortal.post();
    }

    public static String url() {
        return controllers.root.professor_portal.routes.ProfessorPortal.get().
                url();
    }

    private static Result render() {
        final String context = ProfessorPortal.url();

        return ok(professor_portal.render(context, Resource.BACK_LINK(context),
                postCall(), PROFESSOR_FORM));
    }

    public static Result get() {
        return render();
    }

    public static Result post() {

        final Form<UniversityPerson> form = PROFESSOR_FORM.bindFromRequest();

        UniversityPerson p = form.get();

        if (form.hasErrors()) {
            return badRequest();
        } else {
            return redirect(StudentPortal.url(
                    form.get().getId()));
        }
    }
}
