package controllers.root.admin_portal.course_schedules.semester.department.course.section.students;

import models.course.Semester;
import models.person.Student;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.section.students.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Students extends Controller {

    private static final Form<Student> ENROLL_STUDENT_FORM =
            new Form<Student>(Student.class);

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                course.section.students.routes.Students.get(seasonAndYear,
                department, courseNum, sectionNum).url();
    }

    public static Call postCall(final String seasonAndYear,
                                   final String department,
                                   final String courseNum,
                                   final String sectionNum) {

        return controllers.root.admin_portal.course_schedules.semester.department.
                course.section.students.routes.Students.post(seasonAndYear,
                department, courseNum, sectionNum);
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum,
                                 final boolean create)
            throws SQLException {

        final String context = Students.url(seasonAndYear, department,
                courseNum, sectionNum);
        final Form<Student> form;

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            form = ENROLL_STUDENT_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            // TODO database
        } else {
            form = ENROLL_STUDENT_FORM;
        }

        return ok();
    }

    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        try {
            return render(seasonAndYear, department, courseNum, sectionNum,
                    false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String seasonAndYear,
                              final String department,
                              final String courseNum,
                              final String sectionNum) {

        try {
            return render(seasonAndYear, department, courseNum, sectionNum,
                    true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
