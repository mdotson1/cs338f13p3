package controllers.root.admin_portal.departments.department.faculty;

import controllers.root.Resource;
import models.database.dao.concrete.ProfessorRepository;
import models.forms.professor.ProfessorForm2;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Faculty extends Controller {

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

        if (create) {


            final Form<ProfessorForm2> form =
                    Form.form(ProfessorForm2.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            ProfessorRepository.getInstance().add(form.get().
                    toProfessor(department));
        }

        return ok(faculty.render(ProfessorRepository.getInstance().
                getFaculty(department), context,
                Form.form(ProfessorForm2.class), Resource.BACK_LINK(context),
                department, postCall(department)));
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
