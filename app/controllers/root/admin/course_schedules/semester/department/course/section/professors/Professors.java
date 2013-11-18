package controllers.root.admin.course_schedules.semester.department.course.section.professors;

import models.course.Semester;
import models.person.Professor;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.section.professors.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Professors extends Controller {

    private static final Form<Professor> ASSIGN_PROF_FORM =
            new Form<Professor>(Professor.class);

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.admin.course_schedules.semester.department.
                course.section.professors.routes.Professors.get(seasonAndYear,
                department, courseNum, sectionNum).url();
    }

    public static Call postCall(final String seasonAndYear,
                                    final String department,
                                    final String courseNum,
                                    final String sectionNum) {

        return controllers.root.admin.course_schedules.semester.department.
                course.section.professors.routes.Professors.post(seasonAndYear,
                department, courseNum, sectionNum);
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum,
                                 final boolean create)
            throws SQLException {

        final String context = Professors.url(seasonAndYear, department,
                courseNum, sectionNum);
        final Form<Professor> form;

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            form = ASSIGN_PROF_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            // TODO database
        } else {
            form = ASSIGN_PROF_FORM;
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
