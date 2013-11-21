package controllers.root.admin_portal.students.student.semesters.semester.course;

import models.course.Semester;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.students.student.semesters.semester.section.*;
import views.html.helpers.*;

import java.sql.SQLException;

public class Section extends Controller {

    public static String url(final int studentId, final String seasonAndYear,
                             final String courseNum, final String sectionNum) {
        return controllers.root.admin_portal.students.student.semesters.semester.
                course.routes.Section.get(studentId, seasonAndYear, courseNum,
                sectionNum).url();
    }

    private static Result render(final int studentId,
                                 final String seasonAndYear,
                                 final String courseNum,
                                 final String sectionNum)
            throws SQLException {

        final String[] split = seasonAndYear.split(" ");

        final Semester.Season season = Semester.Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        /*
        // TODO
         return ok(payments.render(PaymentHistoryRepository.getInstance().
                findAllPaymentsByStudent(studentId), context, form, studentId,
                Resource.BACK_LINK(context), postCall(studentId)));
         */
         return ok();
    }

    public static Result get(final int studentId, final String seasonAndYear,
                             final String courseNum, final String sectionNum) {
        try {
            return render(studentId, seasonAndYear, courseNum, sectionNum);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
