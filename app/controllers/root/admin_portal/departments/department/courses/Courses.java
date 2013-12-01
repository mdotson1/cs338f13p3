package controllers.root.admin_portal.departments.department.courses;

import controllers.root.Resource;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import models.forms.course.CourseForm1;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.courses.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

import static play.data.Form.form;

public class Courses extends Controller {

    private static Call postCall(final String dept) {
        return controllers.root.admin_portal.departments.department.courses.routes.
                Courses.post(dept);
    }

    public static String url(final String dept) {
        return controllers.root.admin_portal.departments.department.courses.routes.
                Courses.get(dept).url();
    }

    private static Iterator<Course> courses(final String dept)
            throws SQLException {
        return CourseRepository.getInstance().getAllByDepartment(dept);
    }

    private static Result render(final boolean create, final String dept)
            throws SQLException {

        final String context = Courses.url(dept);

        if (create) {

            final Form<CourseForm1> form =
                    form(CourseForm1.class).bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(courses.render(Courses.courses(dept),
                        context, form, dept, Resource.BACK_LINK(context),
                        postCall(dept)));
            }

            CourseRepository.getInstance().add(form.get().toCourse(dept));
        }
        return ok(courses.render(Courses.courses(dept), context,
                form(CourseForm1.class), dept, Resource.BACK_LINK(context),
                postCall(dept)));
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
