package controllers.resources;

import models.database.dao.concrete.StudentRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class OneStudentResource {

    private static final String STUDENTS_URI = controllers.root.admin.students.
            routes.Students.get().url();

    public static Result get(final int studentId, Map<String,String> backLink) {

        final String SINGLE_STUDENT_URI = STUDENTS_URI + "/" + studentId;

        try {
            Map<String,String> links = new HashMap<String,String>();
            links.put("Payment History", SINGLE_STUDENT_URI + "/payments");
            links.put("Courses Taking/Taken", SINGLE_STUDENT_URI +
                    "/semesters");
            links.putAll(backLink);
            return ok(single_student.render(
                    StudentRepository.getInstance().findById(studentId),
                    STUDENTS_URI, links));
        } catch (SQLException e) {
            return ok(e.toString());
        }
    }
}
