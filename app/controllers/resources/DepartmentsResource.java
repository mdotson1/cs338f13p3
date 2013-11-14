package controllers.resources;

import controllers.root.admin.Admin;
import controllers.root.admin.departments.Departments;
import controllers.services.ProfessorService;
import models.course.Course;
import models.database.dao.concrete.CourseRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class DepartmentsResource {

    private final static Form<Professor> PROFESSOR_FORM = Form.form(Professor.class);

    public static Result admin_get() {

        final String context = Departments.url();

        try {
            return ok(departments.render(
                    ProfessorRepository.getInstance().allDepartments(),
                    context, PROFESSOR_FORM, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result admin_post() {

        final String context = Departments.url();

        final Form<Professor> filledForm = PROFESSOR_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {

            ProfessorService.createProfessor(filledForm.data());

            return ok(departments.render(
                    ProfessorRepository.getInstance().allDepartments(),
                    context, filledForm, Resource.BACK_LINK(context)));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
