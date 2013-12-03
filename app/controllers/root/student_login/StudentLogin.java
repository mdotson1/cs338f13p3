package controllers.root.student_login;

import controllers.root.Resource;
import controllers.root.student_login.student_portal.StudentPortal;
import models.database.dao.concrete.StudentRepository;
import models.person.Student;
import models.person.UniversityPerson;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.student_login.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class StudentLogin extends Controller {

    private final static Form<UniversityPerson> STUDENT_FORM =
            Form.form(UniversityPerson.class);

    private static Call postCall() {
        return controllers.root.student_login.routes.StudentLogin.post();
    }

    public static String url() {
        return controllers.root.student_login.routes.StudentLogin.get().url();
    }

    private static Result render(final boolean create) {

        final String context = StudentLogin.url();

        if (create) {
            final Form<UniversityPerson> form = STUDENT_FORM.bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(student_login.render(context,
                        Resource.BACK_LINK(context), postCall(),
                        STUDENT_FORM, true));
            }

            try {
                final Student s = StudentRepository.getInstance().
                        findById(form.get().getId());

                if (s == null) {
                    return badRequest(student_login.render(context,
                            Resource.BACK_LINK(context), postCall(),
                            STUDENT_FORM, true));
                } else {
                    return redirect(StudentPortal.url(form.get().getId()));
                }
            } catch (SQLException e) {
                return ok(debug.render(e.toString()));
            }
        }

        return ok(student_login.render(context, Resource.BACK_LINK(context),
                postCall(), STUDENT_FORM, false));
    }

    public static Result get() {
        return render(false);
    }

    public static Result post() {
        return render(true);
    }
}
