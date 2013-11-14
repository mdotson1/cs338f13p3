package controllers.root.admin.departments.department.faculty.professor.semesters.semester.course;

import controllers.resources.OneCourseResource;
import play.mvc.Controller;
import play.mvc.Result;

public class Course extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear, final String courseNum)
    {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.semester.course.routes.Course.get(dept, professorId,
                        seasonAndYear, courseNum).url();
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum)
    {
        return OneCourseResource.professor_get(department, professorId,
                seasonAndYear, courseNum);
    }
}
