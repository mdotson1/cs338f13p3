package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.students.student.Student;
import models.database.dao.concrete.StudentRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class OneStudentResource {

    public static Result students_get(final int studentId) {

        final String context = Student.url(studentId);

        try {
            Map<String,String> links = new HashMap<String,String>();
            links.put("Payment History", context + "/payments");
            links.put("Courses Taking/Taken", context +
                    "/semesters");

            return ok(one_student.render(
                    StudentRepository.getInstance().findById(studentId),
                    context, links, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(e.toString());
        }
    }
}
