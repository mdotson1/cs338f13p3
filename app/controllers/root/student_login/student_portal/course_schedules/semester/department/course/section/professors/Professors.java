package controllers.root.student_login.student_portal.course_schedules.semester.department.course.section.professors;

import controllers.root.Resource;
import controllers.root.student_login.student_portal.departments.department.faculty.Faculty;
import models.course.CourseOffering;
import models.course.Semester;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.student_login.student_portal.course_schedules.semester.department.course.section.professors.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

public class Professors extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.student_login.student_portal.course_schedules.
                semester.department.course.section.professors.routes.Professors.
                get(studentId, seasonAndYear, department, courseNum,
                        sectionNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String department,
                                 final String courseNum,
                                 final String sectionNum) throws SQLException {

        final String context = Professors.url(studentId, seasonAndYear, department,
                courseNum, sectionNum);

        final String[] split = seasonAndYear.split(" ");
        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final CourseOffering co = CourseOfferingRepository.getInstance().
                findBySectionSemester(season, year, department,
                        Short.parseShort(courseNum), Short.parseShort(sectionNum));

        final Iterator<Professor> profs = CoursesTeachingRepository.
                getInstance().findProfessorsForCourse(co.getCourseOfferingId());

        final String courseInfo = department + "-" + courseNum + "-" + sectionNum;
        final String professorUrl = Faculty.url(studentId, department);

        return ok(professors.render(profs, professorUrl, Resource.BACK_LINK(context),
                courseInfo));
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        try {
            return render(studentId, seasonAndYear, department, courseNum,
                    sectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
