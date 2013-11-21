package controllers.root.student_portal.student.course_schedules.semester.department.course.section.professors;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_portal.student.course_schedules.semester.department.course.section.professors.*;
import views.html.helpers.*;

public class Professors extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.student_portal.student.course_schedules.
                semester.department.course.section.professors.routes.Professors.
                get(studentId, seasonAndYear, department, courseNum,
                        sectionNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) {
        // TODO
        return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        return render(studentId, seasonAndYear, department, courseNum,
                sectionNum);
    }
}
