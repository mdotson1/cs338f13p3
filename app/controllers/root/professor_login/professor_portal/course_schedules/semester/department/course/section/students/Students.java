package controllers.root.professor_login.professor_portal.course_schedules.semester.department.course.section.students;

import controllers.root.Resource;
import controllers.services.StudentCourseService;
import models.course.Semester;
import models.person.Student;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.course_schedules.semester.department.course.section.students.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Students extends Controller {

    public static String url(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.professor_login.professor_portal.course_schedules.
                semester.department.course.section.students.routes.Students.
                get(professorId, seasonAndYear, department, courseNum,
                        sectionNum).url();
    }

    private static Result render(final int professorId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) throws SQLException {

        final String context = Students.url(professorId, seasonAndYear,
                department, courseNum, sectionNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final Iterator<Student> stu = StudentCourseService.studentsTakingCourse(
                season, year, department, courseNum, sectionNum);
        final String courseInfo = department + "-" + courseNum + "-" + sectionNum;
        final String studentsUrl = controllers.root.admin_portal.students.Students.url();

        return ok(students.render(stu, studentsUrl, Resource.BACK_LINK(context),
                courseInfo));
    }

    public static Result get(final int professorId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        try {
            return render(professorId, seasonAndYear, department, courseNum,
                    sectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
