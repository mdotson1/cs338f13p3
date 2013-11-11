package controllers.resources;

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

    private static final String STUDENTS_URI = controllers.root.admin.students.
            routes.Students.get().url();

    private final static Form<Student> STUDENT_FORM = Form.form(Student.class);

    public static Result get(final Map<String,String> backLink) {
        try {
            return ok(students.render(
                    StudentRepository.getInstance().getAll(), STUDENTS_URI,
                    STUDENT_FORM, backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final Map<String,String> backLink) {
        final Form<Student> filledForm = STUDENT_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final ContactInformation ci = new ContactInformation(
                    data.get("Home Address"), data.get("Work Address"),
                    data.get("First Name"), data.get("Last Name"),
                    data.get("Work Phone"), data.get("Home Phone"),
                    data.get("Cell Phone"));
            Student s = new Student(ci, data.get("Date of Birth"), 0);

            StudentRepository.getInstance().add(s);
            return ok(students.render(
                    StudentRepository.getInstance().getAll(), STUDENTS_URI,
                    filledForm, backLink));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
