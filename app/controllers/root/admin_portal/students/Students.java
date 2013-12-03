package controllers.root.admin_portal.students;

import controllers.root.Resource;
import static play.data.Form.*;

import models.database.dao.concrete.StudentRepository;
import models.forms.student.StudentForm1;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Students extends Controller {

    public static Call postCall() {
        return controllers.root.admin_portal.students.routes.Students.post();
    }

    public static String url() {
        return controllers.root.admin_portal.students.routes.Students.get().
                url();
    }

    private static Result render(final boolean create)
            throws SQLException {

        final String context = Students.url();

        if (create) {

            final Form<StudentForm1> form =
                    form(StudentForm1.class).bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(students.render(StudentRepository.
                        getInstance().getAll(), context, form,
                        Resource.BACK_LINK(context), postCall()));
            }

            StudentRepository.getInstance().add(form.get().toStudent());
        }
        return ok(students.render(StudentRepository.getInstance().getAll(),
                context, form(StudentForm1.class), Resource.BACK_LINK(context), postCall()));
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
