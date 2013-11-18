package controllers.services;

import models.course.Course;
import models.database.dao.concrete.CourseRepository;

import java.sql.SQLException;
import java.util.Map;

public class CourseService {

    public static void createCourse(final Map<String,String> data,
                                    final String dept) throws SQLException {
        final Course c = new Course(dept,
                Short.parseShort(data.get("Course Number")),
                Double.parseDouble(data.get("Cost (USD)")),
                data.get("Course Description"));

        CourseRepository.getInstance().add(c);
    }
}
