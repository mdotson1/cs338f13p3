package controllers.root.admin.departments.department.faculty.professor.semesters.semester.courses.course.section;

import play.mvc.Controller;
import play.mvc.Result;

public class Section extends Controller {
    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum,
                             final String sectionNum) {

        return ok();
    }
}
