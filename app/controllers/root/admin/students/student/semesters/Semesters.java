package controllers.root.admin.students.student.semesters;

import controllers.root.Resource;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.CoursesTakenRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.semesters.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Semesters extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.semesters.routes.
                Semesters.get(studentId).url();
    }

    private static Result render(final int studentId)
            throws SQLException {

        final String context = Semesters.url(studentId);

        return ok(semesters.render(CoursesTakenRepository.getInstance().
                allSemestersStudentAttended(studentId), context,
                Resource.BACK_LINK(context),
                StudentRepository.getInstance().findById(studentId)));
    }

    public static Result get(final int studentId) {
        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
