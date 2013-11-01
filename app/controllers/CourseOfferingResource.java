package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class CourseOfferingResource extends Controller {

    public static Result getAllCourseOfferingsForSem(final String seasonAndYear) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result addCourseOffering(final String seasonAndYear) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result getCourseOffering(final String seasonAndYear, final String deptCourseNumSecNum) {
        return ok(index.render("Your new application is ready. DOOD"));
    }

    public static Result updateCourseOffering(final String seasonAndYear, final String deptCourseNumSecNum) {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
