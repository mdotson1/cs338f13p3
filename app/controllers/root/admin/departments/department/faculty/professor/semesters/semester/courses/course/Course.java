package controllers.root.admin.departments.department.faculty.professor.semesters.semester.courses.course;

import play.mvc.Controller;
import play.mvc.Result;

public class Course extends Controller {
    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum)
    {
        return ok();
    }
}
