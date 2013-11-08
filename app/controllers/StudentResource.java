package controllers;

import models.database.dao.concrete.StudentRepository;
import models.person.ContactInformation;
import models.person.Student;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;
import play.api.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentResource extends Controller {

    private static final String studentsUri = routes.StudentResource.
            getAllStudents().absoluteURL(request());

    private final static Form<Student> studentForm = Form.form(Student.class);

    public static Result getAllStudents() {
        try {
            return ok(students_table.render(
                    StudentRepository.getInstance().getAll(), studentsUri,
                    studentForm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addStudent() {
        final Form<Student> filledForm = studentForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final ContactInformation ci = new ContactInformation(
                    data.get("homeAddr"), data.get("workAddr"),
                    data.get("firstName"), data.get("lastName"),
                    data.get("workPhone"), data.get("homePhone"),
                    data.get("cellPhone"));
            Student s = new Student(ci, data.get("dateOfBirth"),
                    Double.parseDouble(data.get("currentBalance")));

            StudentRepository.getInstance().add(s);
            return ok(students_table.render(
                    StudentRepository.getInstance().getAll(), studentsUri,
                    filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result getStudent(final Integer id) {
        final String studentURI = studentsUri + "/" + id;
        try {
            Map<String,String> links = new HashMap<String,String>();
            links.put("Payment History", studentURI + "/payments");
            return ok(single_student.render(
                    StudentRepository.getInstance().findById(id), studentsUri,
                    links));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result removeStudent() {
        return ok();
    }

    public static Result updateStudent(final Integer id) {
        return ok();
    }
}
