package controllers.root.professor_login.professor_portal.departments.department.faculty.professor.semesters.semester.course;

import play.mvc.Controller;
import play.mvc.Result;

import views.html.root.professor_login.professor_portal.departments.department.faculty.professor.semesters.semester.course.*;
import views.html.helpers.*;

public class Course extends Controller {

    public static String url(final int thisId, final String department,
                             final int professorId, final String seasonAndYear,
                             final String courseNum) {
        return controllers.root.professor_login.professor_portal.departments.
                department.faculty.professor.semesters.semester.course.routes.
                Course.get(thisId, department, professorId, seasonAndYear,
                courseNum).url();
    }

    private static Result render(final int thisId, final String department,
                                 final int professorId,
                                 final String seasonAndYear,
                                 final String courseNum) {
        // TODO
        return ok();
    }

    public static Result get(final int thisId, final String department,
                             final int professorId, final String seasonAndYear,
                             final String courseNum) {

        return render(thisId, department, professorId, seasonAndYear,
                courseNum);
    }
}