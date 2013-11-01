package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class CourseResource extends Controller {
    public static Result getAllCourses() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addCourse() {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getCourse(final String deptAndCourseNum) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updateCourse(final String deptAndCourseNum) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
