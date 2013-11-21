package controllers.root.admin_portal.students.student;

import controllers.root.Resource;
import models.database.dao.concrete.StudentRepository;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import views.html.root.admin.students.student.*;
import views.html.helpers.*;

public class Student extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin_portal.students.student.routes.Student.
                get(studentId).url();
    }

    private static Result render(final int studentId)
            throws SQLException {

        final String context = Student.url(studentId);

        Map<String,String> links = new HashMap<String,String>();
        links.put("Payment History", context + "/payments");
        links.put("Courses Taking/Taken", context +
                "/semesters");

        return ok(student.render(
                StudentRepository.getInstance().findById(studentId), context,
                links, Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId) {

        try {
            return render(studentId);
        } catch (SQLException e) {
            return ok(e.toString());
        }
    }
}
