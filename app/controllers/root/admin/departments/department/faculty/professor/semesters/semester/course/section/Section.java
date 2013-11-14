package controllers.root.admin.departments.department.faculty.professor.semesters.semester.course.section;

import controllers.resources.OneSectionResource;
import play.mvc.Controller;
import play.mvc.Result;

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

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum,
                             final String sectionNum) {

        return OneSectionResource.professor_get(department, professorId,
                seasonAndYear, Short.parseShort(courseNum),
                Short.parseShort(sectionNum));
    }
}
