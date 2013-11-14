package controllers.resources;

import controllers.root.admin.course_schedules.semester.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Result;

import java.sql.SQLException;

import static play.mvc.Results.ok;

public class OneSemesterResource {

    public static Result course_schedule_get(final String seasonAndYear) {

        final String[] split = seasonAndYear.split(" ");
        final models.course.Semester.Season season =
                models.course.Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String context = Semester.url(seasonAndYear);

        /*
        try {
            return ok(one_semester.render(
                    CourseOfferingRepository.getInstance().
                            findAllCourseOfferingsBySemester(season, year),
                    context, COURSE_FORM, dept,
                    Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
        */
        return ok();
    }

    public static Result student_get(final int studentId,
                                     final String seasonAndYear) {

        return ok();
    }

    public static Result professor_get(final String department,
                                       final int professorId,
                                       final String seasonAndYear) {

        return ok();
    }
}
