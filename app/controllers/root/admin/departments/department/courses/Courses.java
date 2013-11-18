package controllers.root.admin.departments.department.courses;

import controllers.root.Resource;
import controllers.services.CourseService;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.courses.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Courses extends Controller {

    private final static Form<Course> COURSE_FORM = Form.form(Course.class);

    private static Call postCall(final String dept) {
        return controllers.root.admin.departments.department.courses.routes.
                Courses.post(dept);
    }

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.courses.routes.
                Courses.get(dept).url();
    }

    private static Result render(final boolean create, final String dept)
            throws SQLException {

        final String context = Courses.url(dept);
        final Form<Course> form;

        if (create) {

            form = COURSE_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            CourseService.createCourse(form.data(), dept);
        } else {
            form = COURSE_FORM;
        }
        return ok(courses.render(
                CourseRepository.getInstance().getAllByDepartment(dept), context,
                form, dept, Resource.BACK_LINK(context), postCall(dept)));
    }

    public static Result get(final String dept) {

        try {
            return render(false, dept);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String dept) {

        try {
            return render(true, dept);

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
