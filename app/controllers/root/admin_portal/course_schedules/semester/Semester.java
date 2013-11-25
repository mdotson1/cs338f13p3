package controllers.root.admin_portal.course_schedules.semester;

import controllers.root.Resource;
import controllers.services.CourseOfferingService;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.*;
import views.html.helpers.*;
import models.course.Semester.Season;

import java.sql.SQLException;

public class Semester extends Controller {

    private final static Form<CourseOffering> CO_FORM =
            Form.form(CourseOffering.class);

    private static Call postCall(final String seasonAndYear) {
        return controllers.root.admin_portal.course_schedules.semester.routes.
                Semester.post(seasonAndYear);
    }

    public static String url(final String seasonAndYear) {
        return controllers.root.admin_portal.course_schedules.semester.routes.
                Semester.get(seasonAndYear).url();
    }

    private static Result render(final String seasonAndYear,
                                 final boolean create) throws SQLException {

        final String context = Semester.url(seasonAndYear);
        final Form<CourseOffering> form;

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            form = CO_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            CourseOfferingService.createCourseOffering(form.data(),
                    season, year);
        } else {
            form = CO_FORM;
        }

        return ok(semester.render(CourseOfferingRepository.
                getInstance().departmentsOfferingCoursesBySemester(season,
                year), context, Resource.BACK_LINK(context), seasonAndYear,
                form, postCall(seasonAndYear)));
    }

    public static Result get(final String seasonAndYear) {

        try {
            return render(seasonAndYear, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String seasonAndYear) {

        try {
            return render(seasonAndYear, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
