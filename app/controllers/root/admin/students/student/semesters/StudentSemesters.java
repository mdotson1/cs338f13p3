package controllers.root.admin.students.student.semesters;

import controllers.resources.SemestersResource;
import controllers.root.admin.students.student.Student;
import play.mvc.Controller;
import play.mvc.Result;

public class StudentSemesters extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.semesters.routes.
                StudentSemesters.get(studentId).url();
    }

    public static Result get(final int studentId) {

        return SemestersResource.student_get(studentId);
    }
}
