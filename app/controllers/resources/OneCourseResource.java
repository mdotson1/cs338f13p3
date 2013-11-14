package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.root.admin.departments.department.courses.Courses;
import controllers.root.admin.departments.department.courses.course.Course;
import controllers.root.admin.departments.department.faculty.Faculty;
import models.database.dao.concrete.CourseRepository;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static play.mvc.Results.ok;

public class OneCourseResource {

    public static Result departments_get(final String dept,
                                         final String courseNum) {

        final String course_schedules = CourseSchedules.url();
        final String context = Course.url(dept, courseNum);

        final short courseNumber = Short.parseShort(courseNum);
        try {
            return ok(one_course.render(CourseRepository.getInstance().findById(
                    dept, courseNumber), course_schedules,
                    Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result course_schedules_get(final String seasonAndYear,
                                              final String department,
                                              final String courseNum) {
        return ok();
    }

    public static Result course_schedules_post(final String seasonAndYear,
                                              final String department,
                                              final String courseNum) {
        return ok();
    }

    public static Result professor_get(final String department,
                                       final int professorId,
                                       final String seasonAndYear,
                                       final String courseNum) {

        return ok();
    }
}
