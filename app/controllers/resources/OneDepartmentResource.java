package controllers.resources;

import controllers.root.admin.departments.department.courses.Courses;
import controllers.root.admin.departments.department.faculty.Faculty;
import play.mvc.Result;

import java.util.LinkedHashMap;
import java.util.Map;
import views.html.*;

import static play.mvc.Results.ok;

public class OneDepartmentResource {

    public static Result get(final String dept,
                             final Map<String,String> backLink) {

        Map<String,String> namesAndURLs = new LinkedHashMap<String, String>();
        namesAndURLs.put("Faculty Pages", Faculty.url(dept));
        namesAndURLs.put("Course Catalog", Courses.url(dept));
        namesAndURLs.putAll(backLink);

        return ok(one_department.render(dept, namesAndURLs));
    }
}
