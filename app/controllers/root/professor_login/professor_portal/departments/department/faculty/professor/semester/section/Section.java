package controllers.root.professor_login.professor_portal.departments.department.faculty.professor.semester.section;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.database.dao.relationships.CoursesTeachingRepository;
import models.person.Professor;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.professor.semester.section.*;
import views.html.helpers.*;

import java.sql.SQLException;
import java.util.Iterator;

import static play.mvc.Results.ok;

public class Section {

    public static String url(final int thisId, final String dept,
                             final int professorId,
                             final String seasonAndYear,
                             final String deptCnSn) {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.professor.semester.section.routes.Section.
                get(thisId, dept, professorId, seasonAndYear, deptCnSn).url();
    }

    private static Result render(final int thisId, final String department,
                                 final int professorId,
                                 final String seasonAndYear,
                                 final String deptCnSn)
            throws SQLException {

        final String context = Section.url(thisId, department, professorId,
                seasonAndYear, deptCnSn);

        final String[] split = seasonAndYear.split(" ");
        final models.course.Semester.Season season =
                models.course.Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        final String[] deptCourseSection = deptCnSn.split("-");
        final String dept = deptCourseSection[0];
        final short courseNum = Short.parseShort(deptCourseSection[1]);
        final short sectionNum = Short.parseShort(deptCourseSection[2]);

        final CourseOffering co = CourseOfferingRepository.getInstance().find(
                dept, courseNum, sectionNum, season, year);

        final Professor prof = ProfessorRepository.getInstance().
                findById(professorId);

        return ok(section.render(co, context, Resource.BACK_LINK(context),
                prof));
    }

    public static Result get(final int thisId, final String department,
                             final int professorId,
                             final String seasonAndYear,
                             final String deptCnSn)
    {
        try {
            return render(thisId, department, professorId, seasonAndYear,
                    deptCnSn);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
