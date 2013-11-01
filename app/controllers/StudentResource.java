package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class StudentResource extends Controller {
    public static Result getAllStudents() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addStudent() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getStudent(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result removeStudent() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updateStudent(final Integer id) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
