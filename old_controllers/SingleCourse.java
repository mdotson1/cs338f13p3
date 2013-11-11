package controllers;

import models.database.dao.concrete.CourseRepository;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;

public class SingleCourse extends Controller {
    public static Result getCourse(final String deptAndCourseNum) {

        final String[] split = deptAndCourseNum.split("-");
        final String department = split[0];
        final short courseNumber = Short.parseShort(split[1]);

        try {
            return ok(single_course.render(
                    CourseRepository.getInstance().findById(department,
                            courseNumber), coursesUri));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }
}
