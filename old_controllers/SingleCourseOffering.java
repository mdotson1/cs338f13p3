package controllers;

import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;

public class SingleCourseOffering extends Controller {
    public static Result getCourseOffering(final String seasonAndYear,
                                           final Integer coId) {
        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
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
}
