package controllers.root.admin.course_schedules.semester.department.course.section;

import controllers.resources.OneSectionResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Section extends Controller {

    public static String url(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {
        return controllers.root.admin.course_schedules.semester.department.
                course.section.routes.Section.get(seasonAndYear, department,
                courseNum, sectionNum).url();
    }

    public static Result get(final String seasonAndYear,
                             final String department, final String courseNum,
                             final String sectionNum) {

        return OneSectionResource.course_schedules_get(seasonAndYear,
                department, Short.parseShort(courseNum),
                Short.parseShort(sectionNum));
    }
}
