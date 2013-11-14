package controllers.root.admin.students.student.semesters.semester.section;

import controllers.resources.OneSectionResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Section extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String courseNum, final String sectionNum) {
        return controllers.root.admin.students.student.semesters.semester.
                section.routes.Section.get(studentId, seasonAndYear, courseNum,
                sectionNum).url();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String courseNum, final String sectionNum) {

        return OneSectionResource.student_get(studentId, seasonAndYear,
                Short.parseShort(courseNum), Short.parseShort(sectionNum));
    }
}
