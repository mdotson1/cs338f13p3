package controllers.root.admin.students.student;

import controllers.resources.OneStudentResource;
import controllers.root.admin.students.Students;
import play.mvc.Controller;
import play.mvc.Result;

public class Student extends Controller {

    public static String url(final int studentId) {
        return controllers.root.admin.students.student.routes.Student.
                get(studentId).url();
    }
    public static Result get(final int studentId) {

        return OneStudentResource.students_get(studentId);
    }
}
