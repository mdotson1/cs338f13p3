package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class SemesterResource extends Controller {

    public static Result getAllSemesters() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getSemester(final String seasonAndYear) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addSemester() {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
