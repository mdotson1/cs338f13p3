package controllers.root.professor_login.professor_portal;

import controllers.root.Resource;
import controllers.root.professor_login.professor_portal.course_schedules.CourseSchedules;
import controllers.root.professor_login.professor_portal.departments.Departments;
import controllers.root.professor_login.professor_portal.semesters.Semesters;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorPortal extends Controller {

    public static String url(final int professorId) {
        return controllers.root.professor_login.professor_portal.routes.
                ProfessorPortal.get(professorId).url();
    }

    private static Result render(final int professorId) throws SQLException {
        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();

        final String context = ProfessorPortal.url(professorId);

        namesAndURLs.put("All Course Schedules", CourseSchedules.url(
                professorId));
        namesAndURLs.put("All Departments", Departments.url(professorId));
        namesAndURLs.put("Courses Teaching", Semesters.url(professorId));

        final Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        return ok(professor_portal.render(prof, namesAndURLs,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int professorId) {

        try {
            return render(professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
