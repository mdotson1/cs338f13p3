package controllers.root.admin_portal.course_schedules.semester.department.course.section;

import controllers.root.Resource;
import controllers.root.admin_portal.course_schedules.semester.department.course.section.professors.Professors;
import controllers.root.admin_portal.course_schedules.semester.department.course.section.students.Students;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.course.section.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Section extends Controller {

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                course.section.routes.Section.get(seasonAndYear, department,
                courseNum, sectionNum).url();
    }

    private static Result render(final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum)
            throws SQLException {

        final String context = Section.url(seasonAndYear, department, courseNum,
                sectionNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Map<String,String> namesAndURLs =
                new LinkedHashMap<String, String>();
        namesAndURLs.put("Students Taking This Course Offering",
                Students.url(seasonAndYear, department, courseNum, sectionNum));
        namesAndURLs.put("Professor(s) Teaching This Course Offering",
                Professors.url(seasonAndYear, department, courseNum,
                        sectionNum));

        return ok(section.render(CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum),
                        Short.parseShort(sectionNum)), context,
                Resource.BACK_LINK(context), namesAndURLs));
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
