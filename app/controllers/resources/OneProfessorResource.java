package controllers.resources;

import controllers.root.admin.course_schedules.CourseSchedules;
import controllers.root.admin.departments.department.faculty.professor.Professor;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;
import java.util.Map;

import static play.mvc.Results.ok;

public class OneProfessorResource {
    public static Result get(final String department, int professorId) {
        final String context = Professor.url(department, professorId);

        try {
            return ok(one_professor.render(
                    ProfessorRepository.getInstance().findById(professorId),
                    context, context, null, Resource.BACK_LINK(context)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String department, int professorId) {
        return ok();
    }
}
