package controllers;

import models.database.dao.concrete.StudentRepository;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SingleStudent extends Controller {

    private static final String STUDENTS_URI = routes.StudentResource.
            getAllStudents().absoluteURL(request());

    public static Result getStudent(final Integer id) {
        final String SINGLE_STUDENT_URI = STUDENTS_URI + "/" + id;
        try {
            Map<String,String> links = new HashMap<String,String>();
            links.put("payment History", SINGLE_STUDENT_URI + "/payments");
            return ok(single_student.render(
                    StudentRepository.getInstance().findById(id), STUDENTS_URI,
                    links));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }
}
