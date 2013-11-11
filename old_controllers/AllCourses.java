package controllers;

import models.course.Course;
import models.database.dao.concrete.CourseRepository;

import play.data.Form;
import play.mvc.*;

import java.sql.SQLException;
import java.util.Map;

public class AllCourses extends Controller {

    // this URI is for the resource containing all the semesters
    public static final String ALL_COURSES_URI = routes.AllCourses.
            getAllCourses().absoluteURL(request());

    private final static Form<Course> COURSE_FORM = Form.form(Course.class);

    public static Result getAllCourses() {
        try {
            return ok(courses_table.render(
                    CourseRepository.getInstance().getAll(),
                    AllCourses.ALL_COURSES_URI, COURSE_FORM));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addCourse() {
        final Form<Course> filledForm = COURSE_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            Course course = new Course(data.get("Department"),
                    Short.parseShort(data.get("Semesters Number")),
                    Short.parseShort(data.get("Cost (USD)")),
                    data.get("Semesters Description"));

            CourseRepository.getInstance().add(course);
            return ok(courses_table.render(
                    CourseRepository.getInstance().getAll(),
                    AllCourses.ALL_COURSES_URI, filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result updateCourse(final String deptAndCourseNum) {
        return ok();
    }
}
