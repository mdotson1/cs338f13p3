package controllers.resources;

import controllers.root.admin.students.student.Student;

import models.database.dao.relationships.CoursesTakenRepository;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.ok;

public class SemestersResource {

    public static Result get(final int studentId,
                             final Map<String,String> backLink) {
        try {
            return ok(semesters.render(
                    CoursesTakenRepository.getInstance().
                            allSemestersStudentAttended(studentId),
                    Student.url(studentId), backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
