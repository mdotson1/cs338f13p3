package controllers.resources;

import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.services.SemesterService;
import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CourseSchedulesResource {

    private final static Form<Semester> SEMESTER_FORM =
            Form.form(Semester.class);

    public static Result course_schedules_get() {

        final String context = CourseSchedules.url();

        try {
            return ok(course_schedules.render(
                    SemesterRepository.getInstance().getAll(), context,
                    SEMESTER_FORM, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result course_schedules_post() {

        final String context = CourseSchedules.url();

        final Form<Semester> filledForm = SEMESTER_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            SemesterService.createSemester(filledForm.data());

            return ok(course_schedules.render(
                    SemesterRepository.getInstance().getAll(), context,
                    filledForm, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
