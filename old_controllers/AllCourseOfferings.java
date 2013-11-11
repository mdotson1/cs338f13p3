package controllers;

import controllers.services.CourseOfferingService;
import models.course.Course;
import models.course.CourseOffering;
import models.course.Semester;
import models.course.Semester.Season;
import models.database.dao.concrete.CourseOfferingRepository;

import play.data.Form;

import java.sql.SQLException;
import java.util.Map;

public class AllCourseOfferings extends Controller {

    private final static Form<CourseOffering> COURSE_OFFERING_FORM=
            Form.form(CourseOffering.class);

    // this function is used to obtain the URI for the resource containing
    // all of the specified semesters's CourseOfferings
    public static String allCourseOfferingsURI(final String seasonAndYear) {
        return routes.AllCourseOfferings.
                getAllCourseOfferingsForSem(seasonAndYear).
                absoluteURL(request());
    }

    public static Result getAllCourseOfferingsForSem(final String seasonAndYear)
    {
        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        try {
            final Iterator<CourseOffering> allCourses = CourseOfferingService.
                    findAllBySemester(season, year);

            return ok(course_offering_table.render(allCourses,
                    AllCourseOfferings.allCourseOfferingsURI(seasonAndYear),
                    AllCourses.ALL_COURSES_URI, COURSE_OFFERING_FORM,
                    seasonAndYear));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result addCourseOffering(final String seasonAndYear) {
        final Form<CourseOffering> filledForm = COURSE_OFFERING_FORM.bindFromRequest();

        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final Iterator<CourseOffering> allCourses = CourseOfferingService.
                    findAllBySemester(season, year);

            final Course course = CourseService.find(data.get("Department"),
                    Short.parseShort(data.get("Semesters Number")));

            CourseOffering courseOffering = new CourseOffering(course,
                    season, year, Short.parseShort(data.get("Section Number")));


            CourseOfferingRepository.getInstance().add(course);
            return ok(course_offering_table.render(allCourses,
                    AllCourses.allCourseOfferingsURI(seasonAndYear),
                    AllCourses.ALL_COURSES_URI, filledForm, seasonAndYear));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result updateCourseOffering(final String seasonAndYear,
                                              final Integer coId)
    {
        return ok();
    }
}
