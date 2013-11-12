package controllers.resources;

import controllers.root.admin.departments.Departments;
import controllers.services.Bursar;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.PaymentRepository;
import models.database.dao.relationships.PaymentHistoryRepository;
import models.person.Payment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CoursesResource {

    private final static Form<Course> COURSE_FORM = Form.form(Course.class);

    public static Result get(final String dept,
                             final Map<String,String> backLink,
                             final String contextUri) {
        try {
            return ok(courses.render(
                    CourseRepository.getInstance().getAllByDepartment(dept),
                    contextUri, COURSE_FORM, dept, backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String dept,
                              final Map<String,String> backLink,
                              final String contextUri) {
        /*
        final Form<Course> filledForm = COURSE_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final String dept = data.get("Department");

            final Course c = new Course(dept,
                    Short.parseShort(data.get("Course Number")),
                    Double.parseDouble(data.get("Cost (USD)")),
                    data.get("Course Description"));

            CourseRepository.getInstance().add(c);

            return ok(courses.render(
                    CourseRepository.getInstance().getAll(), coursesUri,
                    filledForm, dept, backLink));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
        */
        return ok();
    }
}
