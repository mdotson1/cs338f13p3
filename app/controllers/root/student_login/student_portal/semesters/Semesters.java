package controllers.root.student_login.student_portal.semesters;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import models.person.Student;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.semesters.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semesters extends Controller {
    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.semesters.routes.
                Semesters.get(studentId).url();
    }

    private static Result render(final int studentId)
            throws SQLException {

        final String context = Semesters.url(studentId);

        final Iterator<Semester> sems = CoursesTakingRepository.getInstance().
                allSemestersStudentAttended(studentId);

        final Student stu = StudentRepository.getInstance().findById(studentId);

        return ok(semesters.render(sems, context, Resource.BACK_LINK(context),
                stu));
    }

    public static Result get(final int studentId) {
        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
