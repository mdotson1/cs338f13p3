package controllers.root.admin.departments.department.faculty.professor.semesters.semester.courses;

import play.mvc.Controller;
import play.mvc.Result;

public class Courses extends Controller {
    public static Result get(final String department, final int professorId,
                             final String seasonAndYear) {

        return ok();
    }
}
