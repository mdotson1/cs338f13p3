package controllers.root.professor_login.professor_portal.course_schedules.semester.department.course.section.students;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.course_schedules.semester.department.course.section.students.*;
import views.html.helpers.*;

public class Students extends Controller {

    public static String url(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                semester.department.course.section.students.routes.Students.
                get(professorId, seasonAndYear, department, courseNum,
                        sectionNum).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) {
        // TODO
        return ok();
    }

    public static Result get(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        return render(professorId, seasonAndYear, department, courseNum,
                sectionNum);
    }
}
