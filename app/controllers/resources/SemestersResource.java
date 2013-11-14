package controllers.resources;

import controllers.root.admin.departments.department.faculty.professor.semesters.ProfessorSemesters;
import controllers.root.admin.students.student.semesters.StudentSemesters;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.concrete.StudentRepository;
import models.database.dao.relationships.CoursesTakenRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import play.mvc.Result;
import views.html.*;

import java.sql.SQLException;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class SemestersResource {

    public static Result student_get(final int studentId) {

        final String context = StudentSemesters.url(studentId);
        try {
            return ok(semesters.render(
                    CoursesTakenRepository.getInstance().
                            allSemestersStudentAttended(studentId), context,
                    Resource.BACK_LINK(context),
                    StudentRepository.getInstance().findById(studentId)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result professor_get(final String department,
                                       final int professorId) {

        final String context = ProfessorSemesters.url(department, professorId);
        try {
            return ok(semesters.render(
                    CoursesTeachingRepository.getInstance().
                            allSemestersProfessorTaughtIn(professorId),
                    context, Resource.BACK_LINK(context),
                    ProfessorRepository.getInstance().findById(professorId)));
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
