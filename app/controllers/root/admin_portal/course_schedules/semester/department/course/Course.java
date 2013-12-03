package controllers.root.admin_portal.course_schedules.semester.department.course;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import models.forms.course_offering.CourseOfferingForm3;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    private static Call postCall(final String seasonAndYear,
                                final String department, final String courseNum)
    {
        return controllers.root.admin_portal.course_schedules.semester.department.
                course.routes.Course.post(seasonAndYear, department, courseNum);
    }

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                course.routes.Course.get(seasonAndYear, department, courseNum).
                url();
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum, final boolean create)
            throws SQLException {

        final String context = Course.url(seasonAndYear, department, courseNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);
        final short courseNumber = Short.parseShort(courseNum);

        if (create) {

            final Form<CourseOfferingForm3> form =
                    Form.form(CourseOfferingForm3.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest(course.render(CourseOfferingRepository.
                        getInstance().findAllSections(season, year, department,
                        courseNumber), context, Resource.BACK_LINK(context),
                        form, postCall(seasonAndYear, department, courseNum),
                        false));
            }

            final CourseOffering co = form.get().toCourseOffering(department,
                    Short.parseShort(courseNum), season, year);

            final boolean contains = CourseOfferingRepository.getInstance().
                    contains(department, courseNumber, co.getSectionNumber(),
                            season, year);

            if(contains) {
                return badRequest(course.render(CourseOfferingRepository.
                        getInstance().findAllSections(season, year, department,
                        courseNumber), context, Resource.BACK_LINK(context),
                        form, postCall(seasonAndYear, department, courseNum),
                        true));
            } else {
                CourseOfferingRepository.getInstance().add(co);
            }
        }

        return ok(course.render(CourseOfferingRepository.getInstance().
                findAllSections(season, year, department,
                        Short.parseShort(courseNum)), context,
                Resource.BACK_LINK(context), Form.form(
                CourseOfferingForm3.class), postCall(seasonAndYear, department,
                courseNum), false));
    }

    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum) {
        try {
            return render(seasonAndYear, department, courseNum, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String seasonAndYear,
                              final String department, final String courseNum) {
        try {
            return render(seasonAndYear, department, courseNum, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
