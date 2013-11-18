package controllers.root.admin.departments;

import controllers.root.Resource;
import controllers.services.ProfessorService;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Departments extends Controller {

    private final static Form<Professor> PROFESSOR_FORM =
            Form.form(Professor.class);

    public static Call postCall() {
        return controllers.root.admin.departments.routes.Departments.post();
    }

    public static String url() {
        return controllers.root.admin.departments.routes.Departments.get().
                url();
    }

    private static Result render(final boolean create)
            throws SQLException {

        final String context = Departments.url();
        final Form<Professor> form;

        if (create) {

            form = PROFESSOR_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            ProfessorService.createProfessor(form.data());
        } else {
            form = PROFESSOR_FORM;
        }
        return ok(departments.render(
                ProfessorRepository.getInstance().allDepartments(), context,
                form, Resource.BACK_LINK(context), postCall()));
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
