package controllers;

import models.course.Semester;
import models.database.dao.concrete.SemesterRepository;

import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

public class AllSemesters extends Controller {

    private static final String SEMESTERS_URI = routes.SemesterResource.
            getAllSemesters().absoluteURL(request());

    private final static Form<Semester> semesterForm = Form.form(Semester.class);

    public static Result getAllSemesters() {
        try {
            return ok(semesters_table.render(
                    SemesterRepository.getInstance().getAll(), SEMESTERS_URI,
                    semesterForm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result addSemester() {
        final Form<Semester> filledForm = semesterForm.bindFromRequest();

        if(filledForm.hasErrors()) {
            return badRequest(debug.render(filledForm.errorsAsJson().toString()));
        }
        try {
            Map<String,String> data = filledForm.data();

            Semester s = new Semester(
                    Semester.Season.valueOf(data.get("season")),
                    Short.parseShort(data.get("year")));

            SemesterRepository.getInstance().add(s);
            return ok(semesters_table.render(
                    SemesterRepository.getInstance().getAll(), SEMESTERS_URI,
                    filledForm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok();
    }
}
