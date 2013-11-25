package controllers.root.student_login.student_portal;

import controllers.root.Resource;
import models.database.dao.concrete.StudentRepository;
import models.person.Student;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentPortal extends Controller {
    public static String url(final int studentId) {
        return controllers.root.student_login.student_portal.routes.
                StudentPortal.get(studentId).url();
    }

    private static Result render(final int studentId) throws SQLException {
        final String context = StudentPortal.url(studentId);

        final Map<String,String> links = new HashMap<String,String>();
        links.put("Course Schedules", context + "/course_schedules");
        links.put("Departments", context + "/departments");
        links.put("Payment History", context + "/payments");
        links.put("Register For Classes", context + "/registration");
        links.put("Courses Taking/Taken", context + "/semesters");

        final Student student = StudentRepository.getInstance().
                findById(studentId);

        return ok(student_portal.render(student, links,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId) {

        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
