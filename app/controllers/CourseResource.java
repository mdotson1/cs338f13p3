package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class CourseResource extends Controller {
    public static Result getAllCourses() {
        return ok();
    }

    public static Result addCourse() {
        return ok();
    }

    public static Result getCourse(final String deptAndCourseNum) {
        return ok();
    }

    public static Result updateCourse(final String deptAndCourseNum) {
        return ok();
    }
}
