package controllers.root.admin.course_schedules;

import controllers.root.Resource;
import controllers.services.SemesterService;
import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class CourseSchedules extends Controller {

    private final static Form<Semester> SEMESTER_FORM =
            Form.form(Semester.class);

    private static Call postCall() {
        return controllers.root.admin.course_schedules.routes.
                CourseSchedules.post();
    }

    public static String url() {
        return controllers.root.admin.course_schedules.routes.CourseSchedules.
                get().url();
    }

    private static Result render(final boolean create) throws SQLException {

        final String context = CourseSchedules.url();
        final Form<Semester> form;

        if (create) {

            form = SEMESTER_FORM.bindFromRequest();

            if(form.hasErrors()) {
                return badRequest();
            }
            SemesterService.createSemester(form.data());
        } else {
            form = SEMESTER_FORM;
        }
        return ok(course_schedules.render(
                SemesterRepository.getInstance().getAll(), context,
                form, Resource.BACK_LINK(context), postCall()));
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
