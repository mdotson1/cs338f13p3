package controllers.root.admin_portal.departments.department.faculty;

import controllers.root.Resource;
import controllers.services.ProfessorService;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Faculty extends Controller {

    private final static Form<Professor> PROFESSOR_FORM =
            Form.form(Professor.class);

    private static Call postCall(final String dept) {
        return controllers.root.admin_portal.departments.department.faculty.routes.
                Faculty.post(dept);
    }

    public static String url(final String dept) {
        return controllers.root.admin_portal.departments.department.faculty.routes.
                Faculty.get(dept).url();
    }

    private static Result render(final boolean create, final String department)
            throws SQLException {

        final String context = Faculty.url(department);
        final Form<Professor> form;

        if (create) {

            form = PROFESSOR_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            ProfessorService.createProfessor(form.data(), department);
        } else {
            form = PROFESSOR_FORM;
        }
        return ok(faculty.render(ProfessorRepository.getInstance().
                getFaculty(department), context, form,
                Resource.BACK_LINK(context), department, postCall(department)));
    }

    public static Result get(final String department) {

        try {
            return render(false, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String department) {

        try {
            return render(true, department);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
