package controllers.root.admin.departments.department.faculty.professor.semesters;

import controllers.resources.SemestersResource;
import play.mvc.Controller;
import play.mvc.Result;

public class ProfessorSemesters extends Controller {

    public static String url(final String dept, final int professorId) {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.routes.ProfessorSemesters.get(dept,
                        professorId).url();
    }

    public static Result get(final String department, final int professorId)
    {
        return SemestersResource.professor_get(department, professorId);
    }
}
