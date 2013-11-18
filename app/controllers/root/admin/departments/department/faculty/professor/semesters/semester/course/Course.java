package controllers.root.admin.departments.department.faculty.professor.semesters.semester.course;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.departments.department.faculty.professor.semesters.semester.course.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Course extends Controller {

    public static String url(final String dept, final int professorId,
                             final String seasonAndYear, final String courseNum)
    {
        return controllers.root.admin.departments.department.faculty.professor
                .semesters.semester.course.routes.Course.get(dept, professorId,
                        seasonAndYear, courseNum).url();
    }

    private static Result render(final String department, final int professorId,
                                 final String seasonAndYear,
                                 final String courseNum)
            throws SQLException {


        // TODO
        return ok();
    }

    public static Result get(final String department, final int professorId,
                             final String seasonAndYear, final String courseNum)
    {
        try {
            return render(department, professorId, seasonAndYear, courseNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
