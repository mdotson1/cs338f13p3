package controllers;

import models.course.Course;
import models.course.CourseOffering;
import models.course.Semester;
import models.course.Semester.Season;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.CourseRepository;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class CourseOfferingResource extends Controller {

    private final static Form<CourseOffering> courseOfferingForm =
            Form.form(CourseOffering.class);

    private static final String coursesUri = routes.CourseResource.
            getAllCourses().absoluteURL(request());

    public static Result getAllCourseOfferingsForSem(final String seasonAndYear)
    {
        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String courseOfferingsURI = routes.CourseOfferingResource.
            getAllCourseOfferingsForSem(seasonAndYear)
            .absoluteURL(request());

        try {

            return ok(course_offering_table.render(
                    CourseOfferingRepository.getInstance().
                            findAllCoursesBySemester(season, year),
                    courseOfferingsURI, coursesUri, courseOfferingForm,
                    seasonAndYear));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result addCourseOffering(final String seasonAndYear) {
        final Form<CourseOffering> filledForm = courseOfferingForm.bindFromRequest();

        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String courseOfferingsURI = routes.CourseOfferingResource.
                getAllCourseOfferingsForSem(seasonAndYear)
                .absoluteURL(request());

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            CourseOffering course = new CourseOffering(
                    CourseRepository.getInstance().findById(data.get("Department"),
                            Short.parseShort(data.get("Course Number"))),
                    season, year, Short.parseShort(data.get("Section Number")));

            CourseOfferingRepository.getInstance().add(course);
            return ok(course_offering_table.render(
                    CourseOfferingRepository.getInstance().
                            findAllCoursesBySemester(season, year),
                    courseOfferingsURI, coursesUri, filledForm, seasonAndYear));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result getCourseOffering(final String seasonAndYear,
                                           final Integer coId) {
        final String[] split = seasonAndYear.split(" ");
        final Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        try {
            return ok(single_course_offering.render(
                    CourseOfferingRepository.getInstance().findById(coId),
                    coursesUri));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result updateCourseOffering(final String seasonAndYear,
                                              final Integer coId)
    {
        return ok();
    }
}
