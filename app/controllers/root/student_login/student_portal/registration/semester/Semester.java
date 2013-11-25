package controllers.root.student_login.student_portal.registration.semester;

import controllers.root.Resource;
import controllers.root.student_login.student_portal.course_schedules.CourseSchedules;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTakingRepository;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.student_login.student_portal.registration.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;


public class Semester extends Controller {

    private final static Form<Registration> REGISTRATION_FORM =
            Form.form(Registration.class);

    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.student_login.student_portal.registration.
                semester.routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Call postCall(final int studentId,
                                 final String seasonAndYear) {
        return controllers.root.student_login.student_portal.registration.
                semester.routes.Semester.post(studentId, seasonAndYear);
    }

    private static boolean registerChoice(final String choice,
                                          final int studentId,
                                          final Season season, final short year)
            throws SQLException{

        System.out.println(choice);
        final String[] tokens = choice.split("-");
        final String department = tokens[0];
        final short courseNum = Short.parseShort(tokens[1]);
        final short sectionNum = Short.parseShort(tokens[2]);

        final CourseOffering co = CourseOfferingRepository.getInstance().find(
                department, courseNum, sectionNum, season, year);

        CoursesTakingRepository.getInstance().add(studentId,
                co.getCourseOfferingId());

        return true;
    }

    private static void registerForm(final Form<Registration> form,
                                     final int studentId,
                                     final String seasonAndYear)
            throws SQLException {

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Registration reg = form.get();

        int count = 0;

        boolean registered = registerChoice(reg.getFirstChoice(), studentId,
                season, year);
        if (registered) count++;
        registered = registerChoice(reg.getSecondChoice(), studentId, season,
                year);
        if (registered) count++;
        registered = registerChoice(reg.getThirdChoice(), studentId, season,
                year);
        if (registered) count++;
        registered = registerChoice(reg.getFourthChoice(), studentId, season,
                year);
        if (registered) count++;
        if (count < 4) {
            registered = registerChoice(reg.getFifthChoice(), studentId, season,
                    year);
            if (registered) count++;
        }
        if (count < 4) {
            registered = registerChoice(reg.getSixthChoice(), studentId, season,
                    year);
            if (registered) count++;
        }

        String[] tokens = reg.getFirstChoice().split("-");
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final boolean create) throws SQLException {

        final String context = Semester.url(studentId, seasonAndYear);

        final Form<Registration> form;

        if (create) {

            form = REGISTRATION_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            registerForm(form, studentId, seasonAndYear);
        } else {
            form = REGISTRATION_FORM;
        }

        final String courseSchedules = controllers.root.student_login.
                student_portal.course_schedules.semester.Semester.
                url(studentId, seasonAndYear);

        return ok(semester.render(context, Resource.BACK_LINK(context),
                courseSchedules, form, postCall(studentId, seasonAndYear)));
    }

    public static Result get(final int studentId, final String seasonAndYear) {

        try {
            return render(studentId, seasonAndYear, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final int studentId, final String seasonAndYear) {

        try {
            return render(studentId, seasonAndYear, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}


