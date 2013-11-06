package controllers;

import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.concrete.StudentRepository;
import models.person.ContactInformation;
import models.person.Professor;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class ProfessorResource extends Controller {

    private static final String professorsUri = routes.ProfessorResource.
            getAllProfessors().absoluteURL(request());

    private final static Form<Professor> professorForm = Form.form(Professor.class);

    public static Result getAllProfessors() {
        try {
            return ok(professors_table.render(
                    ProfessorRepository.getInstance().getAll(), professorsUri,
                    professorForm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addProfessor() {
        final Form<Professor> filledForm = professorForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final ContactInformation ci = new ContactInformation(
                    data.get("homeAddr"), data.get("workAddr"),
                    data.get("firstName"), data.get("lastName"),
                    data.get("workPhone"), data.get("homePhone"),
                    data.get("cellPhone"));
            Professor s = new Professor(ci, data.get("dateOfBirth"),
                    data.get("department"));

            ProfessorRepository.getInstance().add(s);
            return ok(professors_table.render(
                    ProfessorRepository.getInstance().getAll(), professorsUri,
                    filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result getProfessor(final Integer id) {
        try {
            return ok(single_professor.render(
                    ProfessorRepository.getInstance().findById(id),
                    professorsUri));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result removeProfessor() {
        return ok();
    }

    public static Result updateProfessor(final Integer id) {
        return ok();
    }
}
