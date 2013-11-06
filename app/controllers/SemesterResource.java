package controllers;

import models.course.Semester;
import play.*;
import play.data.Form;
import play.mvc.*;

import models.*;
import views.html.*;

import java.sql.SQLException;

public class SemesterResource extends Controller {

    private static final String semestersUri = routes.SemesterResource.
            getAllSemesters().absoluteURL(request());

    private final static Form<Semester> semesterForm = Form.form(Semester.class);

    public static Result getAllSemesters() {
        return ok();
    }

    public static Result addSemester() {
        return ok();
    }
}
