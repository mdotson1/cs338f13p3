package controllers.resources;

import controllers.root.admin.departments.department.courses.Courses;
import controllers.services.CourseService;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CoursesResource {

    private final static Form<Course> COURSE_FORM = Form.form(Course.class);

    public static Result course_catalog_get(final String dept) {

        final String context = Courses.url(dept);

        try {
            return ok(courses.render(
                    CourseRepository.getInstance().getAllByDepartment(dept),
                    context, COURSE_FORM, dept,
                    Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result course_catalog_post(final String dept) {

        final String context = Courses.url(dept);

        final Form<Course> filledForm = COURSE_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            CourseService.createCourse(filledForm.data(), dept);

            return ok(courses.render(
                    CourseRepository.getInstance().getAllByDepartment(dept),
                    context, filledForm, dept, Resource.BACK_LINK(context)));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
