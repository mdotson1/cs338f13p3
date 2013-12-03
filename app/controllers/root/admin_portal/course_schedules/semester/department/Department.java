package controllers.root.admin_portal.course_schedules.semester.department;

import controllers.root.Resource;
import controllers.root.admin_portal.departments.department.courses.Courses;
import models.course.CourseOffering;
import models.database.dao.concrete.CourseOfferingRepository;
import models.forms.course_offering.CourseOfferingForm2;
import play.api.mvc.Call;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.root.admin.course_schedules.semester.department.*;
import views.html.helpers.*;
import models.course.Semester.Season;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Department extends Controller {

    private static Call postCall(final String seasonAndYear,
                                 final String department) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                routes.Department.post(seasonAndYear,
                department);
    }

    public static String url(final String seasonAndYear,
                             final String department) {
        return controllers.root.admin_portal.course_schedules.semester.department.
                routes.Department.get(seasonAndYear, department).
                url();
    }

    private static Result render(final String dept, final String seasonAndYear,
                                 final boolean create) throws SQLException {

        final String context = Department.url(seasonAndYear, dept);

        final String[] split = seasonAndYear.split(" ");
        final Season season = Season.valueOf(split[0]);
        final short year = Short.parseShort(split[1]);

        if (create) {

            final Form<CourseOfferingForm2> form =
                    Form.form(CourseOfferingForm2.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest(department.render(CourseOfferingRepository.
                        getInstance().coursesBySemesterDepartment(season, year,
                        dept), context, Resource.BACK_LINK(context),
                        seasonAndYear, dept, form, postCall(seasonAndYear,
                        dept), null, null));
            }

            final CourseOffering co = form.get().toCourseOffering(dept,
                    season, year);

            if (co.getCourse() == null) {
                final String errorMsg = "That course does not exist. If you " +
                        "would like to create that course, please click ";
                final String url = Courses.url(dept);

                final Map<String, String> nameAndUrl =
                        new LinkedHashMap<String, String>();
                nameAndUrl.put(url, "here");

                return badRequest(department.render(CourseOfferingRepository.
                        getInstance().coursesBySemesterDepartment(season, year,
                        dept), context, Resource.BACK_LINK(context),
                        seasonAndYear, dept, form, postCall(seasonAndYear,
                        dept), nameAndUrl, errorMsg));
            }

            final boolean contains = CourseOfferingRepository.getInstance().
                    contains(dept, co.getCourse().getCourseNumber(),
                            co.getSectionNumber(), season, year);

            if (contains) {
                final String errorMsg = "Course offering " + dept + "-" +
                        co.getCourse().getCourseNumber() + "-" +
                        co.getSectionNumber() +" already exists.";

                return badRequest(department.render(CourseOfferingRepository.
                        getInstance().coursesBySemesterDepartment(season, year,
                        dept), context, Resource.BACK_LINK(context),
                        seasonAndYear, dept, form, postCall(seasonAndYear,
                        dept), null, errorMsg));
            } else {
                CourseOfferingRepository.getInstance().add(
                        form.get().toCourseOffering(dept, season, year));
            }
        }

        return ok(department.render(CourseOfferingRepository.
                getInstance().coursesBySemesterDepartment(season, year,
                dept), context, Resource.BACK_LINK(context),
                seasonAndYear, dept, Form.form(CourseOfferingForm2.class),
                postCall(seasonAndYear, dept), null, null));
    }

    public static Result get(final String seasonAndYear,
                             final String department) {

        try {
            return render(department, seasonAndYear, false);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }

    public static Result post(final String seasonAndYear,
                             final String department) {
        try {
            return render(department, seasonAndYear, true);
        } catch (SQLException e) {
            return ok(debug.render(e.toString()));
        }
    }
}
