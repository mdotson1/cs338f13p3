package controllers.root.admin.students;

import controllers.root.Resource;
import controllers.services.StudentService;
import models.database.dao.concrete.StudentRepository;
import models.person.Student;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Students extends Controller {

    private final static Form<Student> STUDENT_FORM = Form.form(Student.class);

    public static Call postCall() {
        return controllers.root.admin.students.routes.Students.post();
    }

    public static String url() {
        return controllers.root.admin.students.routes.Students.get().url();
    }

    private static Result render(final boolean create)
            throws SQLException {

        final String context = Students.url();
        final Form<Student> form;

        if (create) {

            form = STUDENT_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            StudentService.createStudent(form.data());
        } else {
            form = STUDENT_FORM;
        }
        return ok(students.render(StudentRepository.getInstance().getAll(),
                context, form, Resource.BACK_LINK(context), postCall()));
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
