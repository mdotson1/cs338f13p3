package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.students.Students;
import controllers.services.StudentService;
import models.database.dao.concrete.StudentRepository;
import models.person.ContactInformation;
import models.person.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.mvc.*;
import views.html.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class StudentsResource {

    private final static Form<Student> STUDENT_FORM = Form.form(Student.class);

    public static Result students_get() {

        final String context = Students.url();

        try {
            return ok(students.render(
                    StudentRepository.getInstance().getAll(), context,
                    STUDENT_FORM, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result students_post() {

        final String context = Students.url();

        final Form<Student> filledForm = STUDENT_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            StudentService.createStudent(filledForm.data());

            return ok(students.render(
                    StudentRepository.getInstance().getAll(), context,
                    filledForm, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
