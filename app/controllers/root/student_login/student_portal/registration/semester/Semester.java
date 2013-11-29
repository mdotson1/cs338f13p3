package controllers.root.student_login.student_portal.registration.semester;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.relationships.CoursesTakingRepository;
import models.forms.registration.RegistrationForm1;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.student_login.student_portal.registration.semester.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Semester extends Controller {

    public static String url(final int studentId, final String seasonAndYear) {
        return controllers.root.student_login.student_portal.registration.
                semester.routes.Semester.get(studentId, seasonAndYear).url();
    }

    private static Call postCall(final int studentId,
                                 final String seasonAndYear) {
        return controllers.root.student_login.student_portal.registration.
                semester.routes.Semester.post(studentId, seasonAndYear);
    }


    private static void registerCourses(
            final Map<Integer,CourseOffering> cos, final int studentId)
            throws SQLException {

        int coursesEnrolled = 0;
        final int MAX_COURSES = 4;
        for (int i = 1; i <= cos.size(); i++) {
            if (coursesEnrolled < MAX_COURSES) {
                CoursesTakingRepository.getInstance().add(studentId,
                        cos.get(i).getCourseOfferingId());
                coursesEnrolled++;
            }
        }
    }

    private static List<String> errorCheck(
            final Map<Integer,CourseOffering> cos) throws SQLException {

        final List<String> errors = new ArrayList<String>();

        for (int i = 1; i <= cos.size(); i++) {
            final CourseOffering co = cos.get(i);
            if (co == null) {
                switch (i) {
                    case 1:
                        errors.add("First primary choice has errors. Please check the course schedule.");
                        break;
                    case 2:
                        errors.add("Second primary choice has errors. Please check the course schedule.");
                        break;
                    case 3:
                        errors.add("Third primary choice has errors. Please check the course schedule.");
                        break;
                    case 4:
                        errors.add("Fourth primary choice has errors. Please check the course schedule.");
                        break;
                    case 5:
                        errors.add("First secondary choice has errors. Please check the course schedule.");
                        break;
                    case 6:
                        errors.add("Second secondary choice has errors. Please check the course schedule.");
                        break;
                }
            }
        }

        return errors;
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final boolean create) throws SQLException {

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String context = Semester.url(studentId, seasonAndYear);

        final String courseSchedules = controllers.root.student_login.
                student_portal.course_schedules.semester.Semester.
                url(studentId, seasonAndYear);

        if (create) {

            final Form<RegistrationForm1> form =
                    Form.form(RegistrationForm1.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest(semester.render(context,
                        Resource.BACK_LINK(context), courseSchedules,
                        form, postCall(studentId, seasonAndYear), false, null));
            }

            final Map<Integer,CourseOffering> cos = form.get().
                    toCourseOfferings(season, year);

            System.out.println("cos size before errors: " + cos.size());

            final List<String> errors = Semester.errorCheck(cos);

            System.out.println("errors?: " + (errors.size() > 0));

            if (errors.size() > 0) {
                return ok(semester.render(context, Resource.BACK_LINK(context),
                        courseSchedules, Form.form(RegistrationForm1.class),
                        postCall(studentId, seasonAndYear), false,
                        errors.iterator()));
            }

            System.out.println("lets register! cos size: " + cos.size());

            registerCourses(cos, studentId);

            return ok(semester.render(context, Resource.BACK_LINK(context),
                    courseSchedules, Form.form(RegistrationForm1.class),
                    postCall(studentId, seasonAndYear), true, null));
        }
        return ok(semester.render(context, Resource.BACK_LINK(context),
                courseSchedules, Form.form(RegistrationForm1.class),
                postCall(studentId, seasonAndYear), false, null));
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


