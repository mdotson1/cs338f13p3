package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {

        Map<String, String> namesAndURLs = new HashMap<String, String>();

        namesAndURLs.put("students", routes.StudentResource.getAllStudents().absoluteURL(request()));
        namesAndURLs.put("professors", routes.ProfessorResource.getAllProfessors().absoluteURL(request()));
        namesAndURLs.put("courses", routes.CourseResource.getAllCourses().absoluteURL(request()));
        namesAndURLs.put("semesters", routes.SemesterResource.getAllSemesters().absoluteURL(request()));

        return ok(link.render(namesAndURLs));
    }
}
