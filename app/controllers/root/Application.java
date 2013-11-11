package controllers.root;

import controllers.resources.BaseResource;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    public static Result get() {

        return BaseResource.get();
    }
}
