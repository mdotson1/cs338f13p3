package controllers.root.admin.departments.department.faculty.professor;

import controllers.resources.OneProfessorResource;
import controllers.root.admin.departments.department.faculty.Faculty;
import play.mvc.Controller;
import play.mvc.Result;

public class Professor extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin.departments.department.faculty.professor
                .routes.Professor.get(dept, professorId).url();
    }

    public static Result get(final String department, final int professorId)
    {
        return OneProfessorResource.get(department, professorId);
    }
}
