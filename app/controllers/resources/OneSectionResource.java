package controllers.resources;

import models.course.Semester;
import models.course.Semester.Season;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class OneSectionResource {

    public static Result professor_get(final String department,
                                       final int professorId,
                                       final String seasonAndYear,
                                       final short courseNum,
                                       final short sectionNum) {

        final String[] split = seasonAndYear.split(" ");

        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);


        return ok();
    }

    public static Result student_get(final int studentId,
                                     final String seasonAndYear,
                                     final short courseNum,
                                     final short sectionNum) {
        return ok();
    }

    public static Result course_schedules_get(final String seasonAndYear,
                                              final String department,
                                              final short courseNum,
                                              final short sectionNum) {
        return ok();
    }
}
