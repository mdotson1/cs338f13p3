package controllers.root.student_login.student_portal.semesters.semester.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.semesters.semester.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String deptCourseNumSectionNum) {
        return controllers.root.student_login.student_portal.semesters.semester.
                course.routes.Course.get(studentId, seasonAndYear,
                deptCourseNumSectionNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String deptCourseNumSectionNum) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String deptCourseNumSectionNum) {

        return render(studentId, seasonAndYear, deptCourseNumSectionNum);
    }
}
