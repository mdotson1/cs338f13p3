package controllers.resources;

import controllers.root.admin.departments.Departments;
import controllers.root.admin.departments.department.faculty.Faculty;
import controllers.services.ProfessorService;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.SQLException;
import java.util.Map;
import views.html.*;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class FacultyResource {

    private final static Form<Professor> PROFESSOR_FORM =
            Form.form(Professor.class);

    public static Result get(final String department) {
        final String context = Faculty.url(department);

        try {
            return ok(faculty.render(
                    ProfessorRepository.getInstance().getFaculty(department),
                    context, PROFESSOR_FORM, Resource.BACK_LINK(context),
                    department));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String department) {
        final String context = Faculty.url(department);

        final Form<Professor> filledForm = PROFESSOR_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {

            ProfessorService.createProfessor(filledForm.data(), department);

            return ok(faculty.render(
                    ProfessorRepository.getInstance().getFaculty(department),
                    context, filledForm, Resource.BACK_LINK(context),
                    department));

        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
