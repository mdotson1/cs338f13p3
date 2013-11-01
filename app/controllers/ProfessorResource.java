package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class ProfessorResource extends Controller {
    public static Result getAllProfessors() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addProfessor() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getProfessor(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result removeProfessor() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updateProfessor(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
