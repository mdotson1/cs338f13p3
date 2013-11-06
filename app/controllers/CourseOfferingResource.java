package controllers;

import models.course.Semester;
import models.course.Semester.Season;
import models.database.dao.concrete.CourseOfferingRepository;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;

public class CourseOfferingResource extends Controller {

    public static Result getAllCourseOfferingsForSem(final String seasonAndYear)
    {
        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);
        /*
        try {
            return ok(course_offering_table.render(
                    CourseOfferingRepository.getInstance().
                            findAllCoursesBySemester(season, year),
                    routes.CourseOfferingResource.
                            getAllCourseOfferingsForSem(seasonAndYear)
                            .absoluteURL(request())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        return ok();
    }

    public static Result addCourseOffering(final String seasonAndYear) {
        return ok();
    }

    public static Result getCourseOffering(final String seasonAndYear,
                                           final String deptCourseNumSecNum) {
        return ok();
    }

    public static Result updateCourseOffering(final String seasonAndYear,
                                              final String deptCourseNumSecNum)
    {
        return ok();
    }
}
