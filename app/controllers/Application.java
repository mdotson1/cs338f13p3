package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

public class Application extends Controller {
    public static Result index() {
        return ok(index.render("Your new application is ready. DOOD"));
    }
}
