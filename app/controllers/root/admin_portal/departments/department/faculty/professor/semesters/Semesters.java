package controllers.root.admin_portal.departments.department.faculty.professor.semesters;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semesters.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Semesters extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin_portal.departments.department.faculty.professor
                .semesters.routes.Semesters.get(dept,
                        professorId).url();
    }

    private static Result render(final String department, final int professorId)
            throws SQLException {

        final String context = Semesters.url(department, professorId);

        final Iterator<Semester> sems = CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId);

        final Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        return ok(semesters.render(sems, context, Resource.BACK_LINK(context),
                prof));
    }

    public static Result get(final String department, final int professorId) {

        try {
            return render(department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
