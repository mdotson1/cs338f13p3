package controllers.root.professor_login.professor_portal.course_schedules.semester.department.course.section.professors;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.api.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import models.course.Semester.Season;

import views.html.root.professor_login.professor_portal.course_schedules.semester.department.course.section.professors.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professors extends Controller {

    private static Call postCall(final int professorId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                semester.department.course.section.professors.routes.Professors.
                post(professorId, seasonAndYear, department, courseNum,
                        sectionNum);
    }

    public static String url(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                semester.department.course.section.professors.routes.Professors.
                get(professorId, seasonAndYear, department, courseNum,
                        sectionNum).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum, final boolean create)
            throws SQLException {

        final String context = Professors.url(professorId, seasonAndYear,
                department, courseNum, sectionNum);

        final String[] sy = seasonAndYear.split(" ");
        final Season season = Season.valueOf(sy[0]);
        final short year = Short.parseShort(sy[1]);

        final CourseOffering co = CourseOfferingRepository.getInstance().find(
                department, Short.parseShort(courseNum),
                Short.parseShort(sectionNum), season, year);
        final Iterator<Professor> profs = CoursesTeachingRepository.getInstance().
                findProfessorsForCourse(co.getCourseOfferingId());

        if (create) {
            CoursesTeachingRepository.getInstance().add(professorId,
                    co.getCourseOfferingId());
        }

        return ok(professors.render(profs, context, Resource.BACK_LINK(context),
                postCall(professorId, seasonAndYear, department, courseNum,
                        sectionNum), department + "-" + courseNum));
    }

    public static Result get(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        try {
            return render(professorId, seasonAndYear, department, courseNum,
                    sectionNum, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final int professorId, final String seasonAndYear,
                              final String department, final String courseNum,
                              final String sectionNum) {

        try {
            return render(professorId, seasonAndYear, department, courseNum,
                    sectionNum, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
