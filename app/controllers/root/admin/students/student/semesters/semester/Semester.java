package controllers.root.admin.students.student.semesters.semester;

import controllers.resources.OneSemesterResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Semester extends Controller {

    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.admin.students.student.semesters.semester.
                routes.Semester.get(studentId, seasonAndYear).url();
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        return OneSemesterResource.student_get(studentId, seasonAndYear);
    }
}
