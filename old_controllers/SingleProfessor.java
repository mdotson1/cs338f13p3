package controllers;

import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;

import models.person.ContactInformation;
import models.person.Professor;
import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class SingleProfessor extends Controller {

    private static final String COURSES_URI = routes.CourseResource.
            getAllCourses().absoluteURL(request());

    private static final String COURSE_OFFERING_URI = routes.CourseResource.
            getAllCourses().absoluteURL(request());

    public static Result getProfessor(final Integer id) {

        try {
            return ok(single_professor.render(
                    ProfessorRepository.getInstance().findById(id),
                    COURSE_OFFERING_URI, COURSES_URI,
                    CoursesTeachingRepository.getInstance().
                            findAllCoursesTaughtByProfessor(id)));
        } catch (SQLException e) {
            debug.render(e.toString());
        }
        return ok();
    }

    public static Result updateProfessor(final Integer id) {
        return ok();
    }
}
