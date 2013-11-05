package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class Application extends Controller {
    public static Result index() {
        return ok(view.render("students", routes.StudentResource.getAllStudents().absoluteURL(request())));
    }

    private static void renderURLs() {
        view.render("students", routes.StudentResource.getAllStudents().absoluteURL(request()));
        view.render("professors", routes.ProfessorResource.getAllProfessors().absoluteURL(request()));
        view.render("courses", routes.CourseResource.getAllCourses().absoluteURL(request()));
        view.render("semesters", routes.SemesterResource.getAllSemesters().absoluteURL(request()));
    }
}
