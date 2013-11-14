package controllers.root.admin.departments.department.faculty;

import controllers.resources.FacultyResource;
import controllers.root.admin.departments.Departments;
import play.mvc.Controller;
import play.mvc.Result;

public class Faculty extends Controller {

    public static String url(final String dept) {
        return controllers.root.admin.departments.department.faculty.routes.
                Faculty.get(dept).url();
    }

    public static Result get(final String department) {

        return FacultyResource.get(department);
    }

    public static Result post(final String department) {
        return FacultyResource.post(department);
    }
}
