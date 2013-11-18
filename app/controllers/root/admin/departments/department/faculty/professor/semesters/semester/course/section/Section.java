package controllers.root.admin.departments.department.faculty.professor.semesters.semester.course.section;

import models.course.Semester.Season;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semesters.semester.course.section.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Section extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear, final String courseNum,
                             final String sectionNum)
    {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.semester.course.section.routes.Section.get(dept,
                        professorId, seasonAndYear, courseNum, sectionNum).
                        url();
    }

    private static Result render(final String department, final int professorId,
                                 final String seasonAndYear,
                                 final String courseNum,
                                 final String sectionNum) throws SQLException {

        final String context = Section.url(department, professorId,
                seasonAndYear, courseNum, sectionNum);

        final String[] split = seasonAndYear.split(" ");

        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        // TODO
        return ok();
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum,
                             final String sectionNum) {
        try {
            return render(department, professorId, seasonAndYear, courseNum,
                    sectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
