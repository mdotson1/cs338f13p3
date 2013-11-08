package controllers;

import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.person.ContactInformation;
import models.person.Professor;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class CourseResource extends Controller {

    private static final String coursesUri = routes.CourseResource.
            getAllCourses().absoluteURL(request());

    private final static Form<Course> courseForm = Form.form(Course.class);

    public static Result getAllCourses() {
        try {
            return ok(courses_table.render(
                    CourseRepository.getInstance().getAll(), coursesUri,
                    courseForm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addCourse() {
        final Form<Course> filledForm = courseForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            Course course = new Course(data.get("Department"),
                    Short.parseShort(data.get("Course Number")),
                    Short.parseShort(data.get("Cost (USD)")),
                    data.get("Course Description"));

            CourseRepository.getInstance().add(course);
            return ok(courses_table.render(
                    CourseRepository.getInstance().getAll(), coursesUri,
                    filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

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

    public static Result updateCourse(final String deptAndCourseNum) {
        return ok();
    }
}
