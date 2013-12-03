package controllers.root.student_login.student_portal.semesters.semester.course;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.semesters.semester.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String deptCourseNumSectionNum) {
        return controllers.root.student_login.student_portal.semesters.semester.
                course.routes.Course.get(studentId, seasonAndYear,
                deptCourseNumSectionNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String deptCourseNumSectionNum)
            throws SQLException {

        final String context = Course.url(studentId, seasonAndYear,
                deptCourseNumSectionNum);

        final String[] split = seasonAndYear.split(" ");

        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String[] dept_cn_sn = deptCourseNumSectionNum.split("-");

        final String department = dept_cn_sn[0];
        final String courseNum = dept_cn_sn[1];
        final String sectionNum = dept_cn_sn[2];

        final CourseOffering co = CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum),
                        Short.parseShort(sectionNum));

        return ok(course.render(co, context, Resource.BACK_LINK(context),
                null));
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String deptCourseNumSectionNum) {

        try {
            return render(studentId, seasonAndYear, deptCourseNumSectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
