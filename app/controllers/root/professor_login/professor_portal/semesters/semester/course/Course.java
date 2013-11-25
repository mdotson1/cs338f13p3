package controllers.root.professor_login.professor_portal.semesters.semester.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.semesters.semester.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int professorId, final String seasonAndYear,
                             final String deptCourseSection) {
        return controllers.root.professor_login.professor_portal.semesters.semester.
                course.routes.Course.get(professorId, seasonAndYear,
                deptCourseSection).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String deptCourseSection) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId, final String seasonAndYear,
                             final String deptCourseSection) {

        return render(professorId, seasonAndYear, deptCourseSection);
    }
}
