package controllers.resources;

import controllers.root.admin.departments.Departments;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class DepartmentsResource {

    private final static Form<Course> COURSE_FORM = Form.form(Course.class);

    public static Result get(final Map<String,String> backLink) {
        try {
            return ok(departments.render(
                    CourseRepository.getInstance().allDepartments(),
                    Departments.url(), COURSE_FORM, backLink));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final Map<String,String> backLink) {

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

            return ok(departments.render(
                    CourseRepository.getInstance().allDepartments(),
                    Departments.url(), filledForm, backLink));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
