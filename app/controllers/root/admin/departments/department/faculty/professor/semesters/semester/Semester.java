package controllers.root.admin.departments.department.faculty.professor.semesters.semester;

import controllers.resources.OneSemesterResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Semester extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear) {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.semester.routes.Semester.get(dept, professorId,
                        seasonAndYear).url();
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear) {

        return OneSemesterResource.professor_get(department, professorId,
                seasonAndYear);
    }
}
