package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    public static Result rootIndex() {

        Map<String, String> loginsAndURLs = new HashMap<String, String>();

        loginsAndURLs.put("Login as a student", routes.Application.studentIndex().absoluteURL(request()));
        loginsAndURLs.put("Login as a professor", routes.Application.professorIndex().absoluteURL(request()));
        loginsAndURLs.put("Login as an administrator", routes.Application.adminIndex().absoluteURL(request()));

        return ok(index.render(loginsAndURLs));
    }

    public static Result adminIndex() {

        Map<String, String> namesAndURLs = new HashMap<String, String>();

        namesAndURLs.put("students", routes.StudentResource.getAllStudents().absoluteURL(request()));
        namesAndURLs.put("professors", routes.ProfessorResource.getAllProfessors().absoluteURL(request()));
        namesAndURLs.put("courses", routes.CourseResource.getAllCourses().absoluteURL(request()));
        namesAndURLs.put("semesters", routes.SemesterResource.getAllSemesters().absoluteURL(request()));

        return ok(index.render(namesAndURLs));
    }

    public static Result studentIndex() {

//        Map<String, String> namesAndURLs = new HashMap<String, String>();
//
//        namesAndURLs.put("students", routes.StudentResource.getAllStudents().absoluteURL(request()));
//        namesAndURLs.put("professors", routes.ProfessorResource.getAllProfessors().absoluteURL(request()));
//        namesAndURLs.put("courses", routes.CourseResource.getAllCourses().absoluteURL(request()));
//        namesAndURLs.put("semesters", routes.SemesterResource.getAllSemesters().absoluteURL(request()));

        return ok();//index.render(namesAndURLs));
    }

    public static Result professorIndex() {

//        Map<String, String> namesAndURLs = new HashMap<String, String>();
//
//        namesAndURLs.put("students", routes.StudentResource.getAllStudents().absoluteURL(request()));
//        namesAndURLs.put("professors", routes.ProfessorResource.getAllProfessors().absoluteURL(request()));
//        namesAndURLs.put("courses", routes.CourseResource.getAllCourses().absoluteURL(request()));
//        namesAndURLs.put("semesters", routes.SemesterResource.getAllSemesters().absoluteURL(request()));

        return ok();//index.render(namesAndURLs));
    }
}
