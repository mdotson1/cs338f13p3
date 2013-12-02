package controllers.root.admin_portal.course_schedules.semester;

import controllers.root.Resource;
import models.course.Course;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.CourseRepository;
import models.forms.course_offering.CourseOfferingForm1;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.*;
import views.html.helpers.*;
import models.course.Semester.Season;

import java.sql.SQLException;

public class Semester extends Controller {

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

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            final Form<CourseOfferingForm1> form =
                    Form.form(CourseOfferingForm1.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest(semester.render(CourseOfferingRepository.
                        getInstance().departmentsOfferingCoursesBySemester(
                        season,year), context, Resource.BACK_LINK(context),
                        seasonAndYear, form, postCall(seasonAndYear)));
            }

            final CourseOffering co = form.get().toCourseOffering(season, year);

            final Course c = CourseRepository.getInstance().findById(
                    co.getCourse().getDepartment(),
                    co.getCourse().getCourseNumber());

            if (c != null) {
                CourseOfferingRepository.getInstance().add(co);
            }
        }
        return ok(semester.render(CourseOfferingRepository.
                getInstance().departmentsOfferingCoursesBySemester(season,
                year), context, Resource.BACK_LINK(context), seasonAndYear,
                Form.form(CourseOfferingForm1.class), postCall(seasonAndYear)));
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
