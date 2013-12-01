package controllers.root.admin_portal.departments.department.faculty.professor.semester.section;

import controllers.root.Resource;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.database.dao.concrete.ProfessorRepository;
import models.person.Professor;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semester.section.*;
import views.html.helpers.*;

import java.sql.SQLException;

import static play.mvc.Results.ok;

public class Section {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear,
                             final String courseAndSection) {
        return controllers.root.admin_portal.departments.department.faculty.
                professor.semester.section.routes.Section.get(dept, professorId,
                seasonAndYear, courseAndSection).url();
    }

    private static Result render(final String department, final int professorId,
                                 final String seasonAndYear,
                                 final String deptCnSn)
            throws SQLException {

        final String context = Section.url(department, professorId,
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

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear,
                             final String courseAndSection)
    {

        try {
            return render(department, professorId, seasonAndYear,
                    courseAndSection);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
