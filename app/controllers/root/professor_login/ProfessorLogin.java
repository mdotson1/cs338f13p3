package controllers.root.professor_login;

import controllers.root.Resource;
import controllers.root.professor_login.professor_portal.ProfessorPortal;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import models.person.UniversityPerson;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.professor_login.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class ProfessorLogin extends Controller {

    private final static Form<UniversityPerson> PROFESSOR_FORM =
            Form.form(UniversityPerson.class);

    private static Call postCall() {
        return controllers.root.professor_login.routes.ProfessorLogin.post();
    }

    public static String url() {
        return controllers.root.professor_login.routes.ProfessorLogin.get().
                url();
    }

    private static Result render(final boolean create) {
        final String context = ProfessorLogin.url();

        if (create) {
            final Form<UniversityPerson> form = PROFESSOR_FORM.bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(professor_login.render(context,
                        Resource.BACK_LINK(context), postCall(),
                        PROFESSOR_FORM, true));
            }

            try {
                final Professor p = ProfessorRepository.getInstance().
                        findById(form.get().getId());

                if (p == null) {
                    return badRequest(professor_login.render(context,
                            Resource.BACK_LINK(context), postCall(),
                            PROFESSOR_FORM, true));
                } else {
                    return redirect(ProfessorPortal.url(form.get().getId()));
                }
            } catch (SQLException e) {
                return ok(debug.render(e.toString()));
            }
        }

        return ok(professor_login.render(context, Resource.BACK_LINK(context),
                postCall(), PROFESSOR_FORM, false));
    }

    public static Result get() {
        return render(false);
    }

    public static Result post() {
        return render(true);
    }
}
