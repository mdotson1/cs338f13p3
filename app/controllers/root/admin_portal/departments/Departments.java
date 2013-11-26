package controllers.root.admin_portal.departments;

import controllers.root.Resource;
import controllers.services.ProfessorService;
import models.database.dao.concrete.ProfessorRepository;
import models.forms.professor.ProfessorForm1;
import models.person.Professor;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.*;
import views.html.helpers.*;

import java.sql.SQLException;

import static play.data.Form.form;

public class Departments extends Controller {

    public static Call postCall() {
        return controllers.root.admin_portal.departments.routes.Departments.post();
    }

    public static String url() {
        return controllers.root.admin_portal.departments.routes.Departments.get().
                url();
    }

    private static Result render(final boolean create)
            throws SQLException {

        final String context = Departments.url();

        if (create) {

            final Form<ProfessorForm1> form =
                    form(ProfessorForm1.class).bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(departments.render(ProfessorRepository.
                        getInstance().allDepartments(), context, form,
                        Resource.BACK_LINK(context), postCall()));
            }

            ProfessorRepository.getInstance().add(form.get().toProfessor());
        }
        return ok(departments.render(ProfessorRepository.getInstance().
                allDepartments(), context, form(ProfessorForm1.class),
                Resource.BACK_LINK(context), postCall()));
    }

    public static Result get() {

        try {
            return render(false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post() {
        try {
            return render(true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
