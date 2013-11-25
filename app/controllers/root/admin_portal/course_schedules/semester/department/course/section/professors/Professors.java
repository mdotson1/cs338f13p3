package controllers.root.admin_portal.course_schedules.semester.department.course.section.professors;

import controllers.root.Resource;
import controllers.root.admin_portal.departments.department.faculty.Faculty;
import controllers.services.ProfessorCourseService;
import models.course.Semester;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.section.professors.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professors extends Controller {

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                course.section.professors.routes.Professors.get(seasonAndYear,
                department, courseNum, sectionNum).url();
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) throws SQLException {

        final String context = Professors.url(seasonAndYear, department,
                courseNum, sectionNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<Professor> profs = ProfessorCourseService.professorsTeachingCourse(
                season, year, department, courseNum, sectionNum);
        final String courseInfo = department + "-" + courseNum + "-" + sectionNum;
        final String professorUrl = Faculty.url(department);

        return ok(professors.render(profs, professorUrl, Resource.BACK_LINK(context),
                courseInfo));
    }

    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        try {
            return render(seasonAndYear, department, courseNum, sectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
