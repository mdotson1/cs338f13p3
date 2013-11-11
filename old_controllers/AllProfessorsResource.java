package controllers;

import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.ContactInformation;
import models.person.Professor;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class AllProfessorsResource extends Controller {

    private static final String PROFESSORS_URI = routes.ProfessorResource.
            getAllProfessors().absoluteURL(request());

    private final static Form<Professor> PROFESSOR_FORM = Form.form(Professor.class);

    public static Result getAllProfessors() {
        try {
            return ok(professors_table.render(
                    ProfessorRepository.getInstance().getAll(), PROFESSORS_URI,
                    PROFESSOR_FORM));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addProfessor() {
        final Form<Professor> filledForm = PROFESSOR_FORM.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest();
        }
        try {
            Map<String,String> data = filledForm.data();

            final ContactInformation ci = new ContactInformation(
                    data.get("Home Address"), data.get("Work Address"),
                    data.get("Last Name"), data.get("First Name"),
                    data.get("Home Phone"), data.get("Work Phone"),
                    data.get("Cell Phone"));
            Professor s = new Professor(ci, data.get("Date of Birth"),
                    data.get("Department"));

            ProfessorRepository.getInstance().add(s);
            return ok(professors_table.render(
                    ProfessorRepository.getInstance().getAll(), PROFESSORS_URI,
                    filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result removeProfessor() {
        return ok();
    }
}
