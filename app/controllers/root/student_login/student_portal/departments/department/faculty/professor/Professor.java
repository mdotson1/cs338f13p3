package controllers.root.student_login.student_portal.departments.department.faculty.professor;

import controllers.root.Resource;
import models.course.Semester;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.departments.department.faculty.professor.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professor extends Controller{

    public static String url(final int studentId, final String department,
                             final int professorId) {
        return controllers.root.student_login.student_portal.departments.department.
                faculty.professor.routes.Professor.get(studentId, department,
                professorId).url();
    }

    private static Result render(final int studentId, final String department,
                                 final int professorId) throws SQLException {

        final String context = Professor.url(studentId, department,
                professorId);

        final models.person.Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        final Iterator<Semester> sems = CoursesTeachingRepository.getInstance().
                allSemestersProfessorTaughtIn(professorId);

        return ok(professor.render(prof, context, null,
                Resource.BACK_LINK(context)));
    }

    public static Result get(final int studentId, final String department,
                             final int professorId) {

        try {
            return render(studentId, department, professorId);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
