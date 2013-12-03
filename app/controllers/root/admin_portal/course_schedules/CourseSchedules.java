package controllers.root.admin_portal.course_schedules;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;
import models.forms.semester.SemesterForm1;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

import static play.data.Form.form;

public class CourseSchedules extends Controller {

    private static Call postCall() {
        return controllers.root.admin_portal.course_schedules.routes.
                CourseSchedules.post();
    }

    public static String url() {
        return controllers.root.admin_portal.course_schedules.routes.CourseSchedules.
                get().url();
    }

    private static final Iterator<Semester> semesters() throws SQLException {
        return SemesterRepository.getInstance().getAll();
    }

    private static Result render(final boolean create) throws SQLException {

        final String context = CourseSchedules.url();

        if (create) {

            final Form<SemesterForm1> form =
                    form(SemesterForm1.class).bindFromRequest();

            if (form.hasErrors()) {
                return badRequest(course_schedules.render(
                        CourseSchedules.semesters(), context, form,
                        Resource.BACK_LINK(context), postCall()));
            }

            SemesterRepository.getInstance().add(form.get().toSemester());
        }

        return ok(course_schedules.render(CourseSchedules.semesters(), context,
                form(SemesterForm1.class), Resource.BACK_LINK(context),
                postCall()));
    }

    public static Result get() {

        try {
            return render(false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post() {

        try {
            return render(true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
