package controllers.root.admin.course_schedules.semester.department.course;

import controllers.root.Resource;
import controllers.services.CourseOfferingService;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course extends Controller {

    private final static Form<CourseOffering> CO_FORM =
            Form.form(CourseOffering.class);

    private static Call postCall(final String seasonAndYear,
                                final String department, final String courseNum)
    {
        return controllers.root.admin.course_schedules.semester.department.
                course.routes.Course.post(seasonAndYear, department, courseNum);
    }

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum) {
        return controllers.root.admin.course_schedules.semester.department.
                course.routes.Course.get(seasonAndYear, department, courseNum).
                url();
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum, final boolean create)
            throws SQLException {

        final String context = Course.url(seasonAndYear, department, courseNum);
        final Form<CourseOffering> form;

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            form = CO_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            CourseOfferingService.createCourseOffering(form.data(),
                    season, year, department, Short.parseShort(courseNum));
        } else {
            form = CO_FORM;
        }

        return ok(course.render(
                CourseOfferingRepository.getInstance().findAllSections(season,
                        year, department, Short.parseShort(courseNum)), context,
                Resource.BACK_LINK(context), form, postCall(seasonAndYear,
                department, courseNum)));
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
